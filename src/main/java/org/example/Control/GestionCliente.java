package org.example.Control;

import org.example.Model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public  Cliente existeCliente( long identificacion){
        for (Cliente cltemp: listaClientes){
            if (cltemp.getCedula() == identificacion){
                return cltemp;
            }
        }
        return null;
    }
    public void eliminarCliente (long identificacion){

        boolean ver = true;
        char confirmacion = 0;
        for (Cliente cli: listaClientes){
            ver = false;
            System.out.println("\t Desea eliminar el cliente? \nS = si \nN = no");
            Scanner in = new Scanner(System.in);
            confirmacion = in.next().charAt(0);

            if (confirmacion == 'S'){
                listaClientes.remove(cli);
                System.out.println("\tEl documento del cliente elimado es: "+ cli.getCedula());
                System.out.println("\tCLIENTE ELIMINADO CON EXITO");
                break;
            }
        }
        if(ver && confirmacion != 'N')
            System.out.println("\t PRODUCTO NO ENCONTRADO");
    }

    public void verListadoClientes(){
        if (this.listaClientes.size() != 0){
            for (Cliente cliente : this.listaClientes){
                cliente.toString();
            }
        }
        else System.out.println("No hay clientes registrados en el sistema.");
    }
}

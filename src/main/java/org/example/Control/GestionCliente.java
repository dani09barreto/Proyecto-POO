package org.example.Control;

import org.example.Model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Cliente existeCliente(long identificacion) {
        for (Cliente cltemp : listaClientes) {
            if (cltemp.getCedula() == identificacion) {
                return cltemp;
            }
        }
        return null;
    }

    public void eliminarCliente(long identificacion) {

        boolean ver = true;
        char confirmacion = 0;
        for (Cliente cli : listaClientes) {

            if (cli.getCedula() == identificacion){
                ver = false;
                System.out.println("\t Desea eliminar el cliente? \n\tS = si \n\tN = no");
                Scanner in = new Scanner(System.in);
                confirmacion = in.next().charAt(0);

                if (confirmacion == 'S') {
                    listaClientes.remove(cli);
                    System.out.println("\tEl documento del cliente eliminado es: "+ cli.getCedula());
                    System.out.println("\tCLIENTE ELIMINADO CON EXITO");
                    break;
                }
            }
        }
        if (ver && confirmacion != 'N')
            System.out.println("\tPRODUCTO NO ENCONTRADO");
    }

    public void verListadoClientes() {
        if (this.listaClientes.size() != 0) {
            System.out.println("\n\t[!] Listado de clientes. Cantidad de clientes: " + this.listaClientes.size());
            for (int i=0; i<this.listaClientes.size(); i++) {
                System.out.println("\tCLiente [" + i + "]");
                System.out.println(listaClientes.get(i).toString());
            }
        }
        else System.out.println("\tNo hay clientes registrados en el sistema.");
    }

    public void modificarDatosCliente(long cedula){
        boolean clienteEncontrado=false;
        Cliente client=new Cliente();
        for(Cliente auxclient:listaClientes){
            if(auxclient.getCedula()==cedula){
                clienteEncontrado=true;
                client=auxclient;
            }
        }
        if(clienteEncontrado){
            System.out.println("El cliente a modificar: ");
            System.out.println(client.toString());
            int opcion=1;
            Scanner in = new Scanner(System.in);
            do{
                System.out.println("¿Qué aspecto desea modificar del cliente? Digite la opción deseada");
                System.out.println("\t1. Nombre Completo");
                System.out.println("\t2. Teléfono Contacto");
                System.out.println("\t3. Dirección ");
                System.out.println("\t0. No modificar datos");
                opcion = in.nextInt();
                in.useDelimiter("\n");
                switch (opcion){
                    case 1:
                        String nombreNuevo;
                        System.out.println("Digite el nuevo nombre: ");
                        nombreNuevo=in.next();
                        client.setNombreCompleto(nombreNuevo);
                        break;
                    case 2:
                        long telefonoNuevo;
                        System.out.println("Digite el nuevo teléfono: ");
                        telefonoNuevo=in.nextLong();
                        client.setTelefonoContacto(telefonoNuevo);
                        break;
                    case 3:
                        String direccionNueva;
                        System.out.println("Digite la nueva dirección: ");
                        direccionNueva=in.next();
                        client.setDireccion(direccionNueva);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Digite una opción válida");
                        break;
                }
            }while(opcion!=0);
        }else{
            System.out.println("\tEl cliente no fue encontrado");
        }
    }

    public void insertarCliente(Cliente clt){
        boolean verificacion = true;
        for(Cliente c: listaClientes){
            if(c.getCedula() == clt.getCedula()){
                System.out.println("\t[!] Error , ya existe un cliente con el mismo numero de cedula");
                verificacion = false;
                break;
            }
        }
        if(verificacion){
            listaClientes.add(clt);
            System.out.println("\tCLIENTE AGREGADO CON EXITO!!");
        }
    }

}
package org.PUJ.Control;

import org.PUJ.Model.Cliente;
import org.PUJ.Model.Producto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestionCliente {
    private Map<Long, Cliente> listaClientes = new HashMap<>();

    public Map<Long, Cliente> getListaClientes() {
        return listaClientes;
    }
    public Cliente existeCliente (Long cedula){
        if (listaClientes.containsKey(cedula)){
            return listaClientes.get(cedula);
        }
        return null;
    }

    public void modificarDatosCliente(long cedula){
        boolean clienteEncontrado=false;
        Cliente client=new Cliente();
        for(Cliente auxclient:listaClientes.values()){
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
}

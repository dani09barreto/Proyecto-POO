package org.PUJ.Controller;

import org.PUJ.Model.Cliente;

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
    public void VerlistadoClientes(){
        if(listaClientes.isEmpty()){
            System.out.println("\t[!] No existen clientes para mostrar \n");
            return;
        }
        for(Long l : listaClientes.keySet()){
            System.out.println(listaClientes.get(l).toString());
        }
    }
    public void InsertarCliente(Long ced, String nom, Long tel,String dir){
        Cliente cliNew = new Cliente(ced,nom,tel,dir);
        for(long l: listaClientes.keySet()){
            if(listaClientes.get(l).getCedula().equals(ced)){
                System.out.println("\t[!] El cliente insertado ya existe.");
                return;
            }
        }
        listaClientes.put(ced,cliNew);
    }
    public void EliminarCliente(Long cedEliminar){

            Long temp = cedEliminar;
            if (listaClientes.containsKey(cedEliminar)) {
                listaClientes.remove(cedEliminar);
                System.out.println("\t El cliente con cedula " + temp + " fue eliminado con exito!");

            } else {
                System.out.println("\t[!]No existe un cliente con dicha cedula. ");
            }
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

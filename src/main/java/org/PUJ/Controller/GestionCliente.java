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

    public Cliente existeCliente(Long cedula) {
        if (listaClientes.containsKey(cedula)) {
            return listaClientes.get(cedula);
        }
        return null;
    }

    public void VerlistadoClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("\t[!] No existen clientes para mostrar \n");
            return;
        }
        for (Long l : listaClientes.keySet()) {
            System.out.println(listaClientes.get(l).toString());
        }
    }

    public void InsertarCliente(Long ced, String nom, Long tel, String dir) {
        Cliente cliNew = new Cliente(ced, nom, tel, dir);
        for (long l : listaClientes.keySet()) {
            if (listaClientes.get(l).getCedula().equals(ced)) {
                System.out.println("\t[!] El cliente insertado ya existe.");
                return;
            }
        }
        listaClientes.put(ced, cliNew);
    }

    public void EliminarCliente(Long cedEliminar) {

        Long temp = cedEliminar;
        if (listaClientes.containsKey(cedEliminar)) {
            listaClientes.remove(cedEliminar);
        }
    }
}

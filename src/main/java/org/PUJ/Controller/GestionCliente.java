package org.PUJ.Controller;

import org.PUJ.Model.Cliente;

import java.util.HashMap;
import java.util.Map;


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
        if (listaClientes.containsKey(cedEliminar)) {
            listaClientes.remove(cedEliminar);
        }
    }
}

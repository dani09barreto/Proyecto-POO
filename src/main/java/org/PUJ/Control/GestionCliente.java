package org.PUJ.Control;

import org.PUJ.Model.Cliente;

import java.util.HashMap;
import java.util.Map;

public class GestionCliente {
    private Map<Long, Cliente> listaClientes = new HashMap<>();

    public Map<Long, Cliente> getListaClientes() {
        return listaClientes;
    }
}

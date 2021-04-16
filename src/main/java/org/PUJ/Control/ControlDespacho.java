package org.PUJ.Control;

import org.PUJ.Model.Cliente;
import org.PUJ.Model.Pedido;
import org.PUJ.Model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControlDespacho {
    private Map <UUID, Producto> listaProductos = new HashMap<>();
    private Map <Long, Cliente> listaClientes = new HashMap<>();
    private ArrayList <Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente = new GestionCliente();
    private GestionProductos gestionProductos = new GestionProductos();

    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }

    public Map<Long, Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public GestionCliente getGestionCliente() {
        return gestionCliente;
    }

    public GestionProductos getGestionProductos() {
        return gestionProductos;
    }
}

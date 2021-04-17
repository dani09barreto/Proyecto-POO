package org.PUJ.Control;

import org.PUJ.Model.Cliente;
import org.PUJ.Model.Pedido;
import org.PUJ.Model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControlDespacho {

    private GestionProductos gestionProductos = new GestionProductos();
    private ArrayList <Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente = new GestionCliente();

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

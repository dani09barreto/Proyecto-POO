package org.example.Control;

import org.example.Model.Pedido;

import java.util.ArrayList;

public class ControlDespacho {
    private GestionProductos gestionProductos;
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente;

    public GestionCliente getGestionCliente() {
        return gestionCliente;
    }

    public void setGestionCliente(GestionCliente gestionCliente) {
        this.gestionCliente = gestionCliente;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public GestionProductos getGestionProductos() {
        return gestionProductos;
    }

    public void setGestionProductos(GestionProductos gestionProductos) {
        this.gestionProductos = gestionProductos;
    }
}

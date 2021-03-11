package org.example.Control;

import org.example.Model.Pedido;
import org.example.Model.Producto;

import java.util.ArrayList;
import java.util.UUID;

public class ControlDespacho {

    private GestionProductos gestionProductos = new GestionProductos();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente = new GestionCliente();


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

    public boolean eliminarProducto(UUID codigoEliminar) {
        for (Producto p : this.getGestionProductos().getListaProductos()) {
            if (p.getProdId().equals(codigoEliminar)) {
                for (Pedido Pedi : this.getPedidos()) {
                    if (Pedi.getProductoSolicitado().getProdId().equals(codigoEliminar))
                        return false;
                    else {
                        gestionProductos.eliminarProduct(codigoEliminar);
                        return true;
                    }
                }

            } else {
                System.out.println("\tPRODUCTO NO ENCONTRADO ");
                return false;
            }
        }
        return false;
    }

}

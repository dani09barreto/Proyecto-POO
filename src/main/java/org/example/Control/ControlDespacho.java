package org.example.Control;

import org.example.Model.Cliente;
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

    public boolean Pedido_Producto(UUID codigoEliminar) {
        for(Pedido p: pedidos){
            if(p.getProductoSolicitado().getProdId().equals(codigoEliminar)){
                return true;
            }
        }
            return false;
    }
    public boolean Pedido_cliente(long identificacionEliminar) {
        for(Pedido p: pedidos){
            if(p.getSolicitante().getCedula() == identificacionEliminar){
                return true;
            }
        }
        return false;
    }
    public void VerPedido(){
        System.out.println(pedidos.toString());
    }

}

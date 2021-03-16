package org.example.Control;

import org.example.Model.Cliente;
import org.example.Model.Pedido;
import org.example.Model.Producto;


import javax.swing.*;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    public Pedido ExisteProducto (Cliente cliente, Producto producto, Calendar fecha){

        for (Pedido pedidotemp : this.pedidos){
            if (pedidotemp.getProductoSolicitado().equals(producto) && pedidotemp.getSolicitante().equals(cliente) && pedidotemp.getFechaRecibido().equals(fecha)){
                return pedidotemp;
            }
        }
        return null;

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
        if(pedidos.isEmpty())
            System.out.println("[!] NO EXISTEN PEDIDOS");
        else
        System.out.println(pedidos.toString());
    }

    public void ReservarPedido(int cedula, UUID idProducto){

        Scanner in = new Scanner(System.in);
        System.out.println("\tInformacion Pedido\n");
        System.out.println("\tDigite el nombre el repartidor");
        String repartidor = in.next();
        Cliente clientePedido = this.gestionCliente.existeCliente(cedula);
        Producto ProductoPedido = this.gestionProductos.existeProducto(idProducto);
        Calendar fechapedido = Calendar.getInstance();
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(Fecha.format(fechapedido.getTime()));

        if (ExisteProducto(clientePedido, ProductoPedido, fechapedido) == null){

            Pedido nuevopedido = new Pedido(fechapedido,repartidor,clientePedido,ProductoPedido);
            this.pedidos.add(nuevopedido);
        }
        else{
            System.out.println("El pedido ya existe en esta fecha");
            System.out.println("Desea crear el mismo pedido con una nueva fecha ?");
            System.out.println("S. si");
            System.out.println("N. NO");
            char opcion = in.next().charAt(0);

            if (opcion == 'S') {
                Pedido nuevopedido = new Pedido(Calendar.getInstance(), repartidor, clientePedido, ProductoPedido);
                this.pedidos.add(nuevopedido);
            }

        }
    }

    public ArrayList<Pedido> verListadoDePedidosDeProductoYFechaEspecífica(UUID prodId, Calendar fechaRecibido){
        ArrayList<Pedido> pedidosProductoFecha=new ArrayList<>();
        for(Pedido auxPedido: pedidos){
            if(auxPedido.getProductoSolicitado().getProdId().equals(prodId)&&auxPedido.getFechaRecibido().equals(fechaRecibido)){
                pedidosProductoFecha.add(auxPedido);
            }
        }
        return pedidosProductoFecha;
    }

}

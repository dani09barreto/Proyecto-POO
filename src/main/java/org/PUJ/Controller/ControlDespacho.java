package org.PUJ.Controller;

import org.PUJ.Model.*;
import org.PUJ.utils.Exceptions.FechaMenor;
import org.PUJ.utils.Exceptions.Fechaerror;
import org.PUJ.utils.Exceptions.PedidoFechaIgual;

import java.util.*;

public class  ControlDespacho {

    private GestionProductos gestionProductos = new GestionProductos();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
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

    public Pedido ExistePedido(Cliente cliente, Producto producto, Calendar fecha) {
        for (Pedido pedidotemp : this.pedidos) {
            if (pedidotemp.getProductoSolicitado().equals(producto) && pedidotemp.getSolicitante().equals(cliente) && pedidotemp.getFechaRecibido().equals(fecha)) {
                return pedidotemp;
            }
        }
        return null;
    }

    public Pedido ExistePedido(UUID id) {
        for (Pedido pedidotemp : this.pedidos) {
            System.out.println(id+pedidotemp.getNumeroPedido().toString());
            if (pedidotemp.getNumeroPedido().equals(id)) {
                return pedidotemp;
            }
        }
        return null;
    }

    public Pedido ReservarPedido(Pedido nuevopedido, ArrayList<ServicioAdicional> servicios) throws Fechaerror, PedidoFechaIgual, FechaMenor {
        nuevopedido.setServiciosAdicionales(servicios);
        Calendar fecha = nuevopedido.getFechaRecibido();
        //resta de la fecha actual con la fecha de despacho
        Calendar fechanow = Calendar.getInstance();
        long finMs = fechanow.getTimeInMillis();
        long inicioMs = fecha.getTimeInMillis();
        int dias = (int) (Math.abs(finMs - inicioMs) / (1000 * 60 * 60 * 24));

        if (fecha.before(fechanow)){
            throw new FechaMenor("fecha menor");
        }
        if (dias < 2){
            throw new Fechaerror("fecha error");
        }
        if (ExistePedido(nuevopedido.getSolicitante(), nuevopedido.getProductoSolicitado(), fecha) != null){
            throw new PedidoFechaIgual("Fecha igual");
        }
        for (ServicioAdicional serv: nuevopedido.getServiciosAdicionales()){
            if (serv instanceof BonoRegalo){
                Calendar fechaservicio = (Calendar) fecha.clone();
                fechaservicio.set(Calendar.MONTH, fechaservicio.get(Calendar.MONTH) + 6);
                ((BonoRegalo) serv).setFechaVencimiento(fechaservicio);
            }
        }
        pedidos.add(nuevopedido);
        return nuevopedido;
    }
    
    public boolean EliminarPedido(UUID eliminar) {
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido().equals(eliminar)) {
                pedidos.remove(p);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pedido> verListadoDePedidosDeProductoYFechaEspecífica(UUID idProd2, Calendar fecha) {
        boolean hayPedido = false;
        ArrayList<Pedido> pedidosProductoFecha = new ArrayList<>();
        for (Pedido auxPedido : pedidos) {
            if (auxPedido.getNumeroPedido().equals(idProd2) && (auxPedido.getFechaRecibido().compareTo(fecha)==0)){
                pedidosProductoFecha.add(auxPedido);
                hayPedido = true;
            }
        }
        if (!hayPedido) {
            return null;
        } else {
            System.out.println(pedidosProductoFecha.toString());
        }
        return pedidosProductoFecha;
    }


    public ArrayList<Pedido> PedidosDeAseoPorTipo (TipoProducto tipoABuscar) {
        ArrayList <Pedido> pedidosAsociados = new ArrayList<>();
        for (Pedido pedtemp : this.pedidos) {
            if (pedtemp.getProductoSolicitado() instanceof Aseo) {
                if (((Aseo) pedtemp.getProductoSolicitado()).getTipo() == tipoABuscar) {
                    pedidosAsociados.add(pedtemp);
                }
            }
        }
        return pedidosAsociados;
    }

    public boolean validarCliente(Long ced) {
        for (Pedido p : this.pedidos) {
            if (p.getSolicitante().getCedula().equals(ced))
                return true;
        }
        return false;
    }

    public boolean ValidarProducto(UUID productid) {
        for (Pedido ped : this.pedidos) {
            if (ped.getProductoSolicitado().getProdId().equals(productid))
                return true;
        }
        return false;
    }

    public Map<UUID, Producto> verProductosTipoFruver() {
        Map<UUID, Producto> productosFruver = new HashMap<>();
        for (Producto prod : this.getGestionProductos().getListaProductos().values()) {
            if (prod instanceof Fruver) {
                productosFruver.put(prod.getProdId(), prod);
            }
        }
        return productosFruver;
    }

    public ArrayList<Pedido> pedidoEnRangoDeFechas (Calendar fechaInicio, Calendar fechaFinal){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList <Calendar> fechas = new ArrayList<>();

        // diferencia de dias entre las dos fechas//
        long finMs = fechaInicio.getTimeInMillis();
        long inicioMs = fechaFinal.getTimeInMillis();
        int dias = (int) (Math.abs(finMs - inicioMs) / (1000 * 60 * 60 * 24));
        int contador = 0;
        while (contador <= dias){
            Calendar fechtemp = Calendar.getInstance();
            fechtemp = (Calendar) fechaInicio.clone();
            fechtemp.set(Calendar.DATE, fechtemp.get(Calendar.DATE)+ contador);
            fechas.add(fechtemp);
            contador ++;
        }

        for (Pedido pedido : this.pedidos){
            for (Calendar fechtemp : fechas){
                if (pedido.getFechaRecibido().equals(fechtemp)){
                    pedidos.add(pedido);
                }
            }
        }
        return pedidos;
    }
    public ArrayList <ServicioAdicional> enviosPrimePorTipo (TipoTransporte tipobuscar){
        ArrayList <ServicioAdicional> listtemp = new ArrayList<>();

        for (Pedido pedido : this.pedidos){
            for (ServicioAdicional servtemp : pedido.getServiciosAdicionales()){
                if (servtemp instanceof EnvioPrime){
                    if (((EnvioPrime) servtemp).getTipo() == tipobuscar){
                        listtemp.add(servtemp);
                    }
                }
            }
        }
        return listtemp;
    }
}

package org.PUJ.Controller;

import org.PUJ.Model.*;
import org.PUJ.utils.AlertUtils;
import org.PUJ.utils.Fechaerror;
import org.PUJ.utils.PedidoFechaIgual;

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
            if (pedidotemp.getNumeroPedido().equals(id)) {
                return pedidotemp;
            }
        }
        return null;
    }

    public void ReservarPedido(Producto producto, Cliente cliente, Calendar fecha, String repartidor, ArrayList<ServicioAdicional> servicios) throws Fechaerror, PedidoFechaIgual{
        Pedido nuevopedido = new Pedido(fecha, repartidor, cliente, producto);
        nuevopedido.setServiciosAdicionales(servicios);

        //resta de la fecha actual con la fecha de despacho
        Calendar fechanow = Calendar.getInstance();
        long finMs = fechanow.getTimeInMillis();
        long inicioMs = fecha.getTimeInMillis();
        int dias = (int) (Math.abs(finMs - inicioMs) / (1000 * 60 * 60 * 24));

        if (dias <= 2){
            throw new Fechaerror("fecha error");
        }
        if (ExistePedido(cliente, producto, fecha) != null){
            throw new PedidoFechaIgual("Fecha igual");
        }
        else {
            for (ServicioAdicional serv: nuevopedido.getServiciosAdicionales()){
                if (serv instanceof BonoRegalo){
                    Calendar fechaservicio = (Calendar) fecha.clone();
                    fechaservicio.set(Calendar.MONTH, fechaservicio.get(Calendar.MONTH) + 6);
                    ((BonoRegalo) serv).setFechaVencimiento(fechaservicio);
                }
            }
            long costoPedido = 0;
            long PrecioSA = 0;
            long ivaAdicional = 0;
            costoPedido += nuevopedido.getProductoSolicitado().calcularPrecio() + nuevopedido.getProductoSolicitado().getIva();
            for (ServicioAdicional servtemp : nuevopedido.getServiciosAdicionales()) {
                costoPedido += servtemp.calcularPrecio();
            }
            costoPedido += costoPedido * 0.10;

            if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
                costoPedido += 8000;
            }
            nuevopedido.setPagado(true);
            for (ServicioAdicional sev: nuevopedido.getServiciosAdicionales()){
                PrecioSA += sev.calcularPrecio();
            }
            if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
                ivaAdicional = 8000;
            }
            AlertUtils.alertInformation("Informacion Pedido",
                                        "Precio Producto: $" + nuevopedido.getProductoSolicitado().calcularPrecio()+"\n"+
                                                "Precio Iva producto: $"+ nuevopedido.getProductoSolicitado().getIva()+"\n"+
                                                "Precio Servicios Adicionales: $"+ PrecioSA +"\n"+
                                                "Precio Iva Adicional: $"+ ivaAdicional+"\n"+
                                                "Costo Despacho: $"+costoPedido*0.10+"\n"+
                                                "Costo Total: $"+ (costoPedido + costoPedido*0.10), "Pedido Almacenado");
            pedidos.add(nuevopedido);
        }
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
            if (auxPedido.getProductoSolicitado().getProdId().equals(idProd2) && ((fecha.before(auxPedido.getFechaRecibido()))||(fecha.compareTo(auxPedido.getFechaRecibido())==0))){
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

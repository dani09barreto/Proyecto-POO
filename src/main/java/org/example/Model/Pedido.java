package org.example.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Pedido {

    private UUID numPedido;
    private Calendar fechaRecibido;
    private boolean pagado;
    private String nombreRepartidor;
    private Cliente Solicitante;
    private ArrayList<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
    private Producto productoSolicitado;

    public Pedido(Calendar fechaRecibido, String nombreRepartidor, Cliente solicitante, Producto productoSolicitado,  ArrayList<ServicioAdicional> serviciosAdicionales) {
        this.numPedido = UUID.randomUUID();
        this.fechaRecibido = fechaRecibido;
        this.pagado = false;
        this.nombreRepartidor = nombreRepartidor;
        Solicitante = solicitante;
        this.serviciosAdicionales = serviciosAdicionales;
        this.productoSolicitado = productoSolicitado;
    }

    public Pedido() {
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    public ArrayList<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public UUID getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(UUID numPedido) {
        this.numPedido = numPedido;
    }

    public Calendar getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Calendar fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public Cliente getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(Cliente solicitante) {
        Solicitante = solicitante;
    }

    @Override
    public String toString() {

        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        return "\tNumero de Pedido: " + numPedido +
                "\n\tfechaRecibido: " + Fecha.format(this.fechaRecibido.getTime()) +
                "\n\tpagado: " + pagado +
                "\n\tnombreRepartidor: " + nombreRepartidor +
                "\n\tSolicitante: " + Solicitante +
                "\n\tserviciosAdicionales: " + serviciosAdicionales +
                "\n\tproductoSolicitado: " + productoSolicitado + "\n";
    }
}

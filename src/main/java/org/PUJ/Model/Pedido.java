package org.PUJ.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Pedido {
    private UUID numeroPedido;
    private Calendar fechaRecibido;
    private Boolean pagado;
    private String nombreRepartidor;
    private Cliente solicitante;
    private ArrayList <ServicioAdicional> serviciosAdicionales;
    private Producto productoSolicitado;

    public Pedido(Calendar fechaRecibido, String nombreRepartidor, Cliente solicitante, Producto productoSolicitado,  ArrayList<ServicioAdicional> serviciosAdicionales) {
        this.numeroPedido = UUID.randomUUID();
        this.fechaRecibido = fechaRecibido;
        this.pagado = false;
        this.solicitante = solicitante;
        this.nombreRepartidor = nombreRepartidor;
        this.serviciosAdicionales = serviciosAdicionales;
        this.productoSolicitado = productoSolicitado;
    }

    public UUID getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(UUID numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Calendar getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Calendar fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public Cliente getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Cliente solicitante) {
        this.solicitante = solicitante;
    }

    public ArrayList<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }
}

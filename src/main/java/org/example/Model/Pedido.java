package org.example.Model;

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
}

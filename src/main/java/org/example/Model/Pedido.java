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
        String informacion;
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        informacion =     "\tNumero de Pedido: \t\t" + numPedido +
                        "\n\tFecha Recibido: \t\t" + Fecha.format(this.fechaRecibido.getTime()) +
                        "\n\tPagado: \t\t\t\t";
        if (pagado) informacion += "Sí";
        else informacion += "No";
        informacion +=  "\n\tNombre del repartidor: \t" + nombreRepartidor +
                        "\n\tSolicitante: \t\t\t" + Solicitante.getNombreCompleto() + " (Cedula: " + Solicitante.getCedula() + ")";
        if (serviciosAdicionales.size() > 0){
            informacion +=  "\n\t- Servicios adicionales: \n";
            informacion += serviciosAdicionales.toString();
        }
        else informacion += "\n\t[!] El pedido no tiene servicios adicionales.";
        informacion +=  "\n\t[-] Producto solicitado: \n" + productoSolicitado + "\n";

        return informacion;
    }
}

package org.PUJ.Model;

import java.text.SimpleDateFormat;
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

    public Pedido(Calendar fechaRecibido, String nombreRepartidor, Cliente solicitante, Producto productoSolicitado) {
        this.numeroPedido = UUID.randomUUID();
        this.fechaRecibido = fechaRecibido;
        this.pagado = false;
        this.solicitante = solicitante;
        this.nombreRepartidor = nombreRepartidor;
        this.serviciosAdicionales = new ArrayList<>();
        this.productoSolicitado = productoSolicitado;
    }

    public UUID getNumeroPedido() {
        return numeroPedido;
    }

    public void setServiciosAdicionales(ArrayList<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
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

    @Override
    public String toString() {
        String informacion;
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        informacion =     "\n\tNumero de Pedido: \t\t" + numeroPedido +
                "\n\tFecha Recibido: \t\t" + Fecha.format(this.fechaRecibido.getTime()) +
                "\n\tPagado: \t\t\t\t";
        if (pagado) informacion += "Sí";
        else informacion += "No";
        informacion +=  "\n\tNombre del repartidor: \t" + nombreRepartidor +
                "\n\tSolicitante: \t\t\t" + solicitante.getNombreCompleto() + " (Cedula: " + solicitante.getCedula() + ")";
        if(serviciosAdicionales!=null) {
            if (serviciosAdicionales.size() > 0) {
                informacion += "\n\t Servicios adicionales: \n";
                informacion += serviciosAdicionales.toString();
            } else {
                informacion += "\n\t[!] El pedido no tiene servicios adicionales.";
                informacion += "\n\t[-] Producto solicitado: \n" + productoSolicitado + "\n";
            }
        }else{
            informacion += "\n\t[!] El pedido no tiene servicios adicionales.";
            informacion += "\n\t[-] Producto solicitado: \n" + productoSolicitado + "\n";
        }
        return informacion;
    }
    public ArrayList <ServicioAdicional> enviosPrimePorTipo (TipoTransporte tipobuscar){
        ArrayList <ServicioAdicional> listtemp = new ArrayList<>();
        for (ServicioAdicional servtemp : serviciosAdicionales){
            if (servtemp instanceof EnvioPrime){
                if (((EnvioPrime) servtemp).getTipo() == tipobuscar){
                    listtemp.add(servtemp);
                }
            }
        }
        return listtemp;
    }
}

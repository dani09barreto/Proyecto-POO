package org.PUJ.Model;


import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.UUID;

@XmlRootElement
public abstract class ServicioAdicional {
    protected UUID codigoServicio;
    protected String nombreServicio;
    protected Double precio;

    public ServicioAdicional(String nombreServicio, Double precio) {
        this.codigoServicio = UUID.randomUUID();
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }
    public ServicioAdicional (){

    }

    public UUID getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(UUID codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public abstract Double calcularPrecio ();

    @Override
    public String toString() {
        return "\n\tServicio Adicional: " + nombreServicio +
                "\n\tCodigo:" + codigoServicio +
                "\n\tPrecio: $" + precio;
    }
}

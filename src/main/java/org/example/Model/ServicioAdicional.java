package org.example.Model;

public class ServicioAdicional {
    private long codigoServicio;
    private String nombreServicio;
    private double precio;

    public ServicioAdicional(long codigoServicio, String nombreServicio, double precio) {
        this.codigoServicio = codigoServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public ServicioAdicional() {

    }

    public long getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(long codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\tCodigo Servicio:" + codigoServicio +
                "\n\tNombre del servicio:" + nombreServicio +
                "\n\tPrecio: " + precio;
    }
}

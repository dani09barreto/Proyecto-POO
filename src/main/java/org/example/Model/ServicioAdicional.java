package org.example.Model;

public class ServicioAdicional {
    private long codigoServicio;
    private String nombreServicio;
    private double precio;

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
}

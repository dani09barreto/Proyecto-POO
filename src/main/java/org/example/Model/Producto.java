package org.example.Model;

import java.util.UUID;

public class Producto {
    private UUID prodId;
    private String nombreComercial;
    private double precio;
    private double iva;
    private String tienda;

    public Producto(){}

    public Producto(UUID prodId, String nombreComercial, double precio, double iva, String tienda) {
        this.prodId = prodId;
        this.nombreComercial = nombreComercial;
        this.precio = precio;
        this.iva = iva;
        this.tienda = tienda;
    }

    public UUID getProdId() {
        return prodId;
    }

    public void setProdId(UUID prodId) {
        this.prodId = prodId;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    @Override
    public String toString() {
        return        "\tID: " + prodId +
                    "\n\tNombre: " + nombreComercial +
                    "\n\tPrecio: $" + precio +
                    "\n\tIVA: $" + iva +
                    "\n\tTienda: " + tienda + "\n";
    }
}

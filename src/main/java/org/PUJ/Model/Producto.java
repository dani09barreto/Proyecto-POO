package org.PUJ.Model;

import java.util.UUID;

public class Producto {
    protected UUID prodId;
    protected String nombreComercial;
    protected Double precio;
    protected Double iva;
    protected String tienda;

    public Producto(String nombreComercial, Double precio, String tienda) {
        this.prodId = UUID.randomUUID();
        this.nombreComercial = nombreComercial;
        this.iva = precio*0.19;
        this.precio = precio;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getIva() {
        return iva;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public Double CalcularPrecio(){
        return precio + iva;
    }
}

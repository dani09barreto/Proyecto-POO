package org.PUJ.Model;

import java.util.UUID;

public class Fruver extends Producto {
    private Boolean esOrganico;
    private Double impuestoLocal;
    private String nombreHacienda;

    public Fruver(String nombreComercial, Double precio, String tienda, Double impuestoLocal, String nombreHacienda) {
        super(nombreComercial, precio, tienda);
        this.esOrganico = true;
        this.impuestoLocal = impuestoLocal;
        this.nombreHacienda = nombreHacienda;
    }

    public Boolean getEsOrganico() {
        return esOrganico;
    }

    public void setEsOrganico(Boolean esOrganico) {
        this.esOrganico = esOrganico;
    }

    public Double getImpuestoLocal() {
        return impuestoLocal;
    }

    public void setImpuestoLocal(Double impuestoLocal) {
        this.impuestoLocal = impuestoLocal;
    }

    public String getNombreHacienda() {
        return nombreHacienda;
    }

    public void setNombreHacienda(String nombreHacienda) {
        this.nombreHacienda = nombreHacienda;
    }

    public Double calcularPrecio() {
        return this.esOrganico ? (super.precio+this.impuestoLocal + super.iva) * 1.2d : super.precio + this.impuestoLocal + super.iva;
    }

    @Override
    public String toString() {
        String organico = this.esOrganico ? "Si" : "No";
        return super.toString() +
                "\n\tEs orgánico: " + organico +
                "\n\tImpuesto local: $" + impuestoLocal +
                "\n\tNombre Hacienda: " + nombreHacienda + "\n";
    }
}

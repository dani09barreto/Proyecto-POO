package org.PUJ.Model;

import java.util.UUID;

public class Fruver extends Producto{
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

    public Double CalcularPrecio(){
        return precio + iva;
    }

}
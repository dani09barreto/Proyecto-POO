package org.PUJ.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EnvioPrime extends ServicioAdicional {
    private Double distancia;
    private TipoTransporte tipo;
    private Integer numeroCajas;

    public EnvioPrime(String nombreServicio, Double precio, Double distancia, TipoTransporte tipo, Integer numeroCajas){
        super(nombreServicio, precio);
        this.distancia = distancia;
        this.tipo = tipo;
        this.numeroCajas = numeroCajas;
        this.precio = calcularPrecio();
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    public Integer getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(Integer numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    @Override
    public Double calcularPrecio() {
        if (this.tipo == TipoTransporte.BICICLETA){
            return super.precio + this.numeroCajas*1200;
        }
        if (this.tipo == TipoTransporte.MOTO){
            Double recargo = (this.precio + this.numeroCajas*1200)*0.1;
            return this.precio + this.numeroCajas*1200 + recargo;
        }
        if (this.tipo == TipoTransporte.MINIVAN){
            Double recargo = (this.precio + this.numeroCajas*1200)*0.25;
            return this.precio + this.numeroCajas*1200 + recargo;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\tDistancia:" + distancia +
                "\n\tTipo: " + tipo +
                "\n\tNumero de cajas: " + numeroCajas + "\n";
    }
}

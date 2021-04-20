package org.PUJ.Model;

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
            return this.precio*0.1 + this.numeroCajas*1200;
        }
        if (this.tipo == TipoTransporte.MINIVAN){
            return this.precio*0.25 + this.numeroCajas*1200;
        }
        return 0.0;
    }
}

package org.PUJ.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.UUID;

@XmlRootElement
public class Aseo extends Producto {
    private String nombreEmpresa;
    private Boolean tieneInvima;
    private TipoProducto tipo;

    public Aseo(){}

    public Aseo(UUID ID, String nombreComercial, Double precio, String tienda, String nombreEmpresa ,TipoProducto tipo, Boolean tieneInvima) {
        super(ID, nombreComercial, precio, tienda);
        this.nombreEmpresa = nombreEmpresa;
        this.tieneInvima = tieneInvima;
        this.tipo = tipo;
    }

    public Aseo(String nombreComercial, Double precio, String tienda, String nombreEmpresa ,TipoProducto tipo, Boolean tieneInvima) {
        super(nombreComercial, precio, tienda);
        this.nombreEmpresa = nombreEmpresa;
        this.tieneInvima = tieneInvima;
        this.tipo = tipo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Boolean getTieneInvima() {
        return tieneInvima;
    }

    public void setTieneInvima(Boolean tieneInvima) {
        this.tieneInvima = tieneInvima;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Double calcularPrecio (){
        if (this.tipo == TipoProducto.HOGAR || this.tipo == TipoProducto.INDUSTRIAL) {
            return (super.precio + super.iva)*0.9d;
        } else {
            return (super.precio + super.iva)*0.7d;
        }
    }

    @Override
    public String toString() {
        String invima = this.tieneInvima ? "Sí tiene" : "No tiene";
         return super.toString() +
                "\nNombre Empresa: " + nombreEmpresa +
                "\nInvima: " + invima +
                "\nTipo: " + tipo + "\n";
    }
}

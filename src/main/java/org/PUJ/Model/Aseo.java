package org.PUJ.Model;

import java.util.UUID;

public class Aseo extends Producto{
    private String nombreEmpresa;
    private Boolean tieneInvima;
    private TipoProducto tipo;

    public Aseo(String nombreComercial, Double precio, String tienda) {
        super(nombreComercial, precio, tienda);
    }

    public Double calcularPrecio (){
        return 0.0;
    }
}

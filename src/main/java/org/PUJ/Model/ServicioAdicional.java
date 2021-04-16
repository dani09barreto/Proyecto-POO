package org.PUJ.Model;

import java.util.UUID;

public abstract class ServicioAdicional {
    protected UUID codigoServicio;
    protected String nombreServicio;
    protected Double precio;

    public ServicioAdicional(String nombreServicio, Double precio) {
        this.codigoServicio = UUID.randomUUID();
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public abstract Double calcularPrecio ();
}

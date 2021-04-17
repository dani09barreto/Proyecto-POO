package org.PUJ.Control;

import org.PUJ.Model.Producto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GestionProductos {
    private Map<UUID, Producto> listaProductos = new HashMap<>();

    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }
}

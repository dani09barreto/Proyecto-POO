package org.PUJ.Controller;


import org.PUJ.Model.Producto;


import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class GestionProductos {
    private Map<UUID, Producto> listaProductos = new HashMap<>();

    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }
    public Producto existeProducto (UUID codigo){
        if (listaProductos.containsKey(codigo)){
            return listaProductos.get(codigo);
        }
        return null;
    }

    public void insertarProducto(Producto nuevoProducto) {
        if (!listaProductos.containsKey(nuevoProducto.getProdId())) {
            listaProductos.put(nuevoProducto.getProdId(), nuevoProducto);
        }
    }

    public void eliminarProducto(UUID nKey) {
        if (listaProductos.containsKey(nKey)) {
            listaProductos.remove(nKey);
        }
    }
}

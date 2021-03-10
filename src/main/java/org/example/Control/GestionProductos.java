package org.example.Control;

import org.example.Model.Producto;

import java.util.ArrayList;
import java.util.UUID;

public class GestionProductos {

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public ArrayList<Producto> VerListadoDeProductos (){

        ArrayList<Producto> listatemp = new ArrayList<>();
        listatemp.addAll(listaProductos);
        return listatemp;
    }
}

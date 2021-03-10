package org.example.View;

import org.example.Control.ControlDespacho;

public class PantallaDespacho {

    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {

        PantallaDespacho Pantalla = new PantallaDespacho();
        int opcion = 1;
        System.out.println("hola mkos");
        System.out.println("prueba branch");
        System.out.println(" MENU ");
        System.out.println("1. inserte producto");
        System.out.println("2. poner precio");

        switch (opcion) {
            case 1:
                Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos();
                System.out.println("Lista de productos: \n");
                System.out.println(Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().toString());
                break;
        }
    }
}

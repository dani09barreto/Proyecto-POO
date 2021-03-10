package org.example.View;

import org.example.Control.ControlDespacho;

import java.util.Scanner;

public class PantallaDespacho {

    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {

        PantallaDespacho Pantalla = new PantallaDespacho();
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("\n______Oficina de una agencia de reparto de Productos______\n");
        System.out.println("1.  Ver Lista de Productos");
        System.out.println("2.  Insertar Productos");
        System.out.println("3.  Modificar Productos");
        System.out.println("4.  Eliminar Productos");
        System.out.println("5.  Ver listado de Clientes registrados en el sistema");
        System.out.println("6.  Insertar Cliente");
        System.out.println("7.  Modificar datos de Cliente");
        System.out.println("8.  Eliminar un Cliente");
        System.out.println("9.  Realizar el Pedido de un Producto");
        System.out.println("10. Modificar un Pedido de un Producto");
        System.out.println("11. Eliminar un Pedido de un Producto");
        System.out.println("12. Ver listado de Pedidos existentes");
        System.out.println("13. Ver listado de Pedidos existentes de Producto y fecha específica");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
        in.nextInt();
        switch (opcion) {
            case 1:
                Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos();
                System.out.println("Lista de productos: \n");
                System.out.println(Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().toString());
                break;
        }
    }
}

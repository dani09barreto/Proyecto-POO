package org.example.View;

import org.example.Control.ControlDespacho;
import org.example.Model.Producto;

import java.util.Scanner;
import java.util.UUID;

public class PantallaDespacho {

    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {

        PantallaDespacho Pantalla = new PantallaDespacho();
        int opcion = 0;
        Scanner in = new Scanner(System.in);

        //Producto para pruebas
        //Producto prod1=new Producto(UUID.randomUUID(),"Papas",25000,1200,"Jumbo");
        //Pantalla.centralDespacho.getGestionProductos().getListaProductos().add(prod1);

        do {
            System.out.println("\n\t______Oficina de una agencia de reparto de Productos______\n");
            System.out.println("\t1.  Ver Lista de Productos");
            System.out.println("\t2.  Insertar Productos");
            System.out.println("\t3.  Modificar Productos");
            System.out.println("\t4.  Eliminar Productos");
            System.out.println("\t5.  Ver listado de Clientes registrados en el sistema");
            System.out.println("\t6.  Insertar Cliente");
            System.out.println("\t7.  Modificar datos de Cliente");
            System.out.println("\t8.  Eliminar un Cliente");
            System.out.println("\t9.  Realizar el Pedido de un Producto");
            System.out.println("\t10. Modificar un Pedido de un Producto");
            System.out.println("\t11. Eliminar un Pedido de un Producto");
            System.out.println("\t12. Ver listado de Pedidos existentes");
            System.out.println("\t13. Ver listado de Pedidos existentes de Producto y fecha específica");
            System.out.println("\t0. Salir");
            System.out.print("\tOpcion: ");
            opcion = in.nextInt();

            switch (opcion) {
                
                case 1:

                    if(Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().size() != 0){

                        System.out.println("\n\tLista de productos: \n");
                        Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos();
                        System.out.println(Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().toString());
                    }
                    else
                        System.out.println("\tNo hay productos disponibles para mostrar");
                    break;

                case 3:
                    System.out.println("Digite el ID del producto que desea modificar");
                    UUID productId;
                    productId= UUID.fromString(in.next());
                    Pantalla.centralDespacho.getGestionProductos().modificarProducto(productId);
                    break;
            }
        } while (opcion != 0);
    }
}

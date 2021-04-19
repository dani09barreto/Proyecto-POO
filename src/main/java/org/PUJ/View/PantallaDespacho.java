package org.PUJ.View;

import org.PUJ.Control.ControlDespacho;
import java.util.Scanner;
import java.util.UUID;

public class PantallaDespacho {
    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main( String[] args ) {
        PantallaDespacho pantalla = new PantallaDespacho();
        int opcion = 0;
        Scanner in = new Scanner(System.in);

        do{
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

            switch (opcion){
                case 9:
                    System.out.println("\n\tInserte el codigo del producto");
                    UUID idProd = UUID.fromString(in.next());
                    System.out.println("\tInserte el documento del cliente");
                    Long cedulaCliente = in.nextLong();

                    if (pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) != null && pantalla.centralDespacho.getGestionProductos().existeProducto(idProd) != null){
                        pantalla.centralDespacho.ReservarPedido(cedulaCliente,idProd);
                    }
                    else{
                        if(pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) == null)
                            System.out.println("\t[!] El cliente no existe");
                        if (pantalla.centralDespacho.getGestionProductos().existeProducto(idProd) == null)
                            System.out.println("\t[!] El Producto no existe");
                    }
                    break;
            }
        }while (opcion != 0);
        System.out.println("fin");
    }
    public ControlDespacho getCentralDespacho() {
        return centralDespacho;
    }
}

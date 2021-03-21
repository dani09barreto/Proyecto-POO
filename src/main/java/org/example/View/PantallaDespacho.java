package org.example.View;

import org.example.Control.ControlDespacho;
import org.example.Model.Cliente;
import org.example.Model.Pedido;
import org.example.Model.Producto;

import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;

public class PantallaDespacho {

    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {

        PantallaDespacho Pantalla = new PantallaDespacho();
        int opcion = 0;
        Scanner in = new Scanner(System.in);
        //OBJETOS DE PRUEBA
        Producto pd1 = new Producto(UUID.randomUUID(), "papa", 2000, 200, "casa");
        Pantalla.centralDespacho.getGestionProductos().getListaProductos().add(pd1);
        Cliente cl1 = new Cliente(1010,"daniel",121212,"calle 6c");
        Pantalla.centralDespacho.getGestionCliente().getListaClientes().add(cl1);
        Producto pd2 = new Producto(UUID.randomUUID(), "Hamburguesa", 20000, 2000, "El Corral");
        Pantalla.centralDespacho.getGestionProductos().getListaProductos().add(pd2);
        Cliente cl2 = new Cliente(2020,"Paco",312563,"Carrera 23");
        Pantalla.centralDespacho.getGestionCliente().getListaClientes().add(cl2);
        Calendar fecha1= Calendar.getInstance();
        fecha1.set(2021,Calendar.FEBRUARY,11);
        Pedido ped1= new Pedido(fecha1,"Juan",cl1,pd1,null);
        Pantalla.centralDespacho.getPedidos().add(ped1);
        Calendar fecha2=Calendar.getInstance();
        fecha2.set(2021,Calendar.JANUARY,15);
        Pedido ped2= new Pedido(fecha2,"Pepito",cl2,pd2,null);
        Pantalla.centralDespacho.getPedidos().add(ped2);
        //FIN OBJETOS DE PRUEBA

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

                    if (Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().size() != 0) {
                        System.out.println("\n\tLista de productos: \n");
                        Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos();
                        System.out.println(Pantalla.centralDespacho.getGestionProductos().VerListadoDeProductos().toString());
                    } else
                        System.out.println("\tNo hay productos disponibles para mostrar");
                    break;

                case 2:

                    Producto nuevoProd = new Producto();
                    System.out.println();
                    System.out.println("\n\tInsertar un producto: ");
                    System.out.println("\tInserte el nombre del producto: ");
                    nuevoProd.setNombreComercial(in.next());
                    System.out.println("\tInserte el precio del producto con IVA: ");
                    nuevoProd.setPrecio(in.nextInt());
                    System.out.println("\tInserte la tienda del producto: ");
                    nuevoProd.setTienda(in.next());
                    nuevoProd.setProdId(UUID.randomUUID());
                    Pantalla.centralDespacho.getGestionProductos().insertarProductos(nuevoProd);
                    break;

                case 3:

                    System.out.println("\n\tDigite el ID del producto que desea modificar: ");
                    UUID productId;
                    productId = UUID.fromString(in.next());
                    Pantalla.centralDespacho.getGestionProductos().modificarProducto(productId);
                    break;

                case 4:

                    System.out.println("\n\tDigite el Id del producto que desea eliminar: ");
                    UUID deleteCode;
                    deleteCode = UUID.fromString(in.next());

                    if (Pantalla.centralDespacho.getGestionProductos().existeProducto(deleteCode) == null){
                        System.out.println("\tProducto no puede ser eliminado por que no existe");
                    }
                    else{
                        if (Pantalla.centralDespacho.Pedido_Producto(deleteCode)) {
                            System.out.println("\tEL PRODUCTO PERTENCE A UN ENVIO");
                        }
                        else {
                            Pantalla.centralDespacho.getGestionProductos().eliminarProduct(deleteCode);
                        }
                    }
                    break;

                case 5:
                    Pantalla.centralDespacho.getGestionCliente().verListadoClientes();
                    break;
                case 6:
                    Cliente nuevoCliente = new Cliente();
                    System.out.println("\n\tDigite los datos del Nuevo Cliente: ");
                    System.out.println("\tNombre Completo: ");
                    nuevoCliente.setNombreCompleto(in.next());
                    System.out.println("\tTelefono: ");
                    nuevoCliente.setTelefonoContacto(in.nextLong());
                    System.out.println("\tCedula: ");
                    nuevoCliente.setCedula(in.nextLong());
                    System.out.println("\tDireccion: ");
                    nuevoCliente.setDireccion(in.next());
                    Pantalla.centralDespacho.getGestionCliente().insertarCliente(nuevoCliente);
                    break;
                case 7:
                    System.out.println("\n\tDigite la Identificacion del Cliente que desea modificar: ");
                    long cedula;
                    cedula = in.nextLong();
                    Pantalla.centralDespacho.getGestionCliente().modificarDatosCliente(cedula);
                    break;
                case 8:
                    System.out.println(cl1.getCedula());
                    System.out.println("\n\tDigite la Identificacion del Cliente que desea eliminar: ");
                    long deleteIdentification;
                    deleteIdentification = in.nextLong();

                    if (Pantalla.centralDespacho.getGestionCliente().existeCliente(deleteIdentification) == null) {
                        System.out.println("\tCliente no puede ser eliminado por que no existe");
                    }
                    else {

                        if (Pantalla.centralDespacho.Pedido_cliente(deleteIdentification)) {
                            System.out.println("\tEL CLIENTE PERTENCE A UN PEDIDO!");
                        }
                        else {
                            Pantalla.centralDespacho.getGestionCliente().eliminarCliente(deleteIdentification);
                        }
                    }
                    break;
                case 9:

                    System.out.println("\n\tInserte el codigo del producto");
                    UUID idProd1 = UUID.fromString(in.next());
                    System.out.println("\tInserte el documento del cliente");
                    int cedulaCliente = in.nextInt();

                    if (Pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) != null && Pantalla.centralDespacho.getGestionProductos().existeProducto(idProd1) != null){
                        Pantalla.centralDespacho.ReservarPedido(cedulaCliente,idProd1);
                    }
                    else{
                        if(Pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) == null)
                            System.out.println("\t[!] El cliente no existe");
                        if (Pantalla.centralDespacho.getGestionProductos().existeProducto(idProd1) == null)
                            System.out.println("\t[!] El Producto no existe");
                    }
                    break;
                case 10:
                    Pantalla.centralDespacho.modificarPedido();
                    break;
                case 11:
                    System.out.println("\tEliminar Pedido");
                    System.out.print("\tDigite el numero del pedido que desea eliminar: ");
                    UUID EliminarPed;
                    EliminarPed = UUID.fromString(in.next());
                    Pantalla.centralDespacho.EliminarPedido(EliminarPed);
                case 12:
                    Pantalla.centralDespacho.VerPedido();
                    break;
                case 13:
                    Pantalla.centralDespacho.verListadoDePedidosDeProductoYFechaEspecífica();
                    break;
                case 0:
                    System.out.println("\tGracias por usar nuestro sistema");
                    break;
                default:
                    System.out.println("Digite una opción válida y vuelva a intentarlo");
                    break;
            }
        } while (opcion != 0);
    }
}

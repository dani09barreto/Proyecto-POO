package org.PUJ.View;

import org.PUJ.Control.ControlDespacho;
import org.PUJ.Model.Pedido;

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

                case 3:
                    System.out.println("\n\tDigite el ID del producto que desea modificar: ");
                    UUID productId=UUID.fromString(in.next());
                    pantalla.centralDespacho.getGestionProductos().modificarProducto(productId);
                    break;
                case 5:
                    pantalla.centralDespacho.getGestionCliente().VerlistadoClientes();
                    break;
                case 6:
                    Boolean ver = false;
                    System.out.println("\tInsertar Cliente: ");
                    System.out.print("\tIngrese el Nombre completo del nuevo cliente: ");
                    in.nextLine();
                    String nombre = in.nextLine();
                    System.out.print("\tIngrese la cedula de "+nombre+" :");
                    Long ced=0l;
                    do{
                        try{
                            ced=in.nextLong();
                            ver = true;
                        }
                        catch (Exception e){
                            in.next();
                            System.out.println("\t[!] Ingrese un valor numerico.");

                        }
                    }while(!ver);
                    System.out.print("\tIngrese el telefono de contacto de "+nombre+" :");
                    Long tel=0l;
                    ver = false;
                    do{
                        try{
                            tel=in.nextLong();
                            ver = true;
                        }
                        catch (Exception e){
                            in.next();
                            System.out.println("\t[!] Ingrese un valor numerico.");

                        }
                    }while(!ver);
                    in.nextLine();
                    System.out.print("\tIngrese la direccion de "+nombre+" :");
                    String dir= in.nextLine();
                    pantalla.centralDespacho.getGestionCliente().InsertarCliente(ced,nombre,tel,dir);
                    break;
                case 7:
                    System.out.println("\n\tDigite la Identificacion del Cliente que desea modificar: ");
                    long cedula;
                    cedula = in.nextLong();
                    pantalla.centralDespacho.getGestionCliente().modificarDatosCliente(cedula);
                    break;
                case 8:
                    System.out.println("\tIngrese el numero de cedula del cliente que deseas eliminar.");
                    Long cedulaE=0l;
                    ver=false;
                    do{
                        try{
                            cedulaE = in.nextLong();
                            ver = true;

                        }catch (Exception e){
                            in.next();
                            System.out.println("\t[!]Ingrese un valor numerico.");

                        }

                    }while(!ver);
                    if(pantalla.Validarcliente(cedulaE))
                    pantalla.centralDespacho.getGestionCliente().EliminarCliente(cedulaE);
                    else System.out.println("\t[!]El cliente esta asociado a un Pedido");
                    break;
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
                case 13:
                    pantalla.centralDespacho.verListadoDePedidosDeProductoYFechaEspecífica();
                    break;
                default:
                    System.out.println("Digite una opción válida y vuelva a intentarlo");
                    break;
            }
        }while (opcion != 0);
        System.out.println("fin");
    }
    public ControlDespacho getCentralDespacho() {
        return centralDespacho;
    }

    public Boolean Validarcliente(Long cedula){
        for(Pedido p:this.centralDespacho.getPedidos()){
            if(p.getSolicitante().getCedula().equals(cedula))
                return false;
        }
        return true;
    }
}

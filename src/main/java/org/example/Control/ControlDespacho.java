package org.example.Control;

import org.example.Model.Cliente;
import org.example.Model.Pedido;
import org.example.Model.Producto;
import org.example.Model.ServicioAdicional;


import javax.swing.*;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlDespacho {

    private GestionProductos gestionProductos = new GestionProductos();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente = new GestionCliente();


    public GestionCliente getGestionCliente() {
        return gestionCliente;
    }

    public void setGestionCliente(GestionCliente gestionCliente) {
        this.gestionCliente = gestionCliente;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public GestionProductos getGestionProductos() {
        return gestionProductos;
    }

    public void setGestionProductos(GestionProductos gestionProductos) {
        this.gestionProductos = gestionProductos;
    }


    public Pedido ExisteProducto (Cliente cliente, Producto producto, Calendar fecha){

        for (Pedido pedidotemp : this.pedidos){
            if (pedidotemp.getProductoSolicitado().equals(producto) && pedidotemp.getSolicitante().equals(cliente) && pedidotemp.getFechaRecibido().equals(fecha)){
                return pedidotemp;
            }
        }
        return null;

    }
    public boolean Pedido_Producto(UUID codigoEliminar) {
        for(Pedido p: pedidos){
            if(p.getProductoSolicitado().getProdId().equals(codigoEliminar)){
                return true;
            }
        }
            return false;
    }
    public boolean Pedido_cliente(long identificacionEliminar) {
        for(Pedido p: pedidos){
            if(p.getSolicitante().getCedula() == identificacionEliminar){
                return true;
            }
        }
        return false;
    }
    public void VerPedido(){
        if(pedidos.isEmpty())
            System.out.println("[!] NO EXISTEN PEDIDOS");
        else
        System.out.println(pedidos.toString());
    }

    public void ReservarPedido(int cedula, UUID idProducto){

        Scanner in = new Scanner(System.in);
        System.out.println("\tInformacion Pedido\n");
        System.out.println("\tDigite el nombre el repartidor");
        String repartidor = in.next();
        Cliente clientePedido = this.gestionCliente.existeCliente(cedula);
        Producto ProductoPedido = this.gestionProductos.existeProducto(idProducto);
        Calendar fechapedido = Calendar.getInstance();
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(Fecha.format(fechapedido.getTime()));

        if (ExisteProducto(clientePedido, ProductoPedido, fechapedido) == null){

            Pedido nuevopedido = new Pedido(fechapedido,repartidor,clientePedido,ProductoPedido);
            this.pedidos.add(nuevopedido);
        }
        else{
            System.out.println("El pedido ya existe en esta fecha");
            System.out.println("Desea crear el mismo pedido con una nueva fecha ?");
            System.out.println("S. si");
            System.out.println("N. NO");
            char opcion = in.next().charAt(0);

            if (opcion == 'S') {
                Pedido nuevopedido = new Pedido(Calendar.getInstance(), repartidor, clientePedido, ProductoPedido);
                this.pedidos.add(nuevopedido);
            }

        }
    }

    public void modificarPedido(){
        Pedido pedidoModificar = new Pedido();
        Calendar nuevaFecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        ServicioAdicional nuevoServicio = new ServicioAdicional();
        boolean encontrado = false, modificarFecha = true;
        UUID numeroPedido;
        int opt, opt2, nuevoPrecio = 0, anio, mes, dia;
        Scanner in = new Scanner (System.in);
        System.out.println("\t- Modificar un pedido.");
        System.out.print("\tIngrese el número de pedido: ");
        numeroPedido = UUID.fromString(in.nextLine());

        for (Pedido ped : pedidos){
            if (ped.getNumPedido().equals(numeroPedido)){
                pedidoModificar = ped;
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("\t[!] No se encontró ningún pedido con el número " + numeroPedido + ".");
            return;
        }
        System.out.println("\t[-] Modificar pedido [" + numeroPedido + "]");
        System.out.println("\t 1] Cambiar fecha del pedido (" +
                pedidoModificar.getFechaRecibido().get(Calendar.YEAR) + "/" +
                pedidoModificar.getFechaRecibido().get(Calendar.MONTH) + "/" +
                pedidoModificar.getFechaRecibido().get(Calendar.DATE) + ")");
        System.out.println("\t 2] Cambiar nombre del repartidor (" + pedidoModificar.getNombreRepartidor() + ")");
        System.out.println("\t 3] Cambiar servicios adicionales seleccionados.");
        System.out.print("Ingrese una opción: ");
        switch (in.nextInt()){
            case 1:
                System.out.print("\t - Escriba la nueva fecha para el pedido");
                System.out.print("\tAño: ");
                anio = in.nextInt();
                System.out.print("\tMes (ej: 05): ");
                mes = in.nextInt();
                System.out.print("\tDia: ");
                dia = in.nextInt();
                nuevaFecha.set(anio, mes-1, dia);

                for (Pedido ped : pedidos) {
                    if (ped.getSolicitante().equals(pedidoModificar.getSolicitante()) && ped.getFechaRecibido().equals(nuevaFecha)){
                        // Arreglar el .equals
                        System.out.println("[!] Ya tiene un pedido para esta fecha.");
                        modificarFecha = false;
                        break;
                    }
                }
                if (modificarFecha){
                    pedidoModificar.setFechaRecibido(nuevaFecha);
                    System.out.println("\tFecha modificada.");
                }
                break;
            case 2:
                in.nextLine();
                System.out.print("\t - Escriba el nombre del nuevo repartidor: ");
                pedidoModificar.setNombreRepartidor(in.nextLine());
                break;
            case 3:
                System.out.println("\t[1] Eliminar o modificar un servicio solicitado para el pedido. Hay " + pedidoModificar.getServiciosAdicionales().size() + " servicios para este pedido.");
                System.out.println("\t[2] Agregar un nuevo servicio al pedido.");
                System.out.print("\t - Seleccione la opción que desea realizar: ");
                switch (in.nextInt()){
                    case 1:
                        if (pedidoModificar.getServiciosAdicionales().size() > 0){
                            for (int i = 0; i < pedidoModificar.getServiciosAdicionales().size(); ++i){
                                System.out.println("\tServicio " + i + ": " + pedidoModificar.getServiciosAdicionales().get(i).getNombreServicio());
                            }
                            System.out.print("\t- Ingrese el número del servicio que desea modificar: ");
                            opt = in.nextInt();
                            if (opt > pedidoModificar.getServiciosAdicionales().size()){
                                System.out.println("\t[!] Opción no válida.");
                            }
                            else {
                                System.out.println("\tServicio " + opt + ": " + pedidoModificar.getServiciosAdicionales().get(opt).getNombreServicio());
                                System.out.println("\t[1] Modificar servicio - [2] Eliminar servicio");
                                switch (in.nextInt()){
                                    case 1:
                                        System.out.println("\t- [1] Modificar nombre (" + pedidoModificar.getServiciosAdicionales().get(opt).getNombreServicio());
                                        System.out.println("\t- [2] Modificar precio (" + pedidoModificar.getServiciosAdicionales().get(opt).getPrecio());
                                        switch (in.nextInt()){
                                            case 1:
                                                System.out.print("\t- Ingrese el nuevo nombre del servicio: ");
                                                in.nextLine();
                                                pedidoModificar.getServiciosAdicionales().get(opt).setNombreServicio(in.nextLine());
                                                System.out.println("[!] Nombre actualizado: " + pedidoModificar.getServiciosAdicionales().get(opt).getNombreServicio());
                                                break;
                                            case 2:
                                                System.out.println("\t- Ingrese el nuevo precio del servicio: ");
                                                pedidoModificar.getServiciosAdicionales().get(opt).setPrecio(in.nextDouble());
                                                System.out.println("[!] Precio actualizado: " + pedidoModificar.getServiciosAdicionales().get(opt).getPrecio());
                                                break;
                                        }
                                        break;
                                    case 2:
                                        pedidoModificar.getServiciosAdicionales().remove(pedidoModificar.getServiciosAdicionales().get(opt));
                                        System.out.println("\t[!] Servicio eliminado.");
                                        break;
                                    default:
                                        System.out.println("\t[!] Opción no válida.");
                                        break;
                                }
                            }
                        }
                        else System.out.println("\t[!] No hay servicios adicionales para este pedido.");
                        break;
                    case 2:
                        System.out.print("\t- Ingrese el código del servicio que desea agregar: ");
                        nuevoServicio.setCodigoServicio(in.nextLong());
                        in.nextLine();
                        System.out.print("\t- Ingrese el nombre del servicio que desea agregar: ");
                        nuevoServicio.setNombreServicio(in.nextLine());
                        System.out.print("\t- Ingrese el precio del servicio: ");
                        nuevoServicio.setPrecio(in.nextDouble());
                        pedidoModificar.getServiciosAdicionales().add(nuevoServicio);
                        System.out.println("\t[!] Servicio agregado correctamente.");
                        // Calculando el nuevo precio del producto:
                        System.out.println("\t[$] Precio del pedido.");
                        System.out.println("\t    $" + pedidoModificar.getProductoSolicitado().getPrecio() + " -> " + pedidoModificar.getProductoSolicitado().getNombreComercial());
                        nuevoPrecio += pedidoModificar.getProductoSolicitado().getPrecio(); // Incluye IVA.
                        for (ServicioAdicional serv : pedidoModificar.getServiciosAdicionales()){
                            System.out.println("\t    $" + serv.getPrecio() + " -> " + serv.getNombreServicio() + "(Servicio adicional)");
                            nuevoPrecio += serv.getPrecio();
                        }
                        System.out.println("\t    $" + pedidoModificar.getProductoSolicitado().getPrecio()*0.1 + " -> Costo del despacho");
                        nuevoPrecio += pedidoModificar.getProductoSolicitado().getPrecio()*0.1;
                        if (pedidoModificar.getProductoSolicitado().getIva() > 50000){
                            System.out.println("\t    $8000 -> Adicional por impuesto");
                            nuevoPrecio += 8000;
                        }
                        System.out.println("\t[$] TOTAL: $" + nuevoPrecio);
                        break;
                    default:
                        System.out.println("\t[!] Opción no válida.");
                        break;
                }
                break;
            case 0:
                break;
            default:
                System.out.println("\t[!] Opción no válida.");
                break;
        }

    }

    public void EliminarPedido(UUID eliminar){
        boolean ver = true;
        char Confi = 'j';
        Scanner c = new Scanner(System.in);
        for(Pedido p: pedidos){
            if(p.getNumPedido() == eliminar){
                ver = false;
                System.out.println("Esta seguro que desea elimianr el pedido : "+p.toString());
                System.out.println("S = si, N = no");
                Confi = c.next().charAt(0);
                if(Confi == 'S'){
                    pedidos.remove(p);
                    System.out.println("\tPEDIDO ELIMINADO CON EXITO");
                }
                break;

            }
        }
        if(ver && Confi!='N')
            System.out.println("\t[!] EL PEDIDO QUE DESEA ELIMINAR NO EXISTE ");
    }

    public ArrayList<Pedido> verListadoDePedidosDeProductoYFechaEspecífica(UUID prodId, Calendar fechaRecibido){
        ArrayList<Pedido> pedidosProductoFecha=new ArrayList<>();
        for(Pedido auxPedido: pedidos){
            if(auxPedido.getProductoSolicitado().getProdId().equals(prodId)&&auxPedido.getFechaRecibido().equals(fechaRecibido)){
                pedidosProductoFecha.add(auxPedido);
            }
        }
        return pedidosProductoFecha;
    }
}

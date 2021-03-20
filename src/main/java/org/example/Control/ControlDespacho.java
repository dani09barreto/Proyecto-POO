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
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\t______Informacion Pedido________\n");
        System.out.println("\tDigite el nombre el repartidor");
        String repartidor = in.next();
        System.out.println("\tDigite la fecha en la cual desea despachar el pedido en formato dd/MM/yyyy");
        String fechain = in.next();
        Cliente clientePedido = this.gestionCliente.existeCliente(cedula);
        Producto ProductoPedido = this.gestionProductos.existeProducto(idProducto);
        Calendar fechaDespacho = Calendar.getInstance();

        try {
            Date fechaDate = Fecha.parse(fechain);
            fechaDespacho.setTime(fechaDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //resta de la fecha actual con la fecha de despacho

        Calendar fechanow = Calendar.getInstance();
        long finMs = fechanow.getTimeInMillis();
        long inicioMs = fechaDespacho.getTimeInMillis();
        int dias = (int) (Math.abs(finMs-inicioMs)/ (1000*60*60*24));

        System.out.println(dias);
        if (dias < 2){
            System.out.println("\t [!] Ups.. el pedido lo debes hacer 2 dias antes de la fecha de entrega");
        }
        else{
            if (ExisteProducto(clientePedido, ProductoPedido, fechaDespacho) == null){
                char opcion;
                ArrayList<ServicioAdicional> servicios = new ArrayList<>();
                do{
                    System.out.println("\tDesea incluir servicion adicionales ?");
                    System.out.println("\tS. si");
                    System.out.println("\tN. NO");
                    opcion = in.next().charAt(0);

                    if (opcion == 'S'){
                        long idproducto;
                        String nombre;
                        long precio;
                        System.out.println("\tIngrese el id del servicio");
                        idproducto = in.nextLong();
                        System.out.println("\tIngrese el nombre del servicio");
                        nombre = in.next();
                        System.out.println("\tIngrese el precio del servicio");
                        precio = in.nextLong();
                        ServicioAdicional serviciotemp = new ServicioAdicional(idproducto, nombre, precio);
                        servicios.add(serviciotemp);

                    }
                }while (opcion != 'N');
                Pedido nuevopedido;

                if (servicios.size() != 0){
                    nuevopedido = new Pedido(fechaDespacho, repartidor, clientePedido, ProductoPedido, servicios);
                    long costoPedido = 0;
                    costoPedido += ProductoPedido.getPrecio() + ProductoPedido.getIva();

                    for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                        costoPedido += servtemp.getPrecio();
                    }
                    costoPedido += costoPedido * 0.10;

                    if (ProductoPedido.getIva() > 50000){
                        costoPedido += 8000;
                    }
                    nuevopedido.setPagado(true);
                    System.out.println("\n\t___Costo de Pedido___\n");
                    System.out.println("\tPrecio Producto: $" + ProductoPedido.getPrecio());
                    System.out.println("\tPrecio Iva producto: $" + ProductoPedido.getIva());
                    for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                        System.out.println("\tPrecio servicio adicional: $" + servtemp.getPrecio());
                    }
                    System.out.println("\tCosto de despacho: $"+ costoPedido*0.10);
                    System.out.println("\tCosto total: $" + costoPedido);
                }
                else{
                    nuevopedido = new Pedido(fechaDespacho, repartidor, clientePedido, ProductoPedido, null);
                    long costoPedido = 0;
                    costoPedido += ProductoPedido.getPrecio() + ProductoPedido.getIva();

                    for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                        costoPedido += servtemp.getPrecio();
                    }
                    costoPedido += costoPedido * 0.10;

                    if (ProductoPedido.getIva() > 50000){
                        costoPedido += 8000;
                    }
                    nuevopedido.setPagado(true);
                    System.out.println("\n\t___Costo de Pedido___\n");
                    System.out.println("\tPrecio Producto: $" + ProductoPedido.getPrecio());
                    System.out.println("\tPrecio Iva producto: $" + ProductoPedido.getIva());
                    for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                        System.out.println("\tPrecio servicio adicional: $" + servtemp.getPrecio());
                    }
                    System.out.println("\tCosto de despacho: $"+ costoPedido*0.10);
                    System.out.println("\tCosto total: $" + costoPedido);
                }
                this.pedidos.add(nuevopedido);
            }
            else{

                System.out.println("\t [!] El pedido ya existe en esta fecha");
                System.out.println("\tDesea crear el mismo pedido con una nueva fecha ?");
                System.out.println("\tS. si");
                System.out.println("\tN. NO");
                char opcion = in.next().charAt(0);

                if (opcion == 'S') {
                    System.out.println("\tDigite la fecha en la cual desea despachar el pedido en formato dd/MM/yyyy");
                    fechain = in.next();
                    try {
                        Date fechaDate = Fecha.parse(fechain);
                        fechaDespacho.setTime(fechaDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (dias < 2){
                        System.out.println("\t [!] Ups.. el pedido lo debes hacer 2 dias antes de la fecha de entrega");
                    }
                    else{
                        ArrayList<ServicioAdicional> servicios = new ArrayList<>();
                        do{
                            System.out.println("\tDesea incluir servicion adicionales ?");
                            System.out.println("\tS. si");
                            System.out.println("\tN. NO");
                            opcion = in.next().charAt(0);

                            if (opcion == 'S'){
                                long idproducto;
                                String nombre;
                                long precio;
                                System.out.println("\tIngrese el id del servicio");
                                idproducto = in.nextLong();
                                System.out.println("\tIngrese el nombre del servicio");
                                nombre = in.next();
                                System.out.println("\tIngrese el precio del servicio");
                                precio = in.nextLong();
                                ServicioAdicional serviciotemp = new ServicioAdicional(idproducto, nombre, precio);
                                servicios.add(serviciotemp);

                            }
                        }while (opcion != 'N');

                        Pedido nuevopedido;
                        if (servicios.size() != 0){
                            nuevopedido = new Pedido(fechaDespacho, repartidor, clientePedido, ProductoPedido, servicios);
                            long costoPedido = 0;
                            costoPedido += ProductoPedido.getPrecio() + ProductoPedido.getIva();

                            for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                                costoPedido += servtemp.getPrecio();
                            }
                            costoPedido += costoPedido * 0.10;

                            if (ProductoPedido.getIva() > 50000){
                                costoPedido += 8000;
                            }
                            nuevopedido.setPagado(true);
                            System.out.println("\n\t___Costo de Pedido___\n");
                            System.out.println("\tPrecio Producto: $" + ProductoPedido.getPrecio());
                            System.out.println("\tPrecio Iva producto: $" + ProductoPedido.getIva());
                            for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                                System.out.println("\tPrecio servicio adicional: $" + servtemp.getPrecio());
                            }
                            System.out.println("\tCosto de despacho: $"+ costoPedido*0.10);
                            System.out.println("\tCosto total: $" + costoPedido);
                        }
                        else{ 
                            nuevopedido = new Pedido(fechaDespacho, repartidor, clientePedido, ProductoPedido, null);
                            long costoPedido = 0;
                            costoPedido += ProductoPedido.getPrecio() + ProductoPedido.getIva();

                            for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                                costoPedido += servtemp.getPrecio();
                            }
                            costoPedido += costoPedido * 0.10;

                            if (ProductoPedido.getIva() > 50000){
                                costoPedido += 8000;
                            }
                            nuevopedido.setPagado(true);
                            System.out.println("\n\t___Costo de Pedido___\n");
                            System.out.println("\tPrecio Producto: $" + ProductoPedido.getPrecio());
                            System.out.println("\tPrecio Iva producto: $" + ProductoPedido.getIva());
                            for (ServicioAdicional servtemp: nuevopedido.getServiciosAdicionales()){
                                System.out.println("\tPrecio servicio adicional: $" + servtemp.getPrecio());
                            }
                            System.out.println("\tCosto de despacho: $"+ costoPedido*0.10);
                            System.out.println("\tCosto total: $" + costoPedido);
                        }
                        this.pedidos.add(nuevopedido);
                    }
                }
            }
        }
    }

    public void modificarPedido(){
        Scanner in = new Scanner (System.in);
        Pedido pedidoModificar = new Pedido();
        Calendar nuevaFecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ServicioAdicional nuevoServicio = new ServicioAdicional();
        boolean encontrado = false, modificarFecha = true;
        UUID numeroPedido;
        int opt, opt2, nuevoPrecio = 0, anio, mes, dia;
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
                System.out.print("\t - Escriba la nueva fecha para el pedido (dd/MM/yyyy)");
                try {
                    nuevaFecha.setTime(formato.parse(in.nextLine()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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

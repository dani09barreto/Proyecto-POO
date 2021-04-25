package org.PUJ.Control;

import org.PUJ.Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlDespacho {

    private GestionProductos gestionProductos = new GestionProductos();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private GestionCliente gestionCliente = new GestionCliente();

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public GestionCliente getGestionCliente() {
        return gestionCliente;
    }

    public GestionProductos getGestionProductos() {
        return gestionProductos;
    }

    public Pedido ExistePedido(Cliente cliente, Producto producto, Calendar fecha) {
        for (Pedido pedidotemp : this.pedidos) {
            if (pedidotemp.getProductoSolicitado().equals(producto) && pedidotemp.getSolicitante().equals(cliente) && pedidotemp.getFechaRecibido().equals(fecha)) {
                return pedidotemp;
            }
        }
        return null;
    }

    public Pedido ExistePedido(UUID id) {
        for (Pedido pedidotemp : this.pedidos) {
            if (pedidotemp.getNumeroPedido().equals(id)){
                return pedidotemp;
            }
        }
        return null;
    }

    public void ReservarPedido(Pedido nuevopedido) {

        long costoPedido = 0;
        costoPedido += nuevopedido.getProductoSolicitado().calcularPrecio() + nuevopedido.getProductoSolicitado().getIva();

        for (ServicioAdicional servtemp : nuevopedido.getServiciosAdicionales()) {
            costoPedido += servtemp.calcularPrecio();
        }
        costoPedido += costoPedido * 0.10;

        if (nuevopedido.getProductoSolicitado().getIva() > 50000) {
            costoPedido += 8000;
        }
        nuevopedido.setPagado(true);
        System.out.println("\n\t___Costo de Pedido___\n");
        System.out.println("\tPrecio Producto: $" + nuevopedido.getProductoSolicitado().calcularPrecio());
        System.out.println("\tPrecio Iva producto: $" + nuevopedido.getProductoSolicitado().getIva());
        for (ServicioAdicional servtemp : nuevopedido.getServiciosAdicionales()) {
            System.out.println("\tServicio adicional: " + servtemp.getNombreServicio());
            System.out.println("\tPrecio servicio adicional: $" + servtemp.calcularPrecio());
        }
        if (nuevopedido.getProductoSolicitado().getIva() > 50000) {
            System.out.println("\tPrecio Iva Adicional: $ 8000");
        }
        System.out.println("\tCosto de despacho: $" + costoPedido * 0.10);
        System.out.println("\tCosto total: $" + costoPedido);
    }

    public void ModificarPedido(UUID id) {
        Pedido pedidoModificar = ExistePedido(id);
        Scanner in = new Scanner(System.in);
        System.out.println("\tSeleccione el aspecto a modificar del pedido");
        System.out.println("\t1. Fecha de pedido");
        System.out.println("\t2. Pago de pedido");
        System.out.println("\t3. Nombre del repartidor");
        int opcion = in.nextInt();
        switch (opcion) {
            case 1:
                SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("\tDigite la  nueva fecha en la cual desea despachar el pedido en formato dd/MM/yyyy");
                String fechain = in.next();
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
                int dias = (int) (Math.abs(finMs - inicioMs) / (1000 * 60 * 60 * 24));

                System.out.println(dias);
                if (dias <= 2) {
                    System.out.println("\t [!] Ups.. el pedido lo debes hacer 2 dias antes de la fecha de entrega \n\t No se ha modificado tu pedido, vuelve a intentarlo");
                } else {
                    if (ExistePedido(pedidoModificar.getSolicitante(), pedidoModificar.getProductoSolicitado(), fechaDespacho) == null) {
                        pedidoModificar.setFechaRecibido(fechaDespacho);
                    }
                    else {
                        System.out.println("\t [!] El pedido ya existe en esta fecha");
                        System.out.println("\tDesea crear el mismo pedido con una nueva fecha ?");
                        System.out.println("\tS. si");
                        System.out.println("\tN. NO");
                        char op = in.next().charAt(0);

                        if (op == 'S') {
                            System.out.println("\tDigite la fecha en la cual desea despachar el pedido en formato dd/MM/yyyy");
                            fechain = in.next();
                            try {
                                Date fechaDate = Fecha.parse(fechain);
                                fechaDespacho.setTime(fechaDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (dias <= 2) {
                                System.out.println("\t [!] Ups.. el pedido lo debes hacer 2 dias antes de la fecha de entrega");
                            }
                            else{
                                if (ExistePedido(pedidoModificar.getSolicitante(), pedidoModificar.getProductoSolicitado(), fechaDespacho) == null) {
                                    pedidoModificar.setFechaRecibido(fechaDespacho);
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                if (pedidoModificar.getPagado()) {
                    System.out.println("\t Desea cambiar que no este pago ?");
                    System.out.println("\t1. Si");
                    System.out.println("\t1. NO");
                    char op = in.next().charAt(0);
                    if (op == 's') {
                        pedidoModificar.setPagado(false);
                    }
                } else {
                    System.out.println("\t Desea cambiar que este pago ?");
                    System.out.println("\t1. Si");
                    System.out.println("\t1. NO");
                    char op = in.next().charAt(0);
                    if (op == 's') {
                        pedidoModificar.setPagado(true);
                    }
                }
                break;
            case 3:
                System.out.println("\tDigite el  nuevo nombre el repartidor");
                String repartidor = in.next();
                pedidoModificar.setNombreRepartidor(repartidor);
                break;
        }
    }

    public void EliminarPedido(UUID eliminar){
        boolean ver = true;
        char Confi = 'j';
        Scanner c = new Scanner(System.in);
        for(Pedido p: pedidos){
            if(p.getNumeroPedido().equals(eliminar)){
                ver = false;
                System.out.println("Esta seguro que desea eliminar el pedido? : "+p.toString());
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

    public void VerPedido(){
        if(pedidos.isEmpty())
            System.out.println("[!] NO EXISTEN PEDIDOS");
        else
            System.out.println(pedidos.toString());
    }

    public boolean verListadoDePedidosDeProductoYFechaEspecífica() {
        boolean hayPedido = false;
        System.out.println("\tInserte el codigo del producto");
        Scanner in = new Scanner(System.in);
        UUID idProd2 = UUID.fromString(in.next());
        if (getGestionProductos().existeProducto(idProd2) == null) {
            System.out.println("\t[!] El producto no existe");
        } else {
            System.out.println("\tInserte el año de la fecha específica (AAAA)");
            int anio = in.nextInt();
            System.out.println("\tInserte el mes de la fecha específica (MM)");
            int mes = in.nextInt();
            System.out.println("\tInserte el dia de la fecha específica (DD)");
            int dia = in.nextInt();
            Calendar fecha = Calendar.getInstance();
            fecha.set(anio, mes - 1, dia);
            ArrayList<Pedido> pedidosProductoFecha = new ArrayList<>();
            for (Pedido auxPedido : pedidos) {
                if (auxPedido.getProductoSolicitado().getProdId().equals(idProd2) && fecha.after(auxPedido.getFechaRecibido())) {
                    pedidosProductoFecha.add(auxPedido);
                    hayPedido = true;
                }
            }
            if (!hayPedido) {
                System.out.println("\tNo se encontraron pedidos para el producto especificado posteriores la fecha especificada");
            } else {
                System.out.println(pedidosProductoFecha.toString());
            }
        }
        return hayPedido;
    }

    public double precioPedidosDeAseoPorTipo(TipoProducto tipoABuscar){
        Double precio = 0.0;
        for (Pedido pedtemp : this.pedidos){
            if (pedtemp.getProductoSolicitado() instanceof Aseo){
                if (((Aseo) pedtemp.getProductoSolicitado()).getTipo() == tipoABuscar){
                    precio += ((Aseo) pedtemp.getProductoSolicitado()).calcularPrecio();
                }
            }
        }
        return precio;
    }
    public ArrayList <ServicioAdicional> VerEnvioPrimeConTipoEnPedido (UUID idpedido, TipoTransporte tipo){
        Pedido pedidover = this.ExistePedido(idpedido);
        ArrayList<ServicioAdicional> listatemp = pedidover.enviosPrimePorTipo(tipo);
        return listatemp;
    }
    public boolean validarCliente(Long ced){
        for(Pedido p: this.pedidos){
            if(p.getSolicitante().getCedula().equals(ced))
                return false;
        }
        return true;
    }
    public boolean ValidarProducto(Producto product){
        for(Pedido ped: this.pedidos){
            if(ped.getProductoSolicitado().equals(product))
                return true;
        }
        return false;
    }

    public Map<UUID,Producto> verProductosTipoFruver() {
        Map<UUID, Producto> productosFruver = new HashMap<>();
        for (Producto prod : this.getGestionProductos().getListaProductos().values()) {
            if (prod instanceof Fruver) {
                productosFruver.put(prod.getProdId(), prod);
            }
        }
        return productosFruver;
    }

    public ArrayList<Pedido> verPedidosAsociadosAProductosAseo(){
        ArrayList<Pedido> pedidosProductosAseo=new ArrayList<>();
        for(Pedido ped: this.getPedidos()){
            if(ped.getProductoSolicitado() instanceof Aseo){
                pedidosProductosAseo.add(ped);
            }
        }
        return pedidosProductosAseo;
    }

}

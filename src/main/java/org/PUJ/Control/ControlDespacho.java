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
            if (pedidotemp.getNumeroPedido().equals(id)) {
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

        if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
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
        if (nuevopedido.getProductoSolicitado().getIva() > 50000d) {
            System.out.println("\tPrecio Iva Adicional: $ 8000");
        }
        System.out.println("\tCosto de despacho: $" + costoPedido * 0.10);
        System.out.println("\tCosto total: $" + costoPedido);
        pedidos.add(nuevopedido);
    }

    public void ModificarPedido(UUID id) {
        Pedido pedidoModificar = ExistePedido(id);
        Scanner in = new Scanner(System.in);
        System.out.println("\tSeleccione el aspecto a modificar del pedido");
        System.out.println("\t1. Fecha de pedido");
        System.out.println("\t2. Pago de pedido");
        System.out.println("\t3. Nombre del repartidor");
        System.out.println("\t4. Servicios adicionales");
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
                    while (ExistePedido(pedidoModificar.getSolicitante(), pedidoModificar.getProductoSolicitado(), fechaDespacho) != null) {
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
                                break;
                            }
                        }
                    }
                    pedidoModificar.setFechaRecibido(fechaDespacho);
                    for (ServicioAdicional servver : pedidoModificar.getServiciosAdicionales()) {
                        if (servver instanceof BonoRegalo) {
                            Calendar fechaVencimiento = (Calendar) fechaDespacho.clone();
                            fechaVencimiento.set(Calendar.MONTH, fechaVencimiento.get(Calendar.MONTH) + 6);
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
            case 4:
                int conta = 0;
                System.out.println("\tTenemos los siguientes servicios adicionales para el pedido id:" + pedidoModificar.getNumeroPedido());
                for (ServicioAdicional servver : pedidoModificar.getServiciosAdicionales()) {
                    conta++;
                    System.out.println(conta + "." + servver.getNombreServicio());
                }
                System.out.println("\tInserte el numero del servicio adicional");
                int opc = in.nextInt();
                opc --;
                if (pedidoModificar.getServiciosAdicionales().get(opc) instanceof BonoRegalo) {
                    System.out.println("\tSeleccione el aspecto a modificar del servicio adicional");
                    System.out.println("\t1. Precio");
                    System.out.println("\t2. Comercio asociado");
                    System.out.println("\t3. Mensaje");
                    int srv = in.nextInt();
                    switch (srv) {
                        case 1:
                            System.out.println("\tIngrese el nuevo precio");
                            pedidoModificar.getServiciosAdicionales().get(opc).setPrecio(in.nextDouble());
                            break;
                        case 2:
                            System.out.println("\tIngrese el nuevo Comercio asociado");
                            ((BonoRegalo) pedidoModificar.getServiciosAdicionales().get(opc)).setComercioAsociado(leerString());
                            break;
                        case 3:
                            System.out.println("\tIngrese el nuevo mensaje");
                            ((BonoRegalo) pedidoModificar.getServiciosAdicionales().get(opc)).setMensaje(leerString());
                            break;
                    }
                    System.out.println("\tServicio modificado Correctamente");
                }
                if (pedidoModificar.getServiciosAdicionales().get(opc) instanceof EnvioPrime) {
                    System.out.println("\tSeleccione el aspecto a modificar del servicio adicional");
                    System.out.println("\t1. Precio");
                    System.out.println("\t2. Distancia");
                    System.out.println("\t3. Tipo de transporte");
                    System.out.println("\t4. numero de cajas");
                    int srv = in.nextInt();
                    switch (srv) {
                        case 1:
                            pedidoModificar.getServiciosAdicionales().get(opc).setPrecio(in.nextDouble());
                            break;
                        case 2:
                            ((EnvioPrime) pedidoModificar.getServiciosAdicionales().get(opc)).setDistancia(in.nextDouble());
                            break;
                        case 3:
                            System.out.println("\tTenemos varios tipos de transporte elige el que mas prefieras");
                            System.out.println("\t1. Bicicleta");
                            System.out.println("\t2. Moto");
                            System.out.println("\t3. Minivan");
                            int op = in.nextInt();
                            TipoTransporte tipo;
                            switch (op) {
                                case 1:
                                    tipo = TipoTransporte.BICICLETA;
                                    break;
                                case 2:
                                    tipo = TipoTransporte.MOTO;
                                    break;
                                case 3:
                                    tipo = TipoTransporte.MINIVAN;
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + op);
                            }
                            ((EnvioPrime) pedidoModificar.getServiciosAdicionales().get(opc)).setTipo(tipo);
                            break;
                        case 4:
                            ((EnvioPrime) pedidoModificar.getServiciosAdicionales().get(opc)).setNumeroCajas(in.nextInt());
                            break;
                    }
                    System.out.println("\tServicio modificado Correctamente");
                }
                break;
        }
        System.out.println("\tPedido modificado Correctamente");
    }

    public void EliminarPedido(UUID eliminar) {
        boolean ver = true;
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido().equals(eliminar)) {
                ver = false;
                pedidos.remove(p);
                break;
            }
        }
        if (ver)

            System.out.println("\t[!] El pedido que desea eliminar, no existe.");
    }

    public void VerPedido() {
        if (pedidos.isEmpty())
            System.out.println("[!] No existen pedidos.");
        else
            System.out.println(pedidos.toString());
    }

    public boolean verListadoDePedidosDeProductoYFechaEspecífica(UUID idProd2, Calendar fecha) {
        boolean hayPedido = false;
        ArrayList<Pedido> pedidosProductoFecha = new ArrayList<>();
        for (Pedido auxPedido : pedidos) {
            if (auxPedido.getProductoSolicitado().getProdId().equals(idProd2) && ((fecha.before(auxPedido.getFechaRecibido()))||(fecha.compareTo(auxPedido.getFechaRecibido())==0))){
                pedidosProductoFecha.add(auxPedido);
                hayPedido = true;
            }
        }
        if (!hayPedido) {
            System.out.println("\tNo se encontraron pedidos para el producto especificado posteriores la fecha especificada");
        } else {
            System.out.println(pedidosProductoFecha.toString());
        }
        return hayPedido;
    }


    public Double precioPedidosDeAseoPorTipo(TipoProducto tipoABuscar) {
        Double precio = 0.0;
        for (Pedido pedtemp : this.pedidos) {
            if (pedtemp.getProductoSolicitado() instanceof Aseo) {
                if (((Aseo) pedtemp.getProductoSolicitado()).getTipo() == tipoABuscar) {
                    precio += ((Aseo) pedtemp.getProductoSolicitado()).calcularPrecio();
                }
            }
        }
        return precio;
    }

    public boolean validarCliente(Long ced) {
        for (Pedido p : this.pedidos) {
            if (p.getSolicitante().getCedula().equals(ced))
                return false;
        }
        return true;
    }

    public boolean ValidarProducto(Producto product) {
        for (Pedido ped : this.pedidos) {
            if (ped.getProductoSolicitado().equals(product))
                return true;
        }
        return false;
    }

    public Map<UUID, Producto> verProductosTipoFruver() {
        Map<UUID, Producto> productosFruver = new HashMap<>();
        for (Producto prod : this.getGestionProductos().getListaProductos().values()) {
            if (prod instanceof Fruver) {
                productosFruver.put(prod.getProdId(), prod);
            }
        }
        return productosFruver;
    }

    public ArrayList<Pedido> verPedidosAsociadosAProductosAseo() {
        ArrayList<Pedido> pedidosProductosAseo = new ArrayList<>();
        for (Pedido ped : this.getPedidos()) {
            if (ped.getProductoSolicitado() instanceof Aseo) {
                pedidosProductosAseo.add(ped);
            }
        }
        return pedidosProductosAseo;
    }
    public String leerString() {
        String str = "";
        Scanner sc = new Scanner(System.in);
        do {
            str = sc.nextLine();
        } while (str.isBlank());
        return str;
    }
}

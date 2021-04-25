package org.PUJ.View;

import org.PUJ.Control.ControlDespacho;
import org.PUJ.Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PantallaDespacho {
    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {
        PantallaDespacho pantalla = new PantallaDespacho();
        int opcion = 0;
        Scanner in = new Scanner(System.in);

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
            System.out.println("\t14. Ver listado de envios prime y tipo de un pedido");
            System.out.println("\t15. Ver listado de productos de tipo Fruver");
            System.out.println("\t16. Ver listado de pedidos asociados a productos de aseo");
            System.out.println("\t17. Calcular precio de pedidos de tipo aseo en un producto en especifico");
            System.out.println("\t0. Salir");
            System.out.print("\tOpcion: ");
            opcion = in.nextInt();
            boolean valido = false;

            switch (opcion) {
                case 1:
                    pantalla.centralDespacho.getGestionProductos().verListadoDeProductos();
                    break;
                case 2:
                    System.out.println("\t\t[!] Insertar un producto");
                    System.out.println("\t -> ¿Qué tipo de producto es?");
                    System.out.println("\t [1] Aseo\t[2] Fruver\t[3] Otro");
                    Integer tipoProducto = pantalla.leerEntero();
                    System.out.print("\t-> Ingrese el nombre del producto: ");
                    String nombreProducto = pantalla.leerString();
                    System.out.print("\t-> Ingrese el precio del producto: ");
                    Double precioProducto = pantalla.leerDouble();
                    System.out.print("\t-> Ingrese el nombre de la tienda: ");
                    String nombreTienda = pantalla.leerString();
                    switch (tipoProducto) {
                        case 1: // Aseo
                            System.out.println("\t\t---- Datos de producto de Aseo ----");
                            System.out.print("\t-> Ingrese el nombre de la empresa del producto: ");
                            String nombreEmpresa = pantalla.leerString();
                            System.out.print("\t-> ¿El producto tiene invima? S/N: ");
                            String tieneInv = "";
                            Boolean tieneInvima = false;
                            do {
                                tieneInv = in.next();
                                if ((tieneInv).toUpperCase().equals("S")) {
                                    tieneInvima = true;
                                    valido = true;
                                } else if ((tieneInv).toUpperCase().equals("N")) {
                                    valido = true;
                                }
                            } while (!valido);
                            System.out.println("\t-> ¿Qué tipo de producto de aseo es?");
                            System.out.println("\t-> [1] Hogar \t[2] Industrial \t[3] Hospitalario");
                            Integer tipoProductoAseoSc = pantalla.leerEntero();
                            TipoProducto tipoProductoAseo = null;
                            switch (tipoProductoAseoSc) {
                                case 1:
                                    tipoProductoAseo = TipoProducto.HOGAR;
                                    break;
                                case 2:
                                    tipoProductoAseo = TipoProducto.INDUSTRIAL;
                                    break;
                                case 3:
                                    tipoProductoAseo = TipoProducto.HOSPITALARIO;
                                    break;
                            }
                            Aseo nuevoProductoAseo = new Aseo(nombreProducto, precioProducto, nombreTienda, nombreEmpresa, tipoProductoAseo, tieneInvima);
                            pantalla.centralDespacho.getGestionProductos().insertarProducto(nuevoProductoAseo);
                            break;
                        case 2: // Fruver
                            System.out.println("\t\t---- Datos de producto de Fruver ----");
                            System.out.print("\t-> Ingrese el impuesto local del producto: ");
                            Double impuestoLocal = pantalla.leerDouble();
                            System.out.print("\t-> Ingrese el nombre de la hacienda del producto: ");
                            String nombreHacienda = pantalla.leerString();
                            Fruver nuevoProductoFruver = new Fruver(nombreProducto, precioProducto, nombreTienda, impuestoLocal, nombreHacienda);
                            pantalla.centralDespacho.getGestionProductos().insertarProducto(nuevoProductoFruver);
                            break;
                        case 3: // Otro
                            Producto nuevoProducto = new Producto(nombreProducto, precioProducto, nombreTienda);
                            pantalla.centralDespacho.getGestionProductos().insertarProducto(nuevoProducto);
                            break;
                    }
                    break;

                case 3:
                    System.out.println("\n\tDigite el ID del producto que desea modificar: ");
                    UUID productId = UUID.fromString(in.next());
                    pantalla.centralDespacho.getGestionProductos().modificarProducto(productId);
                    break;
                case 4:
                    System.out.println("\t\t[!] Eliminar un producto.");
                    System.out.print("\t-> Digite el ID del producto que desea eliminar: ");
                    in.nextLine();
                    UUID keyEliminar = null;
                    valido = false;
                    if (!pantalla.centralDespacho.ValidarProducto(pantalla.centralDespacho.getGestionProductos().getListaProductos().get(keyEliminar))) {
                        pantalla.centralDespacho.getGestionProductos().eliminarProducto(keyEliminar);
                    } else {
                        System.out.println("\t[!] Error. El producto está asociado a un pedido, no se puede eliminar.");
                    }
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
                    System.out.print("\tIngrese la cedula de " + nombre + " :");
                    Long ced = 0l;
                    do {
                        try {
                            ced = in.nextLong();
                            ver = true;
                        } catch (Exception e) {
                            in.next();
                            System.out.println("\t[!] Ingrese un valor numerico.");

                        }
                    } while (!ver);
                    System.out.print("\tIngrese el telefono de contacto de " + nombre + " :");
                    Long tel = 0l;
                    ver = false;
                    do {
                        try {
                            tel = in.nextLong();
                            ver = true;
                        } catch (Exception e) {
                            in.next();
                            System.out.println("\t[!] Ingrese un valor numerico.");

                        }
                    } while (!ver);
                    in.nextLine();
                    System.out.print("\tIngrese la direccion de " + nombre + " :");
                    String dir = in.nextLine();
                    pantalla.centralDespacho.getGestionCliente().InsertarCliente(ced, nombre, tel, dir);
                    break;
                case 7:
                    System.out.println("\n\tDigite la Identificacion del Cliente que desea modificar: ");
                    long cedula;
                    cedula = in.nextLong();
                    pantalla.centralDespacho.getGestionCliente().modificarDatosCliente(cedula);
                    break;
                case 8:
                    System.out.println("\tIngrese el numero de cedula del cliente que deseas eliminar.");
                    Long cedulaE = 0l;
                    ver = false;
                    do {
                        try {
                            cedulaE = in.nextLong();
                            ver = true;
                        } catch (Exception e) {
                            in.next();
                            System.out.println("\t[!]Ingrese un valor numerico.");
                        }
                    } while (!ver);
                    if (pantalla.centralDespacho.validarCliente(cedulaE))
                        pantalla.centralDespacho.getGestionCliente().EliminarCliente(cedulaE);
                    else System.out.println("\t[!]El cliente esta asociado a un Pedido");
                    break;
                case 9:
                    System.out.println("\n\tInserte el codigo del producto");
                    UUID idProd = pantalla.leerUUID();
                    System.out.println("\tInserte el documento del cliente");
                    Long cedulaCliente = in.nextLong();

                    if (pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) != null && pantalla.centralDespacho.getGestionProductos().existeProducto(idProd) != null) {
                        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("\t______Informacion Pedido________");
                        System.out.println("\tDigite el nombre el repartidor");
                        String repartidor = pantalla.leerString();
                        System.out.println("\tDigite la fecha en la cual desea despachar el pedido en formato dd/MM/yyyy");
                        String fechain = in.next();
                        Cliente clientePedido = pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente);
                        Producto ProductoPedido = pantalla.centralDespacho.getGestionProductos().existeProducto(idProd);
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
                        if (dias <= 2) {
                            System.out.println("\t [!] Ups.. el pedido lo debes hacer 2 dias antes de la fecha de entrega \n\t No se ha realizado tu pedido, vuelve a intentarlo");
                        }
                        else {
                            while (pantalla.centralDespacho.ExistePedido(clientePedido, ProductoPedido, fechaDespacho) != null) {
                                System.out.println("\t [!] El pedido ya existe en esta fecha");
                                System.out.println("\tDesea crear el mismo pedido con una nueva fecha ?");
                                System.out.println("\tS. si");
                                System.out.println("\tN. NO");
                                opcion = in.next().charAt(0);

                                if (opcion == 'S') {
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
                                }
                            }
                            ArrayList<ServicioAdicional> servicios = new ArrayList<>();
                            do {
                                System.out.println("\tDesea incluir servicion adicionales ?");
                                System.out.println("\tS. si");
                                System.out.println("\tN. NO");
                                opcion = in.next().charAt(0);

                                if (opcion == 'S') {
                                    int opcionservicio;
                                    System.out.println("\tCual de los servicios adicionales desea añadir");
                                    System.out.println("\t1. Bono Regalo");
                                    System.out.println("\t2. Envio Prime");
                                    opcionservicio = pantalla.leerEntero();
                                    if (opcionservicio == 1) {
                                        System.out.print("\tDefine el Precio\n");
                                        int precio = pantalla.leerEntero();
                                        System.out.print("\tDefine su comercio asociado: ");
                                        String comercio = pantalla.leerString();
                                        System.out.print("\tDefine tu mensaje personalizado: ");
                                        String mensaje = pantalla.leerString();
                                        Calendar fechaservicio = (Calendar) fechaDespacho.clone();
                                        fechaservicio.set(Calendar.MONTH, fechaservicio.get(Calendar.MONTH) + 6);
                                        BonoRegalo bonotemp = new BonoRegalo("Bono regalo", (double) precio, comercio, mensaje, fechaservicio);
                                        servicios.add(bonotemp);
                                    }
                                    else {
                                        System.out.println("\tDefine el Precio");
                                        int precio = in.nextInt();
                                        System.out.println("\tInserte la distancia de su envio");
                                        Double distancia = in.nextDouble();
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
                                        System.out.println("\tDigite el numero de cajas a llevar");
                                        Integer cajas = in.nextInt();
                                        EnvioPrime enviotemp = new EnvioPrime("Envio Prime", (double) precio, distancia, tipo, cajas);
                                        servicios.add(enviotemp);
                                    }
                                }
                            } while (opcion != 'N');
                            Pedido nuevopedido;
                            nuevopedido = new Pedido(fechaDespacho, repartidor, clientePedido, ProductoPedido);
                            nuevopedido.setServiciosAdicionales(servicios);
                            pantalla.centralDespacho.ReservarPedido(nuevopedido);
                        }
                    }
                    else {
                        if (pantalla.centralDespacho.getGestionCliente().existeCliente(cedulaCliente) == null)
                            System.out.println("\t[!] El cliente no existe");
                        if (pantalla.centralDespacho.getGestionProductos().existeProducto(idProd) == null)
                            System.out.println("\t[!] El Producto no existe");
                    }
                    break;
                case 10:
                    System.out.println("\n\tInserte el codigo del producto a modificar");
                    UUID idmodificar = pantalla.leerUUID();
                    if (pantalla.centralDespacho.ExistePedido(idmodificar) != null) {
                        pantalla.centralDespacho.ModificarPedido(idmodificar);
                    } else
                        System.out.println("\t[!] el pedido a modificar no existe");
                    break;
                case 11:
                    System.out.println("\tEliminar Pedido");
                    System.out.print("\tDigite el numero del pedido que desea eliminar: ");
                    UUID EliminarPed;
                    EliminarPed = pantalla.leerUUID();
                    pantalla.centralDespacho.EliminarPedido(EliminarPed);
                case 12:
                    pantalla.centralDespacho.VerPedido();
                    break;
                case 13:
                    pantalla.centralDespacho.verListadoDePedidosDeProductoYFechaEspecífica();
                    break;
                case 14:
                    System.out.println("\tDigite el numero del pedido del que quiere ver sus envios prime: ");
                    UUID idped;
                    idped = pantalla.leerUUID();
                    Pedido pd = pantalla.centralDespacho.ExistePedido(idped);
                    System.out.println(pd.getNombreRepartidor());
                    System.out.println("\tElige el tipo de envio a filtrar");
                    System.out.println("\t1. Bicicleta");
                    System.out.println("\t2. Moto");
                    System.out.println("\t3. Minivan");
                    int op = pantalla.leerEntero();
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
                    System.out.println(pd.enviosPrimePorTipo(tipo).toString());
                    break;
                case 15:
                    Map<UUID, Producto> productosFruver = pantalla.getCentralDespacho().verProductosTipoFruver();
                    if (productosFruver.size() != 0) {
                        System.out.println(productosFruver.toString());
                    } else {
                        System.out.println("\tNo hay productos de tipo fruver");
                    }
                    break;
                case 16:
                    ArrayList<Pedido> pedidosAseo = pantalla.getCentralDespacho().verPedidosAsociadosAProductosAseo();
                    if (pedidosAseo.size() != 0) {
                        System.out.println(pedidosAseo.toString());
                    } else {
                        System.out.println("\tNo hay productos de tipo fruver");
                    }
                    break;
                case 17:
                    System.out.println("\tElige el tipo de producto a filtrar");
                    System.out.println("\t1. Hogar");
                    System.out.println("\t2. Industrial");
                    System.out.println("\t3. Hopitalario");
                    int opc = pantalla.leerEntero();
                    TipoProducto tipo1;
                    switch (opc) {
                        case 1:
                            tipo1 = TipoProducto.HOGAR;
                            break;
                        case 2:
                            tipo1 = TipoProducto.INDUSTRIAL;
                            break;
                        case 3:
                            tipo1 = TipoProducto.HOSPITALARIO;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + opc);
                    }
                    if (pantalla.centralDespacho.precioPedidosDeAseoPorTipo(tipo1) != 0) {
                        System.out.println("\tEl precio de productos aseo de: " + tipo1 + "es:" + pantalla.centralDespacho.precioPedidosDeAseoPorTipo(tipo1));
                    } else {
                        System.out.println("\tNo existen producto de aseo de tipo: " + tipo1);
                    }
                    break;
                default:
                    System.out.println("\tDigite una opción válida y vuelva a intentarlo");
                    break;
            }
        } while (opcion != 0);
        System.out.println("fin");
    }

    public ControlDespacho getCentralDespacho() {
        return centralDespacho;
    }

    public Integer leerEntero() {
        int numeroEntero = 0;
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        do {
            try {
                numeroEntero = sc.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("\t[!] Ingrese un valor entero: ");
            }
        } while (!valido);
        return numeroEntero;
    }

    public Double leerDouble() {
        Scanner sc = new Scanner(System.in);
        Double numeroDouble = 0d;
        boolean valido = false;
        do {
            try {
                numeroDouble = sc.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("\t[!] Ingrese un valor entero: ");
            }
        } while (!valido);
        return numeroDouble;
    }

    public String leerString() {
        String str = "";
        Scanner sc = new Scanner(System.in);
        do {
            str = sc.nextLine();
        } while (str.isBlank());
        return str;
    }

    public UUID leerUUID() {
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        UUID key = null;
        do {
            try {
                key = UUID.fromString(sc.nextLine());
                valido = true;
            } catch (Exception e) {
                System.out.print("\t[!] Ingrese un ID válido: ");
            }
        } while (!valido);
        return key;
    }
}

package org.PUJ.View;

import org.PUJ.Controller.ControlDespacho;
import org.PUJ.Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PantallaDespacho {
    private ControlDespacho centralDespacho = new ControlDespacho();

    public static void main(String[] args) {

        PantallaDespacho pantalla = new PantallaDespacho();
        //Objetos
        Cliente cliente1 = new Cliente(1000077617L, "Nicolas David", 316009L, "BG 6447");
        Cliente cliente2 = new Cliente(1000444324L, "Santiago Gutierrez", 951233L, "SS 1234");
        Cliente cliente3 = new Cliente(2106226983L, "Daniel Barreto", 443253L, "DD 5678");
        Cliente cliente4 = new Cliente(1555345340L, "William Martinez", 664323L, "LL 4432");
        Cliente cliente5 = new Cliente(9931183443L, "Jose Diaz", 221453L, "CL 2224");
        // 3 de cada uno: Productos - Fruver, aseo, otros
        Producto producto1 = new Producto("Papas fritas", 2500d, "La esquina");
        Producto producto2 = new Producto("Yogurt con fresa", 3500d, "La esquina");
        Producto producto3 = new Producto("Doritos", 4000d, "La otra esquina");

        Aseo productoAseo1 = new Aseo("Escoba", 4500D, "la esquina", "Dersa",TipoProducto.HOGAR, true);
        Aseo productoAseo2 = new Aseo("Trapero", 7000D, "la esquina", "Dersa", TipoProducto.HOGAR, false);
        Aseo productoAseo3 = new Aseo("Desengrasante", 12000d, "los arboles", "Wd-40", TipoProducto.INDUSTRIAL, true);

        Fruver productoFruver1 = new Fruver("Manzana", 400D, "los arboles", 0d, "Napoles");
        Fruver productoFruver2 = new Fruver("Huevos", 1800D, "Justo y bueno", 0d, "Napoles");
        Fruver productoFruver3 = new Fruver("Arverja", 3000D, "los arboles", 0d, "Quintana");
        // 1 pedido sin servicio adicional
        Pedido pedido1 = new Pedido(Calendar.getInstance(), "Camilo", cliente2, producto1);
        // 5 pedidos con servicios adicionales
        // - Dos con envio prime y uno con bonoregalo.
        Pedido pedido2 = new Pedido(Calendar.getInstance(), "Juan", cliente1, producto1);
        Pedido pedido3 = new Pedido(Calendar.getInstance(), "Miguel", cliente3, producto3);
        Pedido pedido4 = new Pedido(Calendar.getInstance(), "Nicolas", cliente5, productoAseo1);
        Pedido pedido5 = new Pedido(Calendar.getInstance(), "Sofia", cliente5, productoFruver1);
        Pedido pedido6 = new Pedido(Calendar.getInstance(), "Sara", cliente4, productoAseo3);

        //servicios adicionales
        ServicioAdicional serv1 = new BonoRegalo("Bono regalo", 2000.0, "viva ya", "para el men re aspero", Calendar.getInstance());
        ServicioAdicional serv2 = new BonoRegalo("Bono regalo", 2000.0, "la tula", "buenos dias alegria", Calendar.getInstance());
        ServicioAdicional serv3 = new BonoRegalo("Bono regalo", 3000.0, "casa ya", "buenos dias señor sol", Calendar.getInstance());
        ServicioAdicional serv4 = new EnvioPrime("Envio prime", 20000.0, 200.0, TipoTransporte.BICICLETA, 3);
        ServicioAdicional serv5 = new EnvioPrime("Envio prime", 3000.0, 500.0, TipoTransporte.MINIVAN, 20);
        ServicioAdicional serv6 = new EnvioPrime("Envio prime", 5000.0, 500.0, TipoTransporte.MOTO, 5);

        //colecion de servicios
        ArrayList<ServicioAdicional> serv1pd1 = new ArrayList<>();
        serv1pd1.add(serv1);
        ArrayList<ServicioAdicional> serv2pd2 = new ArrayList<>();
        serv2pd2.add(serv2);
        ArrayList<ServicioAdicional> serv3pd3 = new ArrayList<>();
        serv3pd3.add(serv3);
        ArrayList<ServicioAdicional> serv4pd4 = new ArrayList<>();
        serv4pd4.add(serv4);
        ArrayList<ServicioAdicional> serv5pd5 = new ArrayList<>();
        serv5pd5.add(serv5);
        ArrayList<ServicioAdicional> serv6pd6 = new ArrayList<>();
        serv6pd6.add(serv6);

        pedido1.setServiciosAdicionales(serv1pd1);
        pedido2.setServiciosAdicionales(serv2pd2);
        pedido3.setServiciosAdicionales(serv3pd3);
        pedido4.setServiciosAdicionales(serv4pd4);
        pedido5.setServiciosAdicionales(serv5pd5);
        pedido6.setServiciosAdicionales(serv6pd6);

        pantalla.getCentralDespacho().getGestionCliente().getListaClientes().put(cliente1.getCedula(), cliente1);
        pantalla.getCentralDespacho().getGestionCliente().getListaClientes().put(cliente2.getCedula(), cliente2);
        pantalla.getCentralDespacho().getGestionCliente().getListaClientes().put(cliente3.getCedula(), cliente3);
        pantalla.getCentralDespacho().getGestionCliente().getListaClientes().put(cliente4.getCedula(), cliente4);
        pantalla.getCentralDespacho().getGestionCliente().getListaClientes().put(cliente5.getCedula(), cliente5);

        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(producto1.getProdId(), producto1);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(producto2.getProdId(), producto2);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(producto3.getProdId(), producto3);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoAseo1.getProdId(), productoAseo1);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoAseo2.getProdId(), productoAseo2);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoAseo3.getProdId(), productoAseo3);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoFruver1.getProdId(), productoFruver1);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoFruver2.getProdId(), productoFruver2);
        pantalla.getCentralDespacho().getGestionProductos().getListaProductos().put(productoFruver3.getProdId(), productoFruver3);

        pantalla.getCentralDespacho().getPedidos().add(pedido1);
        pantalla.getCentralDespacho().getPedidos().add(pedido2);
        pantalla.getCentralDespacho().getPedidos().add(pedido3);
        pantalla.getCentralDespacho().getPedidos().add(pedido4);
        pantalla.getCentralDespacho().getPedidos().add(pedido5);
        pantalla.getCentralDespacho().getPedidos().add(pedido6);

        //Fin objetos

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
                    Integer tipoProducto = 1;
                    do {
                        System.out.println("\t [1] Aseo\t[2] Fruver\t[3] Otro");
                        tipoProducto = pantalla.leerEntero();
                    } while (tipoProducto > 3 || tipoProducto < 1);
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
                case 11:
                    System.out.println("\tEliminar Pedido");
                    System.out.print("\tDigite el numero del pedido que desea eliminar: ");
                    UUID EliminarPed = pantalla.leerUUID();
                    if (pantalla.centralDespacho.ExistePedido(EliminarPed) != null) {
                        System.out.println("Esta seguro que desea eliminar el pedido? : " + pantalla.centralDespacho.ExistePedido(EliminarPed).toString());
                        System.out.println("S = si, N = no");
                        String verificacion = pantalla.leerString();
                        if (verificacion.equals("S")) {
                            pantalla.centralDespacho.EliminarPedido(EliminarPed);
                            System.out.println("\tPEDIDO ELIMINADO CON EXITO");
                        }
                    }

                case 12:
                    pantalla.centralDespacho.VerPedido();
                    break;
                case 13:
                    System.out.println("\tInserte el codigo del producto");
                    UUID idProd2 = pantalla.leerUUID();
                    if (pantalla.centralDespacho.getGestionProductos().existeProducto(idProd2) == null) {
                        System.out.println("\t[!] El producto no existe");
                    } else {
                        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("\tDigite la fecha específica en formato dd/MM/yyyy");
                        String fechain = in.next();
                        Calendar fecha = Calendar.getInstance();
                        try {
                            Date fechaDate = Fecha.parse(fechain);
                            fecha.setTime(fechaDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        pantalla.getCentralDespacho().verListadoDePedidosDeProductoYFechaEspecífica(idProd2, fecha);
                    }
                    break;

                case 14:
                    System.out.println("\tDigite el numero del pedido del que quiere ver sus envios prime: ");
                    UUID idped;
                    idped = pantalla.leerUUID();
                    Pedido pd = pantalla.centralDespacho.ExistePedido(idped);
                    System.out.println(pd.getNombreRepartidor());
                    int op;
                    do {
                        System.out.println("\tElige el tipo de envio a filtrar");
                        System.out.println("\t1. Bicicleta");
                        System.out.println("\t2. Moto");
                        System.out.println("\t3. Minivan");
                        op = pantalla.leerEntero();
                    } while (op < 1 || op > 3);
                    TipoTransporte tipo = null;
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
                    }
                    if (pd.enviosPrimePorTipo(tipo).size() > 0) {
                        System.out.println(pd.enviosPrimePorTipo(tipo).toString());
                    } else {
                        System.out.println("\t[!] No existen datos para este filtro.");
                    }
                    break;
                case 15:
                    Map<UUID, Producto> productosFruver = pantalla.getCentralDespacho().verProductosTipoFruver();
                    if (productosFruver.size() != 0) {
                        System.out.println(productosFruver.toString());
                    } else {
                        System.out.println("\t[!] No hay productos de tipo fruver");
                    }
                    break;
                case 16:
                    ArrayList<Pedido> pedidosAseo = pantalla.getCentralDespacho().verPedidosAsociadosAProductosAseo();
                    if (pedidosAseo.size() != 0) {
                        System.out.println(pedidosAseo.toString());
                    } else {
                        System.out.println("\t[!] No hay productos de tipo fruver");
                    }
                    break;
                case 17:
                    int opc;
                    do {
                        System.out.println("\tElige el tipo de producto a filtrar");
                        System.out.println("\t1. Hogar");
                        System.out.println("\t2. Industrial");
                        System.out.println("\t3. Hopitalario");
                        opc = pantalla.leerEntero();
                    } while (opc < 1 || opc > 3);
                    TipoProducto tipo1 = null;
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
                    }
                    if (pantalla.centralDespacho.precioPedidosDeAseoPorTipo(tipo1) != 0) {
                        System.out.println("\tEl precio de productos aseo de " + tipo1.toString().toLowerCase() + " es $" + pantalla.centralDespacho.precioPedidosDeAseoPorTipo(tipo1));
                    } else {
                        System.out.println("\t[!] No existen producto de aseo de tipo " + tipo1.toString().toLowerCase() + ".");
                    }
                    break;
                default:
                    System.out.println("\tDigite una opción válida y vuelva a intentarlo.");
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

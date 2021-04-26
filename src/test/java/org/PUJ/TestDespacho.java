package org.PUJ;

import org.PUJ.Control.ControlDespacho;
import org.PUJ.Model.*;
import org.PUJ.View.PantallaDespacho;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class TestDespacho {
    private ControlDespacho control = new ControlDespacho();
    // 5 Clientes
    private Cliente cliente1 = new Cliente(1000077617L, "Nicolas David", 316009L, "BG 6447");
    private Cliente cliente2 = new Cliente(1000444324L, "Santiago Gutierrez", 951233L, "SS 1234");
    private Cliente cliente3 = new Cliente(2106226983L, "Daniel Barreto", 443253L, "DD 5678");
    private Cliente cliente4 = new Cliente(1555345340L, "William Martinez", 664323L, "LL 4432");
    private Cliente cliente5 = new Cliente(9931183443L, "Jose Diaz", 221453L, "CL 2224");
    // 3 de cada uno: Productos - Fruver, aseo, otros
    private Producto producto1 = new Producto("Papas fritas", 2500d, "La esquina");
    private Producto producto2 = new Producto("Yogurt con fresa", 3500d, "La esquina");
    private Producto producto3 = new Producto("Doritos", 4000d, "La otra esquina");

    private Aseo productoAseo1 = new Aseo("Escoba", 4500D, "la esquina", "Dersa", TipoProducto.HOGAR, true);
    private Aseo productoAseo2 = new Aseo("Trapero", 7000D, "la esquina", "Dersa", TipoProducto.HOGAR, false);
    private Aseo productoAseo3 = new Aseo("Desengrasante", 5000d, "los arboles", "Wd-40", TipoProducto.INDUSTRIAL, true);

    private Fruver productoFruver1 = new Fruver("Manzana", 400D, "los arboles", 0d, "Napoles");
    private Fruver productoFruver2 = new Fruver("Huevos", 1800D, "Justo y bueno", 0d, "Napoles");
    private Fruver productoFruver3 = new Fruver("Arverja", 3000D, "los arboles", 0d, "Quintana");
    // 1 pedido sin servicio adicional
    private Pedido pedido1 = new Pedido(Calendar.getInstance(), "Camilo", cliente2, producto1);
    // 5 pedidos con servicios adicionales
    // - Dos con envio prime y uno con bonoregalo.
    private Pedido pedido2 = new Pedido(Calendar.getInstance(), "Juan", cliente1, producto1);
    private Pedido pedido3 = new Pedido(Calendar.getInstance(), "Miguel", cliente3, producto3);
    private Pedido pedido4 = new Pedido(Calendar.getInstance(), "Nicolas", cliente5, productoAseo1);
    private Pedido pedido5 = new Pedido(Calendar.getInstance(), "Sofia", cliente5, productoFruver1);
    private Pedido pedido6 = new Pedido(Calendar.getInstance(), "Sara", cliente4, productoAseo3);
    //pantalla.getCentralDespacho().


    @Test
    public void TestInsertarcliente() {
        control.getGestionCliente().InsertarCliente(cliente1.getCedula(), cliente1.getNombreCompleto(), cliente1.getTelefonoContacto(), cliente1.getDireccion());
        assertEquals(1, control.getGestionCliente().getListaClientes().size());
        assertFalse(control.getGestionCliente().getListaClientes().containsKey(cliente2.getCedula()));

    }

    @Test
    public void testValidarCliente(){
        control.getGestionCliente().InsertarCliente(cliente1.getCedula(),cliente1.getNombreCompleto(),cliente1.getTelefonoContacto(),cliente1.getDireccion());
        assertTrue(control.validarCliente(cliente1.getCedula()));
        control.ReservarPedido(pedido2);
        assertFalse(control.validarCliente(cliente1.getCedula()));
    }

    @Test
    public void testexisteCliente(){
        control.getGestionCliente().InsertarCliente(cliente1.getCedula(),cliente1.getNombreCompleto(),cliente1.getTelefonoContacto(),cliente1.getDireccion());

        //assertEquals(cliente1,control.getGestionCliente().existeCliente(cliente1.getCedula()));
        System.out.println();
    }


    @Test
    public void TestEliminarProducto() {
        control.getGestionCliente().InsertarCliente(cliente3.getCedula(), cliente3.getNombreCompleto(), cliente3.getTelefonoContacto(), cliente3.getDireccion());
        control.getGestionCliente().InsertarCliente(cliente4.getCedula(), cliente4.getNombreCompleto(), cliente4.getTelefonoContacto(), cliente4.getDireccion());
        control.getGestionCliente().EliminarCliente(cliente3.getCedula());
        assertEquals(1, control.getGestionCliente().getListaClientes().size());
        assertNotEquals(2, control.getGestionCliente().getListaClientes().size());

    }

    @Test
    public void testInsertarProducto() {
        control.getGestionProductos().insertarProducto(productoAseo3);
        control.getGestionProductos().insertarProducto(producto2);
        assertEquals(productoAseo3, control.getGestionProductos().existeProducto(productoAseo3.getProdId()));
        assertEquals(producto2, control.getGestionProductos().existeProducto(producto2.getProdId()));
        assertEquals(2, control.getGestionProductos().getListaProductos().size());
        assertTrue(control.getGestionProductos().getListaProductos().containsKey(productoAseo3.getProdId()));
        assertTrue(control.getGestionProductos().getListaProductos().containsKey(producto2.getProdId()));
    }

    @Test
    public void testEliminarProducto() {
        control.getGestionProductos().insertarProducto(productoAseo1);
        control.getGestionProductos().insertarProducto(productoFruver2);
        control.getGestionProductos().eliminarProducto(productoFruver2.getProdId());
        assertFalse(control.getGestionProductos().getListaProductos().containsKey(productoFruver2.getProdId()));
        assertEquals(1, control.getGestionProductos().getListaProductos().size());
        assertTrue(control.getGestionProductos().getListaProductos().containsValue(productoAseo1));
    }
    @Test
    public void testValidarProducto(){
        control.getGestionProductos().insertarProducto(producto2);
        assertFalse(control.ValidarProducto(producto2));
        control.ReservarPedido(pedido1);
        //assertTrue(control.ValidarProducto(producto2));
    }
    @Test
    public void testReservarPedido() {
        ArrayList<ServicioAdicional> servicioAdicional1 = new ArrayList<>();
        ArrayList<ServicioAdicional> servicioAdicional2 = new ArrayList<>();
        ArrayList<ServicioAdicional> servicioAdicional3 = new ArrayList<>();

        control.ReservarPedido(pedido1);
        control.ReservarPedido(pedido2);
        assertEquals(2, control.getPedidos().size());
        servicioAdicional1.add(new BonoRegalo("Servicio adicional 1", 3500d, "Justo y bueno", "Mensaje", Calendar.getInstance()));
        pedido2.setServiciosAdicionales(servicioAdicional1);
        servicioAdicional2.add(new EnvioPrime("Envio prime", 60000d, 5d, TipoTransporte.MOTO, 3));
        pedido6.setServiciosAdicionales(servicioAdicional2);
        servicioAdicional3.add(new EnvioPrime("Servicio envio", 6000d, 6d, TipoTransporte.MOTO, 4));
        pedido4.setServiciosAdicionales(servicioAdicional3);
        control.ReservarPedido(pedido6);
        control.ReservarPedido(pedido4);
        assertEquals(4, control.getPedidos().size());
        assertNotEquals(1, control.getPedidos().size());
        control.ReservarPedido(pedido4);
        assertEquals(pedido2.getServiciosAdicionales(), servicioAdicional1);
        assertEquals(pedido6.getServiciosAdicionales(), servicioAdicional2);
        assertEquals(pedido4.getServiciosAdicionales(), servicioAdicional3);
        assertNotEquals(pedido2.getServiciosAdicionales(), servicioAdicional2);

    }

    @Test
    public void testExistePedido() {
        control.ReservarPedido(pedido1);
        control.ReservarPedido(pedido3);
        control.ReservarPedido(pedido5);
        assertEquals(pedido1, control.ExistePedido(pedido1.getNumeroPedido()));
        assertEquals(pedido3, control.ExistePedido(pedido3.getNumeroPedido()));
        assertNull( control.ExistePedido(pedido6.getNumeroPedido()));
        assertEquals(pedido1,control.ExistePedido(cliente2,producto1,pedido1.getFechaRecibido()));
        assertEquals(pedido3,control.ExistePedido(cliente3,producto3,pedido3.getFechaRecibido()));
        assertEquals(pedido5,control.ExistePedido(cliente5,productoFruver1,pedido5.getFechaRecibido()));
        assertNotEquals(pedido5,control.ExistePedido(cliente2,productoAseo2,Calendar.getInstance()));
        assertNotEquals(pedido2,control.ExistePedido(pedido2.getNumeroPedido()));

    }

    @Test
    public void testExisteProducto() {
        control.getGestionProductos().insertarProducto(productoFruver1);
        control.getGestionProductos().insertarProducto(producto3);
        control.getGestionProductos().insertarProducto(producto2);

        assertEquals(productoFruver1, control.getGestionProductos().existeProducto(productoFruver1.getProdId()));
        assertEquals(producto3, control.getGestionProductos().existeProducto(producto3.getProdId()));
        assertEquals(producto2, control.getGestionProductos().existeProducto(producto2.getProdId()));
    }

    @Test
    public void testEliminarPedido() {
        control.ReservarPedido(pedido1);
        control.ReservarPedido(pedido2);
        control.ReservarPedido(pedido3);

        control.EliminarPedido(pedido1.getNumeroPedido());
        control.EliminarPedido(pedido3.getNumeroPedido());

        assertEquals(1, control.getPedidos().size());
        assertFalse(control.getPedidos().contains(pedido1));
        assertFalse(control.getPedidos().contains(pedido3));
        assertTrue(control.getPedidos().contains(pedido2));

    }
}



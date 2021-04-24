package org.PUJ;

import org.PUJ.Control.ControlDespacho;
import org.PUJ.Model.*;
import org.PUJ.View.PantallaDespacho;
import org.junit.Before;

public class TestDespacho {
    private PantallaDespacho pantalla = new PantallaDespacho();

    @Before
    public void instanciarProductos() throws Exception {

        // 5 Clientes

        Cliente cliente1 = new Cliente(1000077617L, "Nicolas David", 316009L, "BG 6447");
        Cliente cliente2 = new Cliente(1000444324L, "Santiago Gutierrez", 951233L, "SS 1234");
        Cliente cliente3 = new Cliente(2106226983L, "Daniel Barreto", 443253L, "DD 5678");
        Cliente cliente4 = new Cliente(1555345340L, "William Martinez", 664323L, "LL 4432");
        Cliente cliente5 = new Cliente(9931183443L, "Jose Diaz", 221453L, "CL 2224");

        // 3 de cada uno: Productos - Fruver, aseo, otros
        // 1 pedido sin servicio adicional
        // 5 pedidos con servicios adicionales
        // - Dos con envio prime y uno con bonoregalo.

        Producto producto1 = new Producto("Papas fritas", 2500d, "La esquina");
        Producto producto2 = new Producto("Yogurt con fresa", 3500d, "La esquina");
        Producto producto3 = new Producto("Doritos", 4000d, "La otra esquina");

        Aseo productoAseo1 = new Aseo();
        Aseo productoAseo2 = new Aseo();
        Aseo productoAseo3 = new Aseo();

        Fruver productoFruver1 = new Fruver();
        Fruver productoFruver2 = new Fruver();
        Fruver productoFruver3 = new Fruver();

        Pedido pedido1 = new Pedido();

        Pedido pedido2 = new Pedido();
        Pedido pedido3 = new Pedido();
        Pedido pedido4 = new Pedido();
        Pedido pedido5 = new Pedido();
        Pedido pedido6 = new Pedido();




        //pantalla.getCentralDespacho().

    }

}

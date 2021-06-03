package org.PUJ.Controller.Reportes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.PUJ.Model.Pedido;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoAseoTipo {
    private ArrayList<Pedido> pedidosProductosAseo = new ArrayList<>();

    public ArrayList<Pedido> getPedidosProductosAseo() {
        return pedidosProductosAseo;
    }
}

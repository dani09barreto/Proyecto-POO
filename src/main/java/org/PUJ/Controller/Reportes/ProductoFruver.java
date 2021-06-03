package org.PUJ.Controller.Reportes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.PUJ.Model.Producto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoFruver {
    private Map<UUID, Producto> productosFruver = new HashMap<>();

    public Map<UUID, Producto> getProductosFruver() {
        return productosFruver;
    }
}

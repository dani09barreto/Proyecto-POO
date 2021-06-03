package org.PUJ.Controller.Reportes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.PUJ.Model.ServicioAdicional;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EnvioTipoTransporte {
    private ArrayList<ServicioAdicional> servciosEnvios = new ArrayList<>();

    public ArrayList<ServicioAdicional> getServciosEnvios() {
        return servciosEnvios;
    }
}

package org.PUJ.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.PUJ.Controller.GestionProductos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FlieUtils {
    public static void saveXML(File salida, GestionProductos controlador) throws IOException, JAXBException {
        try(FileWriter archvioSalida = new FileWriter(salida)){
            JAXBContext context = JAXBContext.newInstance(GestionProductos.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //TO-DO marshal ar al archivo XML
        }
    }

}

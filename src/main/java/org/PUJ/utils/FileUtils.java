package org.PUJ.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.PUJ.Controller.Reportes.EnvioTipoTransporte;
import org.PUJ.Controller.Reportes.ProductoAseoTipo;
import org.PUJ.Controller.Reportes.ProductoFruver;
import org.PUJ.Controller.Reportes.RangoFecha;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void saveXML(File salida, EnvioTipoTransporte controlador) throws IOException, JAXBException {
        try(FileWriter archvioSalida = new FileWriter(salida)){
            JAXBContext context = JAXBContext.newInstance(EnvioTipoTransporte.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(controlador, archvioSalida);
        }
    }
    public static void saveXML(File salida, ProductoAseoTipo controlador) throws IOException, JAXBException {
        try(FileWriter archvioSalida = new FileWriter(salida)){
            JAXBContext context = JAXBContext.newInstance(ProductoAseoTipo.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(controlador, archvioSalida);
        }
    }
    public static void saveXML(File salida, ProductoFruver controlador) throws IOException, JAXBException {
        try(FileWriter archvioSalida = new FileWriter(salida)){
            JAXBContext context = JAXBContext.newInstance(ProductoFruver.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(controlador, archvioSalida);
        }
    }
    public static void saveXML(File salida, RangoFecha controlador) throws IOException, JAXBException {
        try(FileWriter archvioSalida = new FileWriter(salida)){
            JAXBContext context = JAXBContext.newInstance(RangoFecha.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(controlador, archvioSalida);
        }
    }
}

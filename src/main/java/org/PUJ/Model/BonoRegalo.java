package org.PUJ.Model;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@XmlRootElement
public class BonoRegalo extends ServicioAdicional{
    private String comercioAsociado;
    private String mensaje;
    private Calendar fechaVencimiento;

    public BonoRegalo(String nombreServicio, Double precio, String comercioAsociado, String mensaje, Calendar fechaVencimiento) {
        super(nombreServicio, precio);
        this.comercioAsociado = comercioAsociado;
        this.mensaje = mensaje;
        this.fechaVencimiento = fechaVencimiento;
        this.precio = calcularPrecio();
    }

    public String getComercioAsociado() {
        return comercioAsociado;
    }

    public void setComercioAsociado(String comercioAsociado) {
        this.comercioAsociado = comercioAsociado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getFechaVencimientostring(){
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        return Fecha.format(fechaVencimiento.getTime());
    }

    public void setFechaVencimiento(Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public Double calcularPrecio() {
        return super.precio;
    }

    @Override
    public String toString() {
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        return  super.toString() +
                "\n\tComercio Asociado:" + comercioAsociado +
                "\n\tMensaje: " + mensaje +
                "\n\tFecha de Vencimiento: " + Fecha.format(fechaVencimiento.getTime()) + "\n";
    }
}

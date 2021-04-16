package org.PUJ.Model;
import java.util.Calendar;
import java.util.UUID;

public class BonoRegalo extends ServicioAdicional{
    private String comercioAsociado;
    private String mensaje;
    private Calendar fechaVencimiento;

    public BonoRegalo(String nombreServicio, Double precio , String mensaje) {
        super(nombreServicio, precio);
        this.mensaje = mensaje;
    }

    @Override
    public Double calcularPrecio() {
        return null;
    }
}

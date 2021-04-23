package org.PUJ.Model;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        return "\n\tBonoRegalo: " +
                "\n\tcomercioAsociado: " + comercioAsociado +
                "\n\tmensaje: " + mensaje +
                "\n\tfechaVencimiento: " + Fecha.format(this.fechaVencimiento.getTime()) +
                "\n\tcodigoServicio: " + codigoServicio +
                "\n\tnombreServicio: " + nombreServicio +
                "\n\tprecio: " + precio;
    }
}

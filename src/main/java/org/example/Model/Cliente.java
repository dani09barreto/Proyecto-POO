package org.example.Model;

import java.util.Objects;

public class Cliente {
    private long cedula;
    private String nombreCompleto;
    private long telefonoContacto;
    private String direccion;

    public Cliente(long cedula, String nombreCompleto, long telefonoContacto, String direccion) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
        this.direccion = direccion;
    }

    public Cliente(){}

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public long getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return  "\tCedula: " + cedula +
                "\n\tNombre: " + nombreCompleto +
                "\n\tTelefono: " + telefonoContacto +
                "\n\tDireccion: " + direccion + "\n";
    }
}
package org.PUJ.Model;

public class Cliente {
    private Long cedula;
    private String nombreCompleto;
    private Long telefonoContacto;
    private String direccion;

    public Cliente(Long cedula, String nombreCompleto, Long telefonoContacto, String direccion) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
        this.direccion = direccion;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

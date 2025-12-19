package com.fidelitas.matchmanager.modelo;

public class Usuario {

    private String nombre;
    private String correo;
    private String password;
    private String rol;     // "Administrador" o "Espectador"
    private String estado;

    public Usuario(String nombre, String correo, String password, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.estado = "Activo"; // estado simple por defecto
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

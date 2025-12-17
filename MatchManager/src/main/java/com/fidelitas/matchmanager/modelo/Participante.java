/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * Clase modelo Participante:
 * Representa a un participante dentro de un evento deportivo.
 * Se usará en la lista doblemente enlazada.
 */
public class Participante {
    
    private String nombre;   // Nombre del participante
    private String equipo;   // Equipo al que pertenece
    private String rol;      // Rol dentro del evento (ej: jugador, entrenador)
    private String estado;   // Estado (Activo/Inactivo)

    /**
     * Constructor: obliga a definir los datos al crear el objeto.
     * @param nombre Nombre del participante
     * @param equipo Equipo al que pertenece
     * @param rol Rol dentro del evento
     * @param estado Estado inicial (Activo/Inactivo)
     */
    public Participante(String nombre, String equipo, String rol, String estado) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.rol = rol;
        this.estado = estado;
    }

    // Getters: permiten leer los datos
    public String getNombre() { return nombre; }
    public String getEquipo() { return equipo; }
    public String getRol() { return rol; }
    public String getEstado() { return estado; }

    // Setters: permiten modificar datos si es necesario
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEquipo(String equipo) { this.equipo = equipo; }
    public void setRol(String rol) { this.rol = rol; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombre + " (" + equipo + ") - " + rol + " - " + estado;
    }
}

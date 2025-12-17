package com.fidelitas.matchmanager.modelo;

import java.time.LocalDate;

/**
 * Clase modelo para representar un Evento.
 * Cada evento tiene un nombre, una ubicación y una fecha.
 * Forma parte del paquete 'modelo' (sin dependencias de Swing).
 */
public class Evento {

    // --- Atributos privados ---
    private String nombre;      // Nombre del evento
    private String ubicacion;   // Ubicación del evento
    private LocalDate fecha;    // Fecha del evento

    /**
     * Constructor: crea un nuevo evento con nombre, ubicación y fecha.
     *
     * @param nombre Nombre del evento
     * @param ubicacion Ubicación del evento
     * @param fecha Fecha del evento
     */
    public Evento(String nombre, String ubicacion, LocalDate fecha) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
    }

    // --- Getters ---
    /** @return el nombre del evento */
    public String getNombre() {
        return nombre;
    }

    /** @return la ubicación del evento */
    public String getUbicacion() {
        return ubicacion;
    }

    /** @return la fecha del evento */
    public LocalDate getFecha() {
        return fecha;
    }

    // --- Setters ---
    /** Cambia el nombre del evento */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Cambia la ubicación del evento */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /** Cambia la fecha del evento */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // --- Utilitario para depuración ---
    @Override
    public String toString() {
        return nombre + " | " + ubicacion + " | " + fecha;
    }
}

package com.fidelitas.matchmanager.modelo;

import java.time.LocalDate;

/**
 * Representa un partido programado dentro de un evento.
 */
public class Partido {

    private Equipo equipoA;
    private Equipo equipoB;
    private LocalDate fecha;
    private String lugar;
    private String estado; // "pendiente" | "jugado"

    public Partido(Equipo equipoA, Equipo equipoB, LocalDate fecha, String lugar) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = "pendiente";
    }

    public Equipo getEquipoA() { return equipoA; }
    public Equipo getEquipoB() { return equipoB; }
    public LocalDate getFecha() { return fecha; }
    public String getLugar() { return lugar; }
    public String getEstado() { return estado; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public void marcarComoJugado() { this.estado = "jugado"; }

    @Override
    public String toString() {
        return equipoA.getNombre() + " vs " + equipoB.getNombre() +
               " | " + fecha + " | " + lugar + " | " + estado;
    }
}

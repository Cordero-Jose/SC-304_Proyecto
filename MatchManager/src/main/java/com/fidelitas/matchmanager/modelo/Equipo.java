package com.fidelitas.matchmanager.modelo;

/**
 * Clase modelo Equipo:
 * Representa un equipo deportivo dentro de un evento.
 */
public class Equipo {

    private String nombre;
    private int victorias;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.victorias = 0;
    }

    public String getNombre() { return nombre; }
    public int getVictorias() { return victorias; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void sumarVictoria() { victorias++; }

    @Override
    public String toString() {
        return nombre + " (" + victorias + " victorias)";
    }
}

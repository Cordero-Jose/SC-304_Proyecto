package com.fidelitas.matchmanager.modelo;

import java.time.LocalDate;

/**
 * Clase modelo para representar un Evento.
 * Cada evento tiene su propia data structure interna.
 */
public class Evento {

    // Datos básicos
    private String nombre;
    private String ubicacion;
    private LocalDate fecha;

    // Estructuras del proyecto
    private ListaParticipantes participantes;   // lista doble
    private ColaPartidos colaPartidos;          // cola dinámica
    private PilaResultados pilaResultados;      // pila dinámica
    private GrafoEquipos grafoEnfrentamientos;  // grafo no dirigido
    private BSTClasificacion bstClasificacion;   // árbol de clasificación

    // Constructor
    public Evento(String nombre, String ubicacion, LocalDate fecha) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;

        // Inicializa todas las estructuras
        participantes = new ListaParticipantes();
        colaPartidos = new ColaPartidos();
        pilaResultados = new PilaResultados();
        grafoEnfrentamientos = new GrafoEquipos();
        bstClasificacion = new BSTClasificacion();
    }

    // Getters básicos
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public LocalDate getFecha() { return fecha; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    // Getters de estructuras
    public ListaParticipantes getParticipantes() { return participantes; }
    public ColaPartidos getColaPartidos() { return colaPartidos; }
    public PilaResultados getPilaResultados() { return pilaResultados; }
    public GrafoEquipos getGrafoEquipos() { return grafoEnfrentamientos; }
    public BSTClasificacion getBST() { return bstClasificacion; }

    @Override
    public String toString() {
        return nombre + " | " + ubicacion + " | " + fecha;
    }
}


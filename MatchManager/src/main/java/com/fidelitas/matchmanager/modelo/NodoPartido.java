package com.fidelitas.matchmanager.modelo;

/**
 * Nodo para la cola dinámica de partidos.
 */
public class NodoPartido {

    private Partido partido;
    private NodoPartido siguiente;

    public NodoPartido(Partido partido) {
        this.partido = partido;
        this.siguiente = null;
    }

    public Partido getPartido() { return partido; }
    public NodoPartido getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPartido siguiente) { this.siguiente = siguiente; }
}

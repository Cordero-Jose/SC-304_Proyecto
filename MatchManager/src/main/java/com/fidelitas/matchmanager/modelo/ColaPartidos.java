package com.fidelitas.matchmanager.modelo;

/**
 * Cola dinámica para manejar partidos de un evento (FIFO).
 */
public class ColaPartidos {

    private NodoPartido frente;
    private NodoPartido fin;

    public void encolar(Partido partido) {
        NodoPartido nodo = new NodoPartido(partido);
        if (frente == null) {
            frente = nodo;
            fin = nodo;
        } else {
            fin.setSiguiente(nodo);
            fin = nodo;
        }
    }

    public Partido desencolar() {
        if (frente == null) return null;
        Partido p = frente.getPartido();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        return p;
    }

    public Partido peek() {
        return (frente != null) ? frente.getPartido() : null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public NodoPartido getFrente() {
        return frente;
    }
}

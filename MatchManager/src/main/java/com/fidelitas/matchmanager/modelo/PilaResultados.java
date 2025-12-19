package com.fidelitas.matchmanager.modelo;

/**
 * Pila dinámica para almacenar resultados de partidos jugados.
 */
public class PilaResultados {

    private NodoResultado cima;

    public void apilar(Resultado resultado) {
        NodoResultado nodo = new NodoResultado(resultado);
        nodo.setSiguiente(cima);
        cima = nodo;
    }

    public Resultado desapilar() {
        if (cima == null) return null;
        Resultado r = cima.getResultado();
        cima = cima.getSiguiente();
        return r;
    }

    public Resultado peek() {
        return (cima != null) ? cima.getResultado() : null;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public NodoResultado getCima() {
        return cima;
    }
}

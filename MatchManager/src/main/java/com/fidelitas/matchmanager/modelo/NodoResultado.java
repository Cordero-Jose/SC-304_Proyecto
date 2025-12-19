package com.fidelitas.matchmanager.modelo;

/**
 * Nodo para la pila dinámica de resultados.
 */
public class NodoResultado {

    private Resultado resultado;
    private NodoResultado siguiente;

    public NodoResultado(Resultado resultado) {
        this.resultado = resultado;
        this.siguiente = null;
    }

    public Resultado getResultado() { return resultado; }
    public NodoResultado getSiguiente() { return siguiente; }
    public void setSiguiente(NodoResultado siguiente) { this.siguiente = siguiente; }
}

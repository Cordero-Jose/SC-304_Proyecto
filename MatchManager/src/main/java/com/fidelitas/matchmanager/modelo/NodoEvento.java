/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * Nodo de la lista enlazada simple para eventos.
 * Contiene un Evento y la referencia al siguiente nodo.
 */
public class NodoEvento {
    private Evento dato;
    private NodoEvento siguiente;

    public NodoEvento(Evento dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Evento getDato() { return dato; }
    public NodoEvento getSiguiente() { return siguiente; }
    public void setSiguiente(NodoEvento siguiente) { this.siguiente = siguiente; }
}

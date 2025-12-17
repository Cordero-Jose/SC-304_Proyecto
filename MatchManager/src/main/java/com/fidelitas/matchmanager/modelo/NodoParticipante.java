/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * Nodo de la lista doblemente enlazada para participantes.
 * Contiene un Participante y referencias al anterior y siguiente.
 */
public class NodoParticipante {
    private Participante dato;
    private NodoParticipante siguiente;
    private NodoParticipante anterior;

    public NodoParticipante(Participante dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Participante getDato() { return dato; }
    public NodoParticipante getSiguiente() { return siguiente; }
    public NodoParticipante getAnterior() { return anterior; }
    public void setSiguiente(NodoParticipante siguiente) { this.siguiente = siguiente; }
    public void setAnterior(NodoParticipante anterior) { this.anterior = anterior; }
}


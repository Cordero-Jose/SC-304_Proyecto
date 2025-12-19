/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * Lista doblemente enlazada para gestionar participantes de un evento.
 */
public class ListaParticipantes {
    private NodoParticipante cabeza;
    private NodoParticipante cola;
    private int tamaño;
    
    

public void cambiarEstado(String nombre, String nuevoEstado) {
    NodoParticipante actual = cabeza;
    while (actual != null) {
        if (actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
            actual.getDato().setEstado(nuevoEstado);
            return;
        }
        actual = actual.getSiguiente();
    }
}


    /** Inserta un participante al final de la lista. */
    public void agregar(Participante nuevo) {
        NodoParticipante nodo = new NodoParticipante(nuevo);
        if (cabeza == null) {
            cabeza = nodo;
            cola = nodo;
        } else {
            cola.setSiguiente(nodo);
            nodo.setAnterior(cola);
            cola = nodo;
        }
        tamaño++;
    }

    /** Busca un participante por nombre de forma recursiva. */
    public void buscarRecursivoContiene(NodoParticipante nodo, String texto, java.util.List<Participante> resultados) {
    if (nodo == null) return;

    if (nodo.getDato().getNombre().toLowerCase().contains(texto.toLowerCase())) {
        resultados.add(nodo.getDato());
    }

    buscarRecursivoContiene(nodo.getSiguiente(), texto, resultados);
    }


    /** Elimina un participante por nombre. */
    public boolean eliminarPorNombre(String nombre) {
        NodoParticipante actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
                if (actual.getAnterior() != null) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                } else {
                    cabeza = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                } else {
                    cola = actual.getAnterior();
                }
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int getTamaño() { return tamaño; }
    public NodoParticipante getCabeza() { return cabeza; }
    public NodoParticipante getCola() { return cola; }
}

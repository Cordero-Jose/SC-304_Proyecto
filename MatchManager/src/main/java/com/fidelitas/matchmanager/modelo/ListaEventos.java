/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * Lista enlazada simple para gestionar eventos.
 * Sin uso de colecciones estándar.
 */
public class ListaEventos {
    private NodoEvento cabeza; // inicio de la lista
    private int tamaño;

    /** Inserta un evento al final de la lista. */
    public void agregar(Evento nuevo) {
        NodoEvento nodo = new NodoEvento(nuevo);
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            NodoEvento actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
        tamaño++;
    }

    /** Busca un evento por nombre exacto. Devuelve el Evento o null si no existe. */
    public Evento buscarPorNombre(String nombre) {
        NodoEvento actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /** Elimina el primer evento que coincida por nombre. Devuelve true si se eliminó. */
    public boolean eliminarPorNombre(String nombre) {
        if (cabeza == null) return false;

        // Caso: cabeza coincide
        if (cabeza.getDato().getNombre().equalsIgnoreCase(nombre)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        // Buscar nodo anterior al que queremos eliminar
        NodoEvento actual = cabeza;
        while (actual.getSiguiente() != null &&
               !actual.getSiguiente().getDato().getNombre().equalsIgnoreCase(nombre)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            return false; // no encontrado
        } else {
            // Salta el nodo a eliminar
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            tamaño--;
            return true;
        }
    }

    /** Devuelve el tamaño actual de la lista. */
    public int getTamaño() { return tamaño; }

    /** Iterador sencillo: devuelve la cabeza para recorrer externamente. */
    public NodoEvento getCabeza() { return cabeza; }
}

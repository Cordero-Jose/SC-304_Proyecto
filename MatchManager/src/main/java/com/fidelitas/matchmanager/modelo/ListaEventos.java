package com.fidelitas.matchmanager.modelo;

import java.time.LocalDate;

/**
 * Lista enlazada simple para gestionar eventos.
 * Implementación sin colecciones estándar.
 */
public class ListaEventos {

    private NodoEvento cabeza;  // Primer nodo de la lista
    private int tamaño;         // Cantidad de eventos en la lista

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

        // Caso: el primero es el que se debe eliminar
        if (cabeza.getDato().getNombre().equalsIgnoreCase(nombre)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        NodoEvento actual = cabeza;

        // Buscar nodo previo al que se eliminará
        while (actual.getSiguiente() != null &&
               !actual.getSiguiente().getDato().getNombre().equalsIgnoreCase(nombre)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            return false; // No encontrado
        }

        // Saltar nodo
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamaño--;
        return true;
    }

    /**
     * Actualiza los datos de un evento por nombre.
     * No cambia el nombre a menos que se solicite explícitamente.
     */
    public boolean actualizarPorNombre(String nombre, LocalDate nuevaFecha, String nuevaUbicacion) {
        NodoEvento actual = cabeza;

        while (actual != null) {
            if (actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
                actual.getDato().setFecha(nuevaFecha);
                actual.getDato().setUbicacion(nuevaUbicacion);
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    /** Busca coincidencia parcial para funciones de búsqueda o filtrado. */
    public boolean contieneCoincidencia(String fragmento) {
        NodoEvento actual = cabeza;
        String frag = fragmento.toLowerCase();

        while (actual != null) {
            if (actual.getDato().getNombre().toLowerCase().contains(frag)) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    /** Devuelve el tamaño actual de la lista. */
    public int getTamaño() {
        return tamaño;
    }

    /** Devuelve la cabeza para recorrer la lista externamente. */
    public NodoEvento getCabeza() {
        return cabeza;
    }

    // reemplazar el contenido de la lista actual con otra lista
    public void copiarDesde(ListaEventos otra) {
    this.cabeza = otra.cabeza;
    this.tamaño = otra.tamaño;
}

}

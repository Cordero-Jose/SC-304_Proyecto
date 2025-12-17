package com.fidelitas.matchmanager.controlador;

import com.fidelitas.matchmanager.modelo.*;
import java.time.LocalDate;

/**
 * Controlador para gestionar los eventos.
 * Actúa como puente entre la Vista y el Modelo.
 */
public class ControladorEventos {

    private ListaEventos listaEventos;

    public ControladorEventos(ListaEventos listaEventos) {
        this.listaEventos = listaEventos;
    }

    /** Registrar un nuevo evento */
    public void registrarEvento(String nombre, String ubicacion, LocalDate fecha) {
        Evento e = new Evento(nombre, ubicacion, fecha);
        listaEventos.agregar(e);
    }

    /** Actualizar un evento existente por nombre */
    public boolean actualizarEvento(String nombre, LocalDate fecha, String ubicacion) {
        return listaEventos.actualizarPorNombre(nombre, fecha, ubicacion);
    }

    /** Eliminar un evento por nombre */
    public boolean eliminarEvento(String nombre) {
        return listaEventos.eliminarPorNombre(nombre);
    }

    /** Buscar coincidencia parcial en nombres */
    public boolean buscarCoincidencia(String fragmento) {
        return listaEventos.contieneCoincidencia(fragmento);
    }

    /** Devuelve el nodo cabeza para permitir que la Vista recorra la lista */
    public NodoEvento obtenerCabeza() {
        return listaEventos.getCabeza();
    }

    /** Herramienta opcional de debugging */
    public void listarEventosConsola() {
        NodoEvento actual = listaEventos.getCabeza();
        if (actual == null) {
            System.out.println("No hay eventos registrados.");
        } else {
            System.out.println("Lista de eventos:");
            while (actual != null) {
                Evento e = actual.getDato();
                System.out.println("- " + e);
                actual = actual.getSiguiente();
            }
        }
    }
}

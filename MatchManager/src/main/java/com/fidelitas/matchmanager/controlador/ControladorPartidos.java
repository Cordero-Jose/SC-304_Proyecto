package com.fidelitas.matchmanager.controlador;

import com.fidelitas.matchmanager.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorPartidos {

    private final ColaPartidos cola;

    public ControladorPartidos(ColaPartidos cola) {
        this.cola = cola;
    }

    // agregar partido
    public void agregarPartido(String equipoA, String equipoB, LocalDate fecha, String lugar) {
        Equipo a = new Equipo(equipoA);
        Equipo b = new Equipo(equipoB);
        Partido p = new Partido(a, b, fecha, lugar);
        cola.encolar(p);
    }

    // obtener partido pendiente (siguiente)
    public Partido obtenerSiguiente() {
        return (cola.getFrente() != null) ? cola.getFrente().getPartido() : null;
    }

    // desencolar cuando se juega
    public Partido jugarPartido() {
        return cola.desencolar();
    }

    // obtener todos los partidos pendientes para mostrar en la vista
    public List<String> obtenerLista() {
        List<String> lista = new ArrayList<>();
        NodoPartido nodo = cola.getFrente();
        while (nodo != null) {
            lista.add(nodo.getPartido().toString());
            nodo = nodo.getSiguiente();
        }
        return lista;
    }
}

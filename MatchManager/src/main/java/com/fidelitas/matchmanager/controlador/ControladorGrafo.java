package com.fidelitas.matchmanager.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fidelitas.matchmanager.modelo.GrafoEquipos;

public class ControladorGrafo {

    private final GrafoEquipos grafo;

    public ControladorGrafo(GrafoEquipos grafo) {
        this.grafo = grafo;
    }

    // registrar enfrentamiento (arista no dirigida)
    public void agregarEnfrentamiento(String equipoA, String equipoB) {
        grafo.agregarEnfrentamiento(equipoA, equipoB);
    }

    // lista para mostrar en la vista
    public List<String> obtenerRelaciones() {
        List<String> lista = new ArrayList<>();

        Map<String, Set<String>> mapa = grafo.getAdyacencias();
        for (String eq : mapa.keySet()) {
            String linea = eq + " → " + String.join(", ", mapa.get(eq));
            lista.add(linea);
        }

        return lista;
    }
}

package com.fidelitas.matchmanager.modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoEquipos {

    private final Map<String, Set<String>> adyacencias = new HashMap<>();

    // Agregar nodo (equipo)
    public void agregarEquipo(String nombre) {
        adyacencias.putIfAbsent(nombre, new HashSet<>());
    }

    // Crear arista no dirigida A -- B
    public void agregarEnfrentamiento(String equipoA, String equipoB) {
        agregarEquipo(equipoA);
        agregarEquipo(equipoB);

        adyacencias.get(equipoA).add(equipoB);
        adyacencias.get(equipoB).add(equipoA);
    }

    // Obtener mapa completo para mostrarlo
    public Map<String, Set<String>> getAdyacencias() {
        return adyacencias;
    }
}

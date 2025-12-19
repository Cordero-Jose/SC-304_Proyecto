package com.fidelitas.matchmanager.controlador;

import java.util.List;

import com.fidelitas.matchmanager.modelo.BSTClasificacion;

public class ControladorClasificacion {

    private final BSTClasificacion bst;

    public ControladorClasificacion(BSTClasificacion bst) {
        this.bst = bst;
    }

    // actualizar victorias de un equipo
    public void registrarVictoria(String equipo) {
        int victorias = obtenerVictorias(equipo) + 1;
        bst.insertar(equipo, victorias);
    }

    // obtener lista ordenada del BST
    public List<String> obtenerClasificacion() {
        return bst.obtenerClasificacion();
    }

    // obtener victorias actuales de un equipo
    private int obtenerVictorias(String equipo) {
        for (String linea : bst.obtenerClasificacion()) {

            String[] partes = linea.split("—");
            String nombreEquipo = partes[0].trim();

            if (nombreEquipo.equalsIgnoreCase(equipo)) {
                return Integer.parseInt(
                        partes[1].replace("victorias", "").trim()
                );
            }
        }
        return 0;
    }
}

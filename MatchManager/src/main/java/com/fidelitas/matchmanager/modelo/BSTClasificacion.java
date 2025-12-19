package com.fidelitas.matchmanager.modelo;

import java.util.ArrayList;
import java.util.List;

public class BSTClasificacion {

    private NodoBST raiz;

    public BSTClasificacion() {
        this.raiz = null;
    }

    // Insertar equipo (si ya existe, no crea otro)
    public void insertar(String equipo, int victorias) {
        raiz = insertarRec(raiz, equipo, victorias);
    }

    private NodoBST insertarRec(NodoBST nodo, String equipo, int victorias) {

        if (nodo == null)
            return new NodoBST(equipo, victorias);

        if (equipo.equalsIgnoreCase(nodo.getEquipo())) {
            nodo.setVictorias(victorias);
            return nodo;
        }

        if (victorias < nodo.getVictorias()) {
            nodo.setIzquierda(insertarRec(nodo.getIzquierda(), equipo, victorias));
        } else {
            nodo.setDerecha(insertarRec(nodo.getDerecha(), equipo, victorias));
        }

        return nodo;
    }

    // Obtener lista ordenada (mayor a menor)
    public List<String> obtenerClasificacion() {
        List<String> lista = new ArrayList<>();
        reverseInorder(raiz, lista);
        return lista;
    }

    private void reverseInorder(NodoBST nodo, List<String> lista) {
        if (nodo == null) return;

        reverseInorder(nodo.getDerecha(), lista);
        lista.add(nodo.getEquipo() + " — " + nodo.getVictorias() + " victorias");
        reverseInorder(nodo.getIzquierda(), lista);
    }
}

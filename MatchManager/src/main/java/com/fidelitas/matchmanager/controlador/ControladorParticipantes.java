package com.fidelitas.matchmanager.controlador;

import com.fidelitas.matchmanager.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorParticipantes {

    private final ListaParticipantes lista;

    public ControladorParticipantes(ListaParticipantes lista) {
        this.lista = lista;
    }

    // agregar
    public void agregar(String nombre, String equipo, String rol, String estado) {
        Participante p = new Participante(nombre, equipo, rol, estado);
        lista.agregar(p);
    }

    // actualizar
    public void actualizar(Participante p, String nuevoNombre, String nuevoEquipo, String nuevoRol, String nuevoEstado) {
        p.setNombre(nuevoNombre);
        p.setEquipo(nuevoEquipo);
        p.setRol(nuevoRol);
        p.setEstado(nuevoEstado);
    }

    // eliminar
    public void eliminar(Participante p) {
        lista.eliminarPorNombre(p.getNombre());
    }

    // obtener cabeza
    public NodoParticipante getCabeza() {
        return lista.getCabeza();
    }

    // buscar (recursivo)
    public List<Participante> buscar(String texto) {
        List<Participante> listaResultados = new ArrayList<>();
        lista.buscarRecursivoContiene(lista.getCabeza(), texto, listaResultados);
        return listaResultados;
    }
}

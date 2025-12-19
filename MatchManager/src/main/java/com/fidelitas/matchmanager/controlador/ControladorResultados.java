package com.fidelitas.matchmanager.controlador;

import com.fidelitas.matchmanager.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorResultados {

    private final PilaResultados pila;

    public ControladorResultados(PilaResultados pila) {
        this.pila = pila;
    }

    // registrar resultado (apilar)
    public void agregarResultado(Partido p, int golesA, int golesB) {
        Resultado r = new Resultado(p, golesA, golesB);
        pila.apilar(r);
    }

    // obtener lista de resultados en orden LIFO
    public List<String> obtenerLista() {
        List<String> lista = new ArrayList<>();
        NodoResultado nodo = pila.getCima();

        while (nodo != null) {
            Resultado r = nodo.getResultado();

            String linea =
                    r.getPartido().getEquipoA().getNombre() + " " + r.getGolesA() +
                    " - " +
                    r.getGolesB() + " " + r.getPartido().getEquipoB().getNombre();

            lista.add(linea);

            nodo = nodo.getSiguiente();
        }

        return lista;
    }
}

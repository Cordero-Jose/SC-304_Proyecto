package com.fidelitas.matchmanager.modelo;

/**
 * Representa el resultado de un partido jugado.
 */
public class Resultado {

    private Partido partido;
    private int golesA;
    private int golesB;

    public Resultado(Partido partido, int golesA, int golesB) {
        this.partido = partido;
        this.golesA = golesA;
        this.golesB = golesB;
    }

    public Partido getPartido() { return partido; }
    public int getGolesA() { return golesA; }
    public int getGolesB() { return golesB; }

    @Override
    public String toString() {
        return partido.getEquipoA().getNombre() + " " + golesA +
               " - " + golesB + " " +
               partido.getEquipoB().getNombre();
    }
}


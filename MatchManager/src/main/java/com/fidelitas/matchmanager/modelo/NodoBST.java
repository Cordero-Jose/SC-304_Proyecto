package com.fidelitas.matchmanager.modelo;

public class NodoBST {
    private String equipo;
    private int victorias;
    private NodoBST izquierda;
    private NodoBST derecha;

    public NodoBST(String equipo, int victorias) {
        this.equipo = equipo;
        this.victorias = victorias;
        this.izquierda = null;
        this.derecha = null;
    }

    public String getEquipo() { return equipo; }
    public int getVictorias() { return victorias; }
    public void setVictorias(int v) { victorias = v; }

    public NodoBST getIzquierda() { return izquierda; }
    public NodoBST getDerecha() { return derecha; }
    public void setIzquierda(NodoBST izq) { izquierda = izq; }
    public void setDerecha(NodoBST der) { derecha = der; }
}

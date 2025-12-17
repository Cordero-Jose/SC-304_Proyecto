/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

// Importamos la herramienta de Java para manejar fechas.
// 'LocalDate' es un tipo de dato que guarda solo la fecha
import java.time.LocalDate;

/**
 * Esta clase Evento es una "Clase Modelo"
 *
 * Esta clase no es un evento si no como la base o estrcutura que va a tener cada evento creadoe en la app 
 * osea por medio de esta clase se exige que cada evento tenga: un 'nombre', una 'ubicacion' y una 'fecha'
 * esto obligatoriamente para que se pueda crear
 *
 * En las Vistas (como 'VistaDashboard'):
 * La tabla del dashboard recibe una lista de estos "objetos" Evento
 * y usa métodos (getNombre(), getFecha()) para sacarles la
 * información y mostrarla en las columnas de la ventana 
 */
public class Evento {

  
    
    /*
     * private claramente para proteger los datos
     * La única forma de ponerle datos es al momento de crearlo (con el Constructor)
     * y la única forma de leerlos es con los "Getters" (más abajo).
     * Esto hace que el código sea mucho más ordenado y seguro.
     */
    private String nombre; // Para guardar el nombre
    private String ubicacion; // Para guardar dónde es
    private LocalDate fecha; // Para guardar la fecha del evento.

    /**
     * Evento (Constructor):
     * Este es el método especial que se llama cada que se usa 
     * "new Evento(...)"
     * Es, básicamente, para crear los eventos
     *
     * @param nombre El nombre del nuevo evento.
     * @param ubicacion La ubicación 
     * @param fecha La fecha 
     */
    public Evento(String nombre, String ubicacion, LocalDate fecha) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
    }

    // Getters( para leer los datos):
    /**
     * Permite a otras clases preguntar, cuál es el nombre del evento
     * @return El nombre (un String) que está guardado en 'this.nombre'.
     */
    public String getNombre() { return nombre; }

    /**
     * Permite a otras clases preguntar, dónde es el evento
     * @return La ubicación (un String) que está guardada en 'this.ubicacion'.
     */
    public String getUbicacion() { return ubicacion; }

    /**
     * Permite a otras clases preguntar, cuándo es este evento
     * @return La fecha (un LocalDate) que está guardada en 'this.fecha'.
     */
    public LocalDate getFecha() { return fecha; }
}
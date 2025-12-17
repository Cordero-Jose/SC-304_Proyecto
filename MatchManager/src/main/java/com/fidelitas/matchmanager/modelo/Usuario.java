/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.modelo;

/**
 * clase usuario: 
 * Esta es otra Clase Modelo, igual que la clase 'Evento'.
 *
 * es practicamente lo mismo que en la clase de evento pero ahora con los usuarios
 * esta exige que cada usuario tenga: un 'nombre', un 'correo', un 'rol'
 * y un 'estado'" para que se pueda crear
 */
public class Usuario {
    
    /*
    de la misma forma es private para cuidar los datos
     * - Los datos solo se pueden poner al momento de crear el objeto (con el "Constructor").
     * - Los datos solo se pueden leer con los "Getters" (más abajo).
     */
    private String nombre; // Para guardar el nombre del usuario
    private String correo; // Para guardar el correo, ej: "fmora@ufide.ac.cr"
    private String rol; // Para guardar el rol
    private String estado; // Para guardar si está Activo o Inactivo

    /**
     * (Constructor):
     * exactamente la misma funcionalidad de la clase evento
     * @param nombre El nombre que queremos darle al nuevo usuario.
     * @param correo El correo que le vamos a asignar.
     * @param rol El rol que va a tener.
     * @param estado El estado inicial
     */
    public Usuario(String nombre, String correo, String rol, String estado) {
        /*
         * 'this.nombre' se refiere al atributo "privado" de la clase (el de arriba).
         * 'nombre' se refiere al parámetro que nos llegó por el constructor.
         */
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
    }

    //Getters (leer los datos):
    
    /*
     * proposito de estos:
     * - Para el atributo "nombre", el getter DEBE ser "getNombre()".
     * - Para el atributo "correo", el getter DEBE ser "getCorreo()".
     *
     * Si les cambiamos el nombre (ej: "obtenerElNombre()"), la tabla de JavaFX
     * no va a encontrar los datos y se va a ver vacía.
     */
    
    /**
     * Permite a la tabla preguntar el nombre
     * @return El nombre (String) guardado en 'this.nombre'.
     */
    public String getNombre() { return nombre; }

    /**
     * Permite a la tabla preguntar Cuál es el correo
     * @return El correo (String) guardado en 'this.correo'.
     */
    public String getCorreo() { return correo; }

    /**
     * Permite a la tabla preguntar Cuál es tu rol
     * @return El rol (String) guardado en 'this.rol'.
     */
    public String getRol() { return rol; }

    /**
     * Permite a la tabla preguntar Cuál es el estado
     * @return El estado (String) guardado en 'this.estado'.
     */
    public String getEstado() { return estado; }
}
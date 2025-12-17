/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.controlador;

// Importamos las clases del Modelo (osea la de Evento y Usuario).
// Estas son las plantillas que definen qué datos tiene un Usuario o un Evento.
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.Usuario;
//  herramientas de Java para manejar fechas y listas.
import java.time.LocalDate; // Para manejar fechas 
import java.util.ArrayList; // El tipo de lista a usar
import java.util.List; // La plantilla para la lista 

/**
 * esta clase es solo un borrador, todavía no tenemos implementada la parte de guardar y leer datos
 * de verdad ( Listas Enlazadas, Árboles o archivos JSON), asi que es solo para ponerle datos a la app
 *
 * (Si no tuviéramos esta clase, las tablas y las listas en nuestras
 * ventanas saldrían vacías)
 *
 * Obvio en el proyecto final nada de esto va a existir. este es solo como el boceto
 */
public class ServicioDatosSimulados {

    
    // Simula que fue a la base de datos y trajo una lista de usuarios.
    public List<Usuario> obtenerUsuariosPrueba() {
        
        // lista vacía
        List<Usuario> lista = new ArrayList<>();
        
        // Estos son los datos ¨falsos¨ que usamos para probar.
        // Lo importante es que coincidan con los que pusimos en el
        // reporte escrito, para que el profe vea
        // que la app hace lo que el reporte describe
        // Simplemente creamos nuevos objetos "Usuario" y los metemos
        // a la lista uno por uno.
        lista.add(new Usuario("Fabian Mora", "fmora@ufide.ac.cr", "Admin", "Activo"));
        lista.add(new Usuario("Axel Segura", "asegura@ufide.ac.cr", "Editor", "Activo"));
        lista.add(new Usuario("Jennessy Abarca", "jabarca@ufide.ac.cr", "Espectador", "Inactivo"));
        lista.add(new Usuario("Eduardo Cordero", "jcordero@ufide.ac.cr", "Admin", "Activo"));
        
        // Devolvemos la lista llena. Cualquier clase que llame a este método
        // recibirá estos 4 usuarios.
        return lista;
    }

    // Simula que fue a la base de datos y trajo una lista de eventos.
    public List<Evento> obtenerEventosPrueba() {
        // Igual que arriba, creamos una lista vacía para los eventos
        List<Evento> lista = new ArrayList<>();
        
        // Aquí creamos eventos falsos para llenar la tabla de eventos.
        //
        // NOTA:
        // Usamos 'LocalDate.now().plusDays(X)' para que las fechas
        // de los eventos de prueba no se venzan.
        // 'LocalDate.now()' agarra la fecha de "hoy" (la de la compu)
        // y '.plusDays(10)' le suma 10 días.
        // Así, la demo siempre va a mostrar eventos
        // en el futuro
        lista.add(new Evento("Torneo Nacional de Futbol", "Estadio Nacional", LocalDate.now().plusDays(10)));
        lista.add(new Evento("Carrera de Atletismo", "La Sabana", LocalDate.now().plusDays(5)));
        lista.add(new Evento("Campeonato de Voleibol", "Gimnasio Nacional", LocalDate.now().plusDays(20)));
        
        // Devolvemos la lista de eventos.
        return lista;
    }
    
    /**
     * validarCredenciales:
     * Simula la validación de un inicio de sesión.
     * Esto es temporal para poder pasar de la VistaLogin al VistaDashboard
     *
     * @param correo El correo que escribió el usuario 
     * @param password La contraseña que escribió el usuario.
     * @return 'true' (verdadero) si el login es "correcto", 'false' (falso) si no.
     */
    
    // Método simple para validar login (Hardcodeado para el avance)
    public boolean validarCredenciales(String correo, String password) {
        
        // OJO: Esta es la validación de mentira.
        //
        // Para este avance, estamos aceptando que el login sea exitoso
        // siempre y cuando el correo tenga un "@" (para que parezca correo)
        // Y (&&) la contraseña sea exactamente "123".
        //
        // En el futuro, aquí es donde iríamos a la lista de usuarios
        // (o al árbol) a buscar si el correo existe y si el password es correcto.
        
        return correo.contains("@") && password.equals("123");
    }
}
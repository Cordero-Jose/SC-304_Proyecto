/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager;

// Importaciones necesarias de JavaFX y para la aplicación.
import com.fidelitas.matchmanager.vista.VistaLogin; // primero importar clase VistaLogin, Esta es la primera ventana del programa que va a ver el usuario.
import javafx.application.Application; // Esta es la clase de JavaFX.
import javafx.stage.Stage; // El Stage es el contenedor principal de la interfaz gráfica, osea donde van a ir la ventana y demás elementos ( botones,minimizar, maximizar, cerrar etc).

/**
 * Esta clase main sirve como el punto de entrada para la aplicación JavaFX.
 * que es donde la aplicación inicializa la primera interfaz gráfica (GUI).
 *lo del {@code App}(Launcher), esta solamente para que el codigo arranque
 */
public class App extends Application {
    // Esta clase extiende 'Application' para que sea reconocida por JavaFX como
    // el punto de inicio de la interfaz gráfica.

    @Override
    public void start(Stage stage) {
        VistaLogin login = new VistaLogin(stage);
        login.mostrar();
    }

    public static void main(String[] args) {
        launch();
    }
}
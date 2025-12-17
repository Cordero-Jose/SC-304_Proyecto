/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.vista;

// Importamos el "ServicioDatosSimulados" (base de datos de prueba)
import com.fidelitas.matchmanager.controlador.ServicioDatosSimulados;
// Importaciones de JavaFX (las herramientas para la ventana)
import javafx.geometry.Pos; // Para centrar cosas (Pos.CENTER)
import javafx.scene.Scene; // La "escena", o sea, el contenido que va DENTRO de la ventana.
import javafx.scene.control.*; // Botones, campos de texto, etiquetas, etc.
import javafx.scene.layout.VBox; // Un "contenedor" que apila cosas verticalmente (una encima de otra).
import javafx.scene.text.Font; // Para cambiar el tipo de letra.
import javafx.scene.text.FontWeight; // Para poner la letra en "negrita".
import javafx.stage.Stage; // El "Stage" es la ventana principal (el marco).

/**
 *La clase VistaLogin es la primera pantalla que aparece cuando el usuario abre la app. 
 * Su trabajo es mostrar el formulario de inicio de sesión y verificar que los datos sean correctos.
 * 
 * Está hecha con una VBox como fondo gris y otra VBox encima que funciona como la tarjetita blanca donde van los campos y el botón
 * La crea App.java, que también le pasa la ventana principal. Además, VistaLogin usa ServicioDatosSimulados para revisar las credenciales.
 * 
 * Si el login funciona, esta clase crea la VistaDashboard y le pasa la misma ventana para que reemplace la pantalla de login por el panel principal.
 */
public class VistaLogin {
    
    // Atributos (Variables):
    // El 'Stage' es la ventana principal. vista en clase 'App'
    // sirve para:
    // 1. Poner nuestra "escena" (el login)
    // 2. Pasar a la 'VistaDashboard' si el login es exitoso.
    private final Stage stage;
    
    // Esta es nuestra "base de datos de prueba¨. La vamos a usar
    // para preguntarle si el usuario y la clave son correctos.
    private final ServicioDatosSimulados controlador;

    /**
     * Este método VistaLogin se llama automáticamente cuando la clase 'App'
     * hace: "new VistaLogin(stage)".
     * Su trabajo es inicializar las variables de la clase.
     * @param stage La ventana principal (vacía) que nos manda 'App.java'.
     */
    public VistaLogin(Stage stage) {
        // "this.stage" (el de la clase) = "stage" (el que pasa)
        this.stage = stage;
        
        // Creamos una nueva instancia
        // y la guardamos en nuestra variable 'controlador' para usarla más adelante en el botón de "Ingresar".
        this.controlador = new ServicioDatosSimulados();
    }

    /**
     * MÉTODO: mostrar():
     * Este es el método que dibuja todos los componentes (botones,
     * campos de texto, etc.) en la pantalla.
     * La clase 'App' llama a este método justo después de crearla
     */
    public void mostrar() {
        
        //Fondo General
        VBox mainLayout = new VBox();
        // 'Pos.CENTER' le dice a la VBox que centre *todo* su contenido

        mainLayout.setAlignment(Pos.CENTER);
        // Le ponemos el color de fondo gris claro (el mismo del prototipo en PDF).
        mainLayout.setStyle("-fx-background-color: #f4f6f8;"); 

        //Tarjeta Blanca del Login
        // Creamos OTRA 'VBox'. Esta va a ser la tarjeta blanca
        // que va *adentro* del 'mainLayout' gris.
        VBox loginCard = new VBox(20); 
        loginCard.setMaxWidth(400); // Le ponemos un ancho máximo de 400px.
        loginCard.setAlignment(Pos.CENTER); // Centramos el contenido
        
        // Aquí le ponemos el estilo CSS a mano.
        loginCard.setStyle(
            "-fx-background-color: white;" + // Fondo blanco
            "-fx-background-radius: 10;" + // Bordes redondeados
            "-fx-padding: 40;" + // 40px de relleno o margen 
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);" // sombrita
        );

        //Componentes (Etiquetas, Campos, Botón)

        // Títulos
        Label lblTitulo = new Label("Match Manager");
        lblTitulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28)); // Letra grande y negrita
        lblTitulo.setStyle("-fx-text-fill: #2c3e50;"); // Color de letra azul oscuro

        Label lblSub = new Label("Bienvenido a Match Manager\nSistema de gestión de eventos");
        lblSub.setStyle("-fx-text-alignment: CENTER; -fx-text-fill: #7f8c8d; -fx-font-size: 14px;"); // Subtítulo gris

        // Campos de texto
        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo Institucional");
        txtCorreo.setPrefHeight(40); // Altura de 40px
        txtCorreo.setStyle("-fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;"); // Bordes

        // 'PasswordField' es especial: es igual a un 'TextField' pero
        // automáticamente pone los asteriscos (•) cuando escribes la clave.
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setPrefHeight(40);
        txtPassword.setStyle("-fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

        // Botón Azul
        Button btnEntrar = new Button("INGRESAR");
        btnEntrar.setPrefWidth(400); // Que ocupe todo el ancho de la tarjeta
        btnEntrar.setPrefHeight(40);
        btnEntrar.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        // estilo CSS como texto
        String estiloBotonNormal = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand;";
        String estiloBotonHover = "-fx-background-color: #2980b9; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand;"; // azul más oscuro
        
        btnEntrar.setStyle(estiloBotonNormal); 
        

        btnEntrar.setOnMouseEntered(e -> btnEntrar.setStyle(estiloBotonHover));
        btnEntrar.setOnMouseExited(e -> btnEntrar.setStyle(estiloBotonNormal));
        
        btnEntrar.setOnAction(e -> {
            // Cuando le hacen clic:
            // 1. Agarramos el texto del campo correo (txtCorreo.getText())
            // 2. Agarramos el texto del campo clave (txtPassword.getText())
            // 3. Se los mandamos a nuestra "base de datos" (controlador)
            //    al método 'validarCredenciales'.
            if (controlador.validarCredenciales(txtCorreo.getText(), txtPassword.getText())) {
                
                // SI el 'controlador' nos devuelve 'true'
                // ¡LOGIN EXITOSO!
                
                // 1. Creamos una *nueva* instancia de la 'VistaDashboard'.
                // 2. Le pasamos el 'stage'
                // 3. Llamamos al método 'mostrar()' del Dashboard.
        
                new VistaDashboard(stage).mostrar();
                
            } else {
                
                // si el 'controlador' devuelve 'false'...
                // ¡LOGIN FALLIDO!
                
                // Creamos una 'Alert' (una ventanita de error)
                Alert alert = new Alert(Alert.AlertType.ERROR, "Credenciales inválidas (Pista: usa pass: 123)");
                alert.show(); // Mostramos la ventanita de error.
            }
        });

        //5.Armado Final de la Ventana
        
        // Metemos TODOS los componentes (títulos, campos, botón)
        // DENTRO de la 'loginCard' (la tarjeta blanca), en orden.
        loginCard.getChildren().addAll(lblTitulo, lblSub, txtCorreo, txtPassword, btnEntrar);
        
        // Metemos la 'loginCard' (la tarjeta blanca, que ya tiene todo adentro)
        // DENTRO del 'mainLayout' (el fondo gris).
        mainLayout.getChildren().add(loginCard);

        // Creamos la "Escena" (el contenido) y le pasamos
        // el 'mainLayout', que es el contenedor que tiene todo
        Scene scene = new Scene(mainLayout, 1024, 768); // Tamaño de la ventana: 1024x768
        
        stage.setScene(scene);
        stage.setTitle("Login - Match Manager"); // El título de la barra de la ventana
        
        // y ya por fin por ultimo
        // Esta linea le dice a JavaFX que todo esta correcto y que muestre la ventana 
        stage.show();
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.matchmanager.vista;

// --- Importaciones Clave ---
// Importamos nuestra "base de datos de mentira". La vamos a necesitar
// muchísimo en esta ventana para rellenar las tablas y listas.
import com.fidelitas.matchmanager.controlador.ServicioDatosSimulados;
// Importamos los "moldes" de Evento y Usuario. Los necesitamos para que
// las tablas (como la de Usuarios) sepan qué forma tienen los datos.
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.Usuario;
import java.util.List; 
// Importaciones de JavaFX (las herramientas para las ventanas)
import javafx.geometry.Insets; // Para manejar márgenes y 'padding' (relleno)
import javafx.geometry.Pos; // Para alinear cosas (ej: centrar texto)
import javafx.scene.Scene; // La "escena", o sea, el contenido de la ventana
import javafx.scene.control.*; // Botones, Etiquetas, Tablas, etc.
import javafx.scene.control.cell.PropertyValueFactory; // ¡El "pegamento" mágico para las tablas!
import javafx.scene.layout.*; // Los "contenedores" (VBox, HBox, BorderPane, etc.)
import javafx.scene.text.Font; // Para cambiar el tipo de letra
import javafx.scene.text.FontWeight; // Para poner letra en "negrita"
import javafx.stage.Stage; // La "ventana" principal de la aplicación

/**
 * La clase VistaDashboard es básicamente la ventana principal que ve el usuario después de iniciar sesión. 
 * Es como el “escritorio” desde donde puede acceder a todo lo demás dentro del sistema
 * 
 * Para armar esta pantalla, la clase usa un BorderPane, que es un tipo de diseño que divide la interfaz en 
 * cinco partes: arriba, abajo, izquierda, derecha y el centro
 * 
 * la parte izquierda se usa para colocar el menú lateral, donde están los botones para navegar. 
 * Mientras tanto, la parte del centro es donde se van mostrando las diferentes pantallas del sistema, como el resumen, los usuarios o los eventos.
 */
public class VistaDashboard {

    // El 'Stage' es la ventana principal. La guardamos aquí
    // porque la 'VistaLogin' nos la pasó.
    // es para poder cerrar sesión y volver a mostrar el login.
    private final Stage stage;
    
    // es el principal que tendrá el menú a la izquierda y el contenido al centro.
    private final BorderPane layoutPrincipal;
    
    private final ServicioDatosSimulados servicioDatos;

    // Definición de Colores:
    // Definimos los colores aquí como "constantes" (final String).
    // Si el día de mañana queremos cambiar
    // el azul del menú por un verde, solo lo cambiamos aquí
    // y se arregla en toda la ventana, en lugar de buscarlo
    // línea por línea.
    private final String COLOR_MENU_FONDO = "#2c3e50"; // azul oscuro
    private final String COLOR_FONDO_GENERAL = "#f4f6f8"; // gris muy claro
    // Este es un estilo CSS (como de página web) 
    private final String ESTILO_CARD = "-fx-background-color: white; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2); -fx-padding: 20;";

    /**
     * @param stage La ventana principal (Stage) que nos pasa la vista anterior.
     */
    public VistaDashboard(Stage stage) {
        // "this.stage" (el de la clase) = "stage" (el que nos pasaron)
        this.stage = stage;
        
        // Creamos la "base de datos falsa" y la dejamos lista para usar.
        this.servicioDatos = new ServicioDatosSimulados();
        
        // Creamos el BorderPane que va a contener todo.
        this.layoutPrincipal = new BorderPane();
    }

    /**
     * MÉTODO: mostrar()
     * Este es el método que "dibuja" todo.
     * Configura el menú, pone la pantalla de inicio por defecto
     * y le dice a la ventana (stage) que muestre esta nueva "escena"
     */
    public void mostrar() {
        // MENÚ LATERAL:
        // 'VBox' es un contenedor que apila las cosas verticalmente.
        // El '10' es el espacio (en píxeles) que habrá entre cada botón.
        VBox menuLateral = new VBox(10);
        menuLateral.setPrefWidth(250); //ancho fijo de 250px.
        menuLateral.setPadding(new Insets(20, 0, 0, 0)); // Margen: 20 arriba, 0 lo demás.
        menuLateral.setStyle("-fx-background-color: " + COLOR_MENU_FONDO + ";"); // color azul oscuro.

        //este es el logo
        Label lblLogo = new Label("Match Manager");
        lblLogo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20)); // Letra grande y negrita
        lblLogo.setStyle("-fx-text-fill: white; -fx-padding: 0 0 20 20;"); // Letra blanca y un margen

        // Creamos los botones del menú usando
        // 'crearBotonMenu' (está más abajo).
        Button btnInicio = crearBotonMenu("Dashboard / Resumen");
        Button btnEventos = crearBotonMenu("Eventos");
        Button btnUsuarios = crearBotonMenu("Usuarios");
        Button btnAjustes = crearBotonMenu("Ajustes");
        Button btnSalir = crearBotonMenu("Cerrar Sesión");

        // NAVEGACIÓN:
        // 'setOnAction' es un "listener"
        // ejecuta el código que está después de la flechita (->)".
        
        // Cuando hagan clic en "Inicio", llamamos al método que
        // "dibuja" la pantalla de resumen en el centro.
        btnInicio.setOnAction(e -> mostrarPantallaInicio());
        btnEventos.setOnAction(e -> mostrarPantallaEventos());
        btnUsuarios.setOnAction(e -> mostrarPantallaUsuarios());
        btnAjustes.setOnAction(e -> mostrarPantallaAjustes());
        
        // OJO:
        // Cuando le damos "Cerrar Sesión", lo que hace es
        // 1. Crear una *nueva* instancia de 'VistaLogin'.
        // 2. Le pasa el 'stage' (la MISMA ventana que estamos usando).
        // 3. Llama a 'login.mostrar()'.
        // 'VistaLogin' se va a encargar de poner su escena
        // en *nuestro* 'stage', reemplazando el dashboard por el login.
        btnSalir.setOnAction(e -> new VistaLogin(stage).mostrar());

        // 'new Separator()' es solo la rayita gris que separa los botones.
        // Añadimos todo al 'menuLateral' 
        menuLateral.getChildren().addAll(lblLogo, btnInicio, btnEventos, btnUsuarios, btnAjustes, new Separator(), btnSalir);
        

        // "Pone el 'menuLateral' que acabamos de crear en la zona izquierda".
        layoutPrincipal.setLeft(menuLateral);
        
        mostrarPantallaInicio();
        Scene scene = new Scene(layoutPrincipal, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Match Manager - Panel Principal");
        stage.centerOnScreen(); // Centramos la ventana en el monitor.

    }

    /**
     * MÉTODO: crearBotonMenu
     * @param texto El texto que va a llevar el botón.
     * @return Un objeto 'Button' ya estilizado 
     */
    private Button crearBotonMenu(String texto) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE); // Hace que el botón se estire al ancho del menú (250px)
        btn.setAlignment(Pos.BASELINE_LEFT); // Alinea el texto a la izquierda
        btn.setPadding(new Insets(10, 10, 10, 20)); // Margen interno
        btn.setFont(Font.font("Segoe UI", 14)); // Tipo de letra
        
        // Estilo normal: fondo transparente, letra gris
        String estiloNormal = "-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-cursor: hand;";
        // Estilo hover: fondo azul más claro, letra blanca
        String estiloHover = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-cursor: hand;";
        
        btn.setStyle(estiloNormal); // Le ponemos el estilo normal al empezar.

        // "Cuando el mouse ENTRE en el área del botón..."
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        // "Cuando el mouse SALGA del área del botón..."
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));
        
        return btn; // Devolvemos el botón ya configurado.
    }

    private void mostrarPantallaInicio() {
        // 'VBox' para apilar verticalmente: Título, Fila de Tarjetas, Noticias
        VBox panel = new VBox(20); // 20px de espacio entre elementos
        panel.setPadding(new Insets(30)); // 30px de margen por todos lados
        panel.setStyle("-fx-background-color: " + COLOR_FONDO_GENERAL + ";"); // Fondo gris claro

        Label titulo = new Label("Resumen General");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setStyle("-fx-text-fill: #2c3e50;");

        // 'HBox' (Caja Horizontal) para poner las tarjetas una al lado de la otra.
        HBox filaCards = new HBox(20); // 20px de espacio entre tarjetas
        filaCards.getChildren().addAll(
            // Llamamos a nuestro "ayudante" 'crearCard' varias veces.
            // Todos estos datos son "harcodeados" (falsos), solo para la demo.
            crearCard("Eventos Activos", "12"),
            crearCard("Equipos Registrados", "48"),
            crearCard("Partidos esta semana", "27"),
            crearCard("Nuevos Usuarios", "5")
        );

        // Sección de "Noticias" (más datos falsos)
        Label lblNoticias = new Label("Noticias Internas");
        lblNoticias.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        lblNoticias.setStyle("-fx-text-fill: #2c3e50;");
        
        VBox noticiasBox = new VBox(10);
        noticiasBox.setStyle(ESTILO_CARD); // Reutilizamos el estilo de tarjeta blanca con sombra
        noticiasBox.getChildren().addAll(
            new Label("• Se ha actualizado el módulo de reportes."),
            new Label("• Nueva vista de calendario disponible."),
            new Label("• Próxima capacitación: Nov 2025.")
        );

        // Metemos todo en el panel vertical
        panel.getChildren().addAll(titulo, filaCards, new Separator(), lblNoticias, noticiasBox);
        
        // "Pone este 'panel' que acabamos de crear en tu zona del centro".
        layoutPrincipal.setCenter(panel);
    }

    /**
     * MÉTODO: crearCard 
     * Crea una de las tarjetas blancas (VBox) con el número grande
     * y el título pequeño.
     *
     * @param titulo El texto de abajo
     * @param numero El texto grande de arriba
     * @return Una 'VBox' estilizada 
     */
    private VBox crearCard(String titulo, String numero) {
        VBox card = new VBox(10); // Caja vertical para el número y el título
        card.setMinWidth(200); // Ancho mínimo
        card.setStyle(ESTILO_CARD); // Reutilizamos el estilo de tarjeta blanca
        
        Label lblNum = new Label(numero);
        lblNum.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36)); // Número grande
        lblNum.setStyle("-fx-text-fill: #2c3e50;");
        
        Label lblTit = new Label(titulo);
        lblTit.setFont(Font.font("Segoe UI", 14)); // Título pequeño
        lblTit.setStyle("-fx-text-fill: #7f8c8d;"); // Color gris
        
        card.getChildren().addAll(lblNum, lblTit);
        return card;
    }

    /**
     * MÉTODO: mostrarPantallaUsuarios()
     * "Dibuja" la pantalla de gestión de usuarios (la de la tabla)
     * y la pone en el CENTRO del 'layoutPrincipal'.
     */
    private void mostrarPantallaUsuarios() {
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(30));
        panel.setStyle("-fx-background-color: " + COLOR_FONDO_GENERAL + ";");

        Label titulo = new Label("Gestión de Usuarios");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setStyle("-fx-text-fill: #2c3e50;");

        // 1. CREACIÓN DE LA TABLA
        TableView<Usuario> tabla = new TableView<>();
        // Esta hace que las columnas se ajusten al ancho de la tabla
        // y no aparezca una barra de scroll horizontal.
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setStyle("-fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        
        // 2. CREACIÓN DE LAS COLUMNAS
        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        // Columna "Rol"
        TableColumn<Usuario, String> colRol = new TableColumn<>("Rol");
        // ...esta buscará el método 'getRol()' en Usuario.java
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));

        // Columna "Correo"
        TableColumn<Usuario, String> colCorreo = new TableColumn<>("Correo");
        // ...esta buscará el método 'getCorreo()' en Usuario.java
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Columna "Estado"
        TableColumn<Usuario, String> colEstado = new TableColumn<>("Estado");
        // ...esta buscará el método 'getEstado()' en Usuario.java
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // 3. AÑADIR COLUMNAS Y DATOS A LA TABLA
        
        // Le metemos todas las columnas que creamos a la tabla.
        tabla.getColumns().addAll(colNombre, colRol, colCorreo, colEstado);
        
        tabla.getItems().addAll(servicioDatos.obtenerUsuariosPrueba());

        // Este 'VBox' mostrará los detalles del usuario que seleccionemos.
        VBox detalle = new VBox(5);
        detalle.setStyle(ESTILO_CARD); // Reutilizamos el estilo de tarjeta
        detalle.getChildren().addAll(
            new Label("Seleccione un usuario de la tabla para ver más detalles...")
        );

        // 5. LISTENER DE LA TABLA
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // 'obs' = el observador (no lo usamos)
            // 'oldSelection' = el usuario que ESTABA seleccionado (no lo usamos)
            // 'newSelection' = el usuario que el usuario ACABA de seleccionar
            
            // Si el usuario seleccionó algo (y no hizo clic en 'nada')
            if (newSelection != null) {
                // 1. Borramos lo que sea que había antes en la cajita de 'Detalle'.
                detalle.getChildren().clear();
                
                // 2. Creamos las nuevas etiquetas con la info del 'newSelection'.
                Label lblDetalleTitulo = new Label("Detalle de: " + newSelection.getNombre());
                lblDetalleTitulo.setFont(Font.font(16));
                lblDetalleTitulo.setStyle("-fx-font-weight: bold;");
                
                // 3. Metemos la info nueva. Usamos los 'getters' del objeto 'Usuario'
                //    (ej: newSelection.getRol()) para sacar la info.
                detalle.getChildren().addAll(
                    lblDetalleTitulo,
                    new Label("Rol: " + newSelection.getRol()),
                    new Label("Correo: " + newSelection.getCorreo()),
                    new Label("Última conexión: 29/10/2025") // Dato falso
                );
            }
        });

        // 6. MONTAJE FINAL
        // Metemos el título, la tabla y el panel de detalle en el panel principal
        panel.getChildren().addAll(titulo, tabla, new Label("Detalles:"), detalle);
        
        // Y por último, ponemos este panel en el CENTRO del esqueleto.
        layoutPrincipal.setCenter(panel);
    }

    /**
     * MÉTODO: mostrarPantallaEventos()
     * "Dibuja" la pantalla de Eventos
     * OJO: A diferencia de la de Usuarios, esta pantalla no usa una 'Tabla'
     * (TableView) sino una 'Lista simple' (ListView).
     */
    private void mostrarPantallaEventos() {
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(30));
        panel.setStyle("-fx-background-color: " + COLOR_FONDO_GENERAL + ";");
        
        Label titulo = new Label("Eventos Próximos");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setStyle("-fx-text-fill: #2c3e50;");
        
        // Creamos una 'ListView'. Esta solo muestra filas de texto (String).
        ListView<String> listaVisual = new ListView<>();
        listaVisual.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        
        List<Evento> misEventos = servicioDatos.obtenerEventosPrueba();
        
        if (misEventos.isEmpty()) {
            listaVisual.getItems().add("No hay eventos registrados.");
        } else {
            // 'for (Evento ev : misEventos)' significa:
            // "Por cada 'Evento' (que llamaremos 'ev') dentro de la lista 'misEventos'..."
            for (Evento ev : misEventos) {
                // 3. "Construimos" el texto (String) a mano.
                // Agarramos cada evento ('ev') y le sacamos los datos con
                // los 'getters' (ev.getNombre(), ev.getUbicacion(), etc.)
                // y los pegamos todos en un solo 'String' con emojis.
                String fila = "🏆 " + ev.getNombre() + "   |   📍 " + ev.getUbicacion() + "   |   📅 " + ev.getFecha();
                
                // 4. Metemos el 'String' que acabamos de crear como una
                // nueva fila en la lista visual.
                listaVisual.getItems().add(fila);
            }
        }
        
        panel.getChildren().addAll(titulo, new Label("Lista de eventos cargada del sistema:"), listaVisual);
        
        // 5. Ponemos este panel en el CENTRO del esqueleto.
        layoutPrincipal.setCenter(panel);
    }

    /**
     * MÉTODO: mostrarPantallaAjustes()
     * "Dibuja" la pantalla de Configuración.
     * los botones y menús no hacen nada, solo están ahí para que la pantalla no se vea vacía.
     */
    private void mostrarPantallaAjustes() {
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(30));
        panel.setStyle("-fx-background-color: " + COLOR_FONDO_GENERAL + ";");

        Label titulo = new Label("Configuración del Sistema");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setStyle("-fx-text-fill: #2c3e50;");

        // Usamos un 'GridPane'(cuadrícula)
        // para poner cosas por fila y columna.
        GridPane grid = new GridPane();
        grid.setHgap(20); // Espacio horizontal entre columnas
        grid.setVgap(15); // Espacio vertical entre filas
        grid.setStyle(ESTILO_CARD); // Reutilizamos el estilo de tarjeta

        // Fila 0: Idioma
        // 'grid.add(nodo, columna, fila)'
        grid.add(new Label("Idioma:"), 0, 0); // (Col 0, Fila 0)
        grid.add(new ComboBox<>(javafx.collections.FXCollections.observableArrayList("Español", "Inglés")), 1, 0); // (Col 1, Fila 0)

        // Fila 1: Zona Horaria
        grid.add(new Label("Zona Horaria:"), 0, 1);
        grid.add(new ComboBox<>(javafx.collections.FXCollections.observableArrayList("América/Costa Rica", "UTC")), 1, 1);

        // Fila 2: Tema
        grid.add(new Label("Tema:"), 0, 2);
        grid.add(new ComboBox<>(javafx.collections.FXCollections.observableArrayList("Claro", "Oscuro")), 1, 2);
        
        // Fila 3: Separador
        grid.add(new Separator(), 0, 3, 2, 1);
        
        // Fila 4: Seguridad
        grid.add(new Label("Seguridad:"), 0, 4);
        Button btnPass = new Button("Cambiar Contraseña");
        grid.add(btnPass, 1, 4);
        
        // (Estos botones y ComboBox no están "conectados" a nada,
        // no tienen 'setOnAction', por eso son solo de adorno).

        panel.getChildren().addAll(titulo, grid);
        
        // Ponemos el panel de ajustes en el CENTRO
        layoutPrincipal.setCenter(panel);
    }
}

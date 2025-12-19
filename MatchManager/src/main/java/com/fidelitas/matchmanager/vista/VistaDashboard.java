package com.fidelitas.matchmanager.vista;

import com.fidelitas.matchmanager.controlador.Persistencia;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaDashboard {

    private final Stage stage;
    private final Usuario usuarioActual;

    private final BorderPane layoutPrincipal;

    private final ListaEventos listaEventos;  // lista cargada desde Persistencia

    private final String COLOR_MENU = "#2c3e50";

    public VistaDashboard(Stage stage, Usuario usuarioActual) {
        this.stage = stage;
        this.usuarioActual = usuarioActual;
        this.layoutPrincipal = new BorderPane();
        

        // Cargar datos reales una sola vez
        this.listaEventos = Persistencia.cargar();
        System.out.println("****************************");
        System.out.println("EVENTOS CARGADOS DESDE JSON:");
        System.out.println("Cantidad: " + listaEventos.getTamaño());

        NodoEvento n = listaEventos.getCabeza();
        while (n != null) {
    System.out.println(" - " + n.getDato().getNombre());
    n = n.getSiguiente();
}
System.out.println("****************************");

    }

    public void mostrar() {

        // ----- MENÚ -----
        VBox menu = new VBox(10);
        menu.setPrefWidth(250);
        menu.setPadding(new Insets(20));
        menu.setStyle("-fx-background-color: " + COLOR_MENU);

        Label lblLogo = new Label("Match Manager");
        lblLogo.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");

        Button btnInicio = crearBoton("Dashboard / Resumen");
        Button btnEventos = crearBoton("Eventos");
        Button btnParticipantes = crearBoton("Participantes");
        Button btnPartidos = crearBoton("Partidos");
        Button btnResultados = crearBoton("Resultados");
        Button btnClasificacion = crearBoton("Clasificación");
        Button btnGrafo = crearBoton("Grafo");
        Button btnAjustes = crearBoton("Ajustes");
        Button btnSalir = crearBoton("Cerrar Sesión");

        // --------- ROLES (R2) ---------
        if (usuarioActual.getRol().equalsIgnoreCase("Espectador")) {

            // Es espectador → solo puede ver datos, no editar nada

            btnAjustes.setVisible(false);     // NO ver Ajustes
            btnPartidos.setVisible(false);    // NO ver Partidos (admin-only)
            // NOTA:
            // Eventos, Participantes, Resultados, Clasificación y Grafo
            // deben estar visibles porque son consulta.
        }

        // -------- ACCIONES DEL MENÚ --------

        btnInicio.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaInicio().render()));

        btnEventos.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaEventos(listaEventos, usuarioActual).render()));

        btnParticipantes.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaParticipantes(listaEventos, usuarioActual).render()));

        btnPartidos.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaPartidos(listaEventos, usuarioActual).render()));

        btnResultados.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaResultados(listaEventos).render()));

        btnClasificacion.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaClasificacion(listaEventos).render()));

        btnGrafo.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaGrafo(listaEventos).render()));

        btnAjustes.setOnAction(e ->
                layoutPrincipal.setCenter(new VistaAjustes(listaEventos, usuarioActual).render()));

        btnSalir.setOnAction(e -> {
            Persistencia.guardar(listaEventos); // guardar al cerrar sesión
            new VistaLogin(stage).mostrar();
        });


        menu.getChildren().addAll(
                lblLogo,
                btnInicio,
                btnEventos,
                btnParticipantes,
                btnPartidos,
                btnResultados,
                btnClasificacion,
                btnGrafo,
                btnAjustes,
                new Separator(),
                btnSalir
        );

        layoutPrincipal.setLeft(menu);

        // pantalla inicial
        layoutPrincipal.setCenter(new VistaInicio().render());

        // escena
        Scene scene = new Scene(layoutPrincipal, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Match Manager - Panel Principal");
        stage.centerOnScreen();

        // Guardar automáticamente al cerrar la ventana
        stage.setOnCloseRequest(e2 -> Persistencia.guardar(listaEventos));
    }


    // Crear botón estilizado
    private Button crearBoton(String texto) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.BASELINE_LEFT);
        btn.setPadding(new Insets(10, 10, 10, 20));
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
        return btn;
    }
}

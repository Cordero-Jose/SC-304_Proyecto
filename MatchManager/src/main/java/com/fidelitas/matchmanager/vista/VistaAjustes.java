package com.fidelitas.matchmanager.vista;

import com.fidelitas.matchmanager.controlador.Persistencia;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VistaAjustes {

    private final ListaEventos listaEventos;
    private final Usuario usuarioActual;

    private final String COLOR_FONDO = "#f4f6f8";
    private final String ESTILO_CARD =
            "-fx-background-color: white; -fx-background-radius: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05),5,0,0,2);" +
            "-fx-padding: 20;";

    public VistaAjustes(ListaEventos listaEventos, Usuario usuarioActual) {
        this.listaEventos = listaEventos;
        this.usuarioActual = usuarioActual;
    }

    public VBox render() {

        VBox panel = new VBox(20);
        panel.setPadding(new Insets(30));
        panel.setStyle("-fx-background-color: " + COLOR_FONDO);

        Label titulo = new Label("Configuración del Sistema");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setStyle(ESTILO_CARD);

        // Fila 0: Idioma
        grid.add(new Label("Idioma:"), 0, 0);
        ComboBox<String> cbIdioma =
                new ComboBox<>(javafx.collections.FXCollections.observableArrayList("Español", "Inglés"));
        grid.add(cbIdioma, 1, 0);

        // Fila 1: Zona Horaria
        grid.add(new Label("Zona Horaria:"), 0, 1);
        ComboBox<String> cbZona =
                new ComboBox<>(javafx.collections.FXCollections.observableArrayList("América/Costa Rica", "UTC"));
        grid.add(cbZona, 1, 1);

        // Fila 2: Tema
        grid.add(new Label("Tema:"), 0, 2);
        ComboBox<String> cbTema =
                new ComboBox<>(javafx.collections.FXCollections.observableArrayList("Claro", "Oscuro"));
        grid.add(cbTema, 1, 2);

        // Separador
        grid.add(new Separator(), 0, 3, 2, 1);

        // Fila 4: Seguridad
        grid.add(new Label("Seguridad:"), 0, 4);
        Button btnPass = new Button("Cambiar Contraseña");
        grid.add(btnPass, 1, 4);

        // --- PERSISTENCIA ---
        grid.add(new Label("Guardar Datos:"), 0, 5);
        Button btnGuardar = new Button("Guardar");
        grid.add(btnGuardar, 1, 5);

        grid.add(new Label("Cargar Datos:"), 0, 6);
        Button btnCargar = new Button("Cargar");
        grid.add(btnCargar, 1, 6);

        // --- ROLES ---
        if (usuarioActual.getRol().equalsIgnoreCase("Espectador")) {
            btnGuardar.setDisable(true);
            btnCargar.setDisable(true);
            btnPass.setDisable(true);
        }

        // --- ACCIONES ---
        btnGuardar.setOnAction(e -> {
            Persistencia.guardar(listaEventos);
            mostrarAlerta("Datos guardados correctamente.");
        });

        btnCargar.setOnAction(e -> {
            ListaEventos cargados = Persistencia.cargar();
            listaEventos.copiarDesde(cargados);  // método que vamos a crear en segundos
            mostrarAlerta("Datos cargados correctamente.");
        });

        panel.getChildren().addAll(titulo, grid);
        return panel;
    }

    private void mostrarAlerta(String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}

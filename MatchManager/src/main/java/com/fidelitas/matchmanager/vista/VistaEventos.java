package com.fidelitas.matchmanager.vista;

import java.time.LocalDate;

import com.fidelitas.matchmanager.controlador.ControladorEventos;
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaEventos {

    private final ListaEventos listaEventos;
    private final Usuario usuarioActual;
    private final ControladorEventos controladorEventos;

    private final String COLOR_FONDO = "#f4f6f8";
    private final String ESTILO_CARD =
            "-fx-background-color: white; -fx-background-radius: 8; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2); -fx-padding: 20;";

    public VistaEventos(ListaEventos listaEventos, Usuario usuarioActual) {
        this.listaEventos = listaEventos;
        this.usuarioActual = usuarioActual;
        this.controladorEventos = new ControladorEventos(listaEventos);
    }

    public VBox render() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + COLOR_FONDO);

        Label titulo = new Label("Gestión de Eventos");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        // TABLA
        TableView<Evento> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setStyle(ESTILO_CARD);

        TableColumn<Evento, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));

        TableColumn<Evento, String> colUbicacion = new TableColumn<>("Ubicación");
        colUbicacion.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUbicacion()));

        TableColumn<Evento, LocalDate> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getFecha())
        );

        tabla.getColumns().addAll(colNombre, colUbicacion, colFecha);

        recargarTabla(tabla);

        // FORMULARIO CRUD
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtUbicacion = new TextField();
        txtUbicacion.setPromptText("Ubicación");

        DatePicker dpFecha = new DatePicker();
        dpFecha.setPromptText("Fecha");

        Button btnAgregar = new Button("Agregar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");

        HBox form = new HBox(10, txtNombre, txtUbicacion, dpFecha, btnAgregar, btnActualizar, btnEliminar);

        // ---- ROLES (R2)
        if (usuarioActual.getRol().equalsIgnoreCase("Espectador")) {
            btnAgregar.setDisable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);
        }

        // ---- CRUD ADMIN
        btnAgregar.setOnAction(e -> {

            if (txtNombre.getText().isEmpty() ||
                txtUbicacion.getText().isEmpty() ||
                dpFecha.getValue() == null) return;

            controladorEventos.registrarEvento(
                txtNombre.getText().trim(),
                txtUbicacion.getText().trim(),
                dpFecha.getValue()
            );

            limpiarCampos(txtNombre, txtUbicacion, dpFecha);
            recargarTabla(tabla);
        });

        tabla.setOnMouseClicked(e -> {
            Evento ev = tabla.getSelectionModel().getSelectedItem();
            if (ev != null) {
                txtNombre.setText(ev.getNombre());
                txtUbicacion.setText(ev.getUbicacion());
                dpFecha.setValue(ev.getFecha());
            }
        });

        btnActualizar.setOnAction(e -> {
            Evento ev = tabla.getSelectionModel().getSelectedItem();
            if (ev == null) return;

            controladorEventos.actualizarEvento(
                ev.getNombre(),
                dpFecha.getValue(),
                txtUbicacion.getText().trim()
            );

            recargarTabla(tabla);
        });

        btnEliminar.setOnAction(e -> {
            Evento ev = tabla.getSelectionModel().getSelectedItem();
            if (ev == null) return;
            controladorEventos.eliminarEvento(ev.getNombre());
            recargarTabla(tabla);
        });

        root.getChildren().addAll(titulo, tabla, form);
        return root;
    }

    private void recargarTabla(TableView<Evento> tabla) {
        tabla.getItems().clear();
        NodoEvento actual = listaEventos.getCabeza();
        while (actual != null) {
            tabla.getItems().add(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    private void limpiarCampos(TextField t1, TextField t2, DatePicker dp) {
        t1.clear();
        t2.clear();
        dp.setValue(null);
    }
}

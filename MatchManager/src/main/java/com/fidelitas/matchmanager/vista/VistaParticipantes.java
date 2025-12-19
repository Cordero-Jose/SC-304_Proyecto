package com.fidelitas.matchmanager.vista;

import com.fidelitas.matchmanager.controlador.ControladorParticipantes;
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.NodoParticipante;
import com.fidelitas.matchmanager.modelo.Participante;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class VistaParticipantes {

    private final ListaEventos listaEventos;
    private final Usuario usuarioActual;

    private ControladorParticipantes controlador;

    private final String COLOR_FONDO = "#f4f6f8";
    private final String ESTILO_CARD =
            "-fx-background-color: white; -fx-background-radius: 8;"
                    + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);"
                    + "-fx-padding: 20;";

    public VistaParticipantes(ListaEventos listaEventos, Usuario usuarioActual) {
        this.listaEventos = listaEventos;
        this.usuarioActual = usuarioActual;
    }

    public VBox render() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + COLOR_FONDO);

        Label titulo = new Label("Participantes de los eventos");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        // ---- SELECCIONAR EVENTO ----
        ComboBox<Evento> cbEventos = new ComboBox<>();
        NodoEvento n = listaEventos.getCabeza();
        while (n != null) {
            cbEventos.getItems().add(n.getDato());
            n = n.getSiguiente();
        }

        cbEventos.setPromptText("Seleccione un evento");
        cbEventos.setPrefWidth(300);

        // ---- LISTA VISUAL ----
        ListView<HBox> listaVisual = new ListView<>();
        listaVisual.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        // ---- FORMULARIO CRUD ----
        TextField txtNombre = new TextField();
        TextField txtEquipo = new TextField();
        TextField txtRol = new TextField();
        TextField txtEstado = new TextField();

        txtNombre.setPromptText("Nombre");
        txtEquipo.setPromptText("Equipo");
        txtRol.setPromptText("Rol");
        txtEstado.setPromptText("Estado");

        Button btnAgregar = new Button("Agregar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");

        HBox form = new HBox(10, txtNombre, txtEquipo, txtRol, txtEstado,
                btnAgregar, btnActualizar, btnEliminar);

        // ---- BUSCAR ----
        TextField txtBuscar = new TextField();
        txtBuscar.setPromptText("Buscar participante...");

        // ---- ROLES: ESPECTADOR = SOLO LECTURA ----
        if (usuarioActual.getRol().equalsIgnoreCase("Espectador")) {
            btnAgregar.setDisable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);

            txtNombre.setDisable(true);
            txtEquipo.setDisable(true);
            txtRol.setDisable(true);
            txtEstado.setDisable(true);
        }

        // ---- EVENTO SELECCIONADO ----
        cbEventos.setOnAction(e -> {
            if (cbEventos.getValue() != null) {
                listaVisual.getItems().clear();
                controlador =
                        new ControladorParticipantes(cbEventos.getValue().getParticipantes());
                cargarLista(listaVisual);
            }
        });

        // ---- AGREGAR ----
        btnAgregar.setOnAction(e -> {
            if (controlador == null) return;

            controlador.agregar(
                    txtNombre.getText().trim(),
                    txtEquipo.getText().trim(),
                    txtRol.getText().trim(),
                    txtEstado.getText().trim()
            );

            limpiarCampos(txtNombre, txtEquipo, txtRol, txtEstado);
            cargarLista(listaVisual);
        });

        // ---- SELECCIONAR PARTICIPANTE ----
        listaVisual.setOnMouseClicked(e -> {
            HBox box = listaVisual.getSelectionModel().getSelectedItem();
            if (box != null) {
                Participante p = (Participante) box.getUserData();
                txtNombre.setText(p.getNombre());
                txtEquipo.setText(p.getEquipo());
                txtRol.setText(p.getRol());
                txtEstado.setText(p.getEstado());
            }
        });

        // ---- ACTUALIZAR ----
        btnActualizar.setOnAction(e -> {
            HBox seleccionado = listaVisual.getSelectionModel().getSelectedItem();
            if (seleccionado == null || controlador == null) return;

            Participante p = (Participante) seleccionado.getUserData();
            controlador.actualizar(
                    p,
                    txtNombre.getText().trim(),
                    txtEquipo.getText().trim(),
                    txtRol.getText().trim(),
                    txtEstado.getText().trim()
            );

            cargarLista(listaVisual);
        });

        // ---- ELIMINAR ----
        btnEliminar.setOnAction(e -> {
            HBox seleccionado = listaVisual.getSelectionModel().getSelectedItem();
            if (seleccionado == null || controlador == null) return;

            controlador.eliminar((Participante) seleccionado.getUserData());
            cargarLista(listaVisual);
        });

        // ---- BUSCAR ----
        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            if (controlador == null) return;

            listaVisual.getItems().clear();

            if (newVal == null || newVal.isEmpty()) {
                cargarLista(listaVisual);
                return;
            }

            for (Participante p : controlador.buscar(newVal)) {
                listaVisual.getItems().add(crearItemVisual(p, newVal));
            }
        });

        root.getChildren().addAll(
                titulo,
                cbEventos,
                listaVisual,
                form,
                new Label("Buscar:"),
                txtBuscar
        );

        return root;
    }

    private void cargarLista(ListView<HBox> listaVisual) {
        listaVisual.getItems().clear();
        if (controlador == null) return;

        NodoParticipante actual = controlador.getCabeza();
        while (actual != null) {
            listaVisual.getItems().add(crearItemVisual(actual.getDato(), ""));
            actual = actual.getSiguiente();
        }
    }

    private void limpiarCampos(TextField... campos) {
        for (TextField t : campos) t.clear();
    }

    private HBox crearItemVisual(Participante p, String highlight) {

        HBox box = new HBox(5);

        String nombre = p.getNombre();
        TextFlow tfNombre;

        if (!highlight.isEmpty() && nombre.toLowerCase().contains(highlight.toLowerCase())) {
            int i = nombre.toLowerCase().indexOf(highlight.toLowerCase());
            int j = i + highlight.length();

            Text antes = new Text(nombre.substring(0, i));
            Text match = new Text(nombre.substring(i, j));
            match.setFill(Color.RED);
            match.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            Text despues = new Text(nombre.substring(j));

            tfNombre = new TextFlow(new Text("👤 "), antes, match, despues);

        } else {
            tfNombre = new TextFlow(new Text("👤 " + nombre));
        }

        Label lblEquipo = new Label(" | 🏆 " + p.getEquipo());
        Label lblRol = new Label(" | 🎭 " + p.getRol());
        Label lblEstado = new Label(" | 📌 " + p.getEstado());

        box.getChildren().addAll(tfNombre, lblEquipo, lblRol, lblEstado);
        box.setUserData(p);

        return box;
    }
}

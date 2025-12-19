package com.fidelitas.matchmanager.vista;

import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.NodoResultado;
import com.fidelitas.matchmanager.modelo.Resultado;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaResultados {

    private final ListaEventos listaEventos;

    private final String COLOR_FONDO = "#f4f6f8";
    private final String ESTILO_CARD =
        "-fx-background-color: white; -fx-background-radius: 8; " +
        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2); " +
        "-fx-padding: 20;";

    public VistaResultados(ListaEventos listaEventos) {
        this.listaEventos = listaEventos;
    }

    public VBox render() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + COLOR_FONDO);

        Label titulo = new Label("Resultados del Evento");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.web("#2c3e50"));

        // Seleccionar evento
        javafx.scene.control.ComboBox<Evento> cbEventos = new javafx.scene.control.ComboBox<>();
        NodoEvento ne = listaEventos.getCabeza();
        while (ne != null) {
            cbEventos.getItems().add(ne.getDato());
            ne = ne.getSiguiente();
        }
        cbEventos.setPromptText("Seleccione un evento");

        // lista resultados
        ListView<String> lista = new ListView<>();
        lista.setStyle(ESTILO_CARD);

        cbEventos.setOnAction(e -> {
            lista.getItems().clear();
            Evento ev = cbEventos.getValue();
            if (ev == null) return;

            NodoResultado nodo = ev.getPilaResultados().getCima();
            while (nodo != null) {
                Resultado r = nodo.getResultado();

                String linea =
                        r.getPartido().getEquipoA().getNombre() + " " + r.getGolesA() +
                        " - " +
                        r.getGolesB() + " " + r.getPartido().getEquipoB().getNombre();

                lista.getItems().add(linea);

                nodo = nodo.getSiguiente();
            }
        });

        root.getChildren().addAll(
                titulo,
                cbEventos,
                lista
        );

        return root;
    }
}

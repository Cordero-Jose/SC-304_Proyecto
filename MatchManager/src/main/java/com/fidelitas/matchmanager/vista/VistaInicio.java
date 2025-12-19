package com.fidelitas.matchmanager.vista;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaInicio {

    private final String COLOR_FONDO_GENERAL = "#f4f6f8";
    private final String ESTILO_CARD =
            "-fx-background-color: white; " +
            "-fx-background-radius: 8; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2); " +
            "-fx-padding: 20;";

    /**
     * Renderiza la pantalla de inicio (dashboard / resumen)
     */
    public VBox render() {

        VBox panel = new VBox(20);
        panel.setStyle("-fx-background-color: " + COLOR_FONDO_GENERAL + ";");
        panel.setPadding(new Insets(30));

        // título
        Label titulo = new Label("Resumen General");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        titulo.setTextFill(Color.web("#2c3e50"));

        // cards
        HBox filaCards = new HBox(20);
        filaCards.getChildren().addAll(
                crearCard("Eventos Activos", "—"),
                crearCard("Equipos Registrados", "—"),
                crearCard("Partidos esta semana", "—"),
                crearCard("Nuevos Usuarios", "—")
        );

        // noticias
        Label lblNoticias = new Label("Noticias Internas");
        lblNoticias.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        lblNoticias.setTextFill(Color.web("#2c3e50"));

        VBox noticiasBox = new VBox(10);
        noticiasBox.setStyle(ESTILO_CARD);
        noticiasBox.getChildren().addAll(
                new Label("• Bienvenido al sistema Match Manager."),
                new Label("• Recuerde mantener sus datos actualizados."),
                new Label("• Última actualización: " + java.time.LocalDate.now())
        );

        panel.getChildren().addAll(titulo, filaCards, new Separator(), lblNoticias, noticiasBox);

        return panel;
    }

    /**
     * Crea una tarjeta de resumen (pequeña card)
     */
    private VBox crearCard(String titulo, String numero) {
        VBox card = new VBox(10);
        card.setMinWidth(200);
        card.setStyle(ESTILO_CARD);

        Label lblNum = new Label(numero);
        lblNum.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        lblNum.setTextFill(Color.web("#2c3e50"));

        Label lblTit = new Label(titulo);
        lblTit.setFont(Font.font("Segoe UI", 14));
        lblTit.setTextFill(Color.web("#7f8c8d"));

        card.getChildren().addAll(lblNum, lblTit);
        return card;
    }
}

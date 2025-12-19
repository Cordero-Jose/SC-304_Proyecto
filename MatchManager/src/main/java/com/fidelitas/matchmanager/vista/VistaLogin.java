package com.fidelitas.matchmanager.vista;

import com.fidelitas.matchmanager.controlador.ControladorAuth;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaLogin {

    private final Stage stage;
    private final ControladorAuth controladorAuth;

    public VistaLogin(Stage stage) {
        this.stage = stage;
        this.controladorAuth = new ControladorAuth();
    }

    public void mostrar() {

        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label titulo = new Label("Inicio de Sesión");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");

        Button btnLogin = new Button("Ingresar");
        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        btnLogin.setOnAction(e -> {
            String correo = txtCorreo.getText().trim();
            String pass = txtPassword.getText().trim();

            Usuario u = controladorAuth.login(correo, pass);

            if (u == null) {
                lblError.setText("Credenciales incorrectas");
                return;
            }

            lblError.setText("");

            // pasar usuario autenticado al Dashboard
            VistaDashboard dash = new VistaDashboard(stage, u);
            dash.mostrar();
        });

        root.getChildren().addAll(titulo, txtCorreo, txtPassword, btnLogin, lblError);

        Scene scene = new Scene(root, 550, 450);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}

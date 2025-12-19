package com.fidelitas.matchmanager.controlador;

import java.util.ArrayList;
import java.util.List;

import com.fidelitas.matchmanager.modelo.Usuario;

public class ControladorAuth {

    private List<Usuario> usuarios;

    public ControladorAuth() {
        usuarios = new ArrayList<>();

        // Administradores
        usuarios.add(new Usuario(
            "Fabian Mora",
            "fmora@ufide.ac.cr",
            "123",
            "Administrador"
        ));

        usuarios.add(new Usuario(
            "Eduardo Cordero",
            "jcordero@ufide.ac.cr",
            "123",
            "Administrador"
        ));

        // Espectadores
        usuarios.add(new Usuario(
            "Axel Segura",
            "asegura@ufide.ac.cr",
            "123",
            "Espectador"
        ));

        usuarios.add(new Usuario(
            "Jennessy Abarca",
            "jabarca@ufide.ac.cr",
            "123",
            "Espectador"
        ));
    }

    // autenticar usuario
    public Usuario login(String correo, String password) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}

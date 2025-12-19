package com.fidelitas.matchmanager.vista;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fidelitas.matchmanager.controlador.ControladorClasificacion;
import com.fidelitas.matchmanager.controlador.ControladorGrafo;
import com.fidelitas.matchmanager.controlador.ControladorPartidos;
import com.fidelitas.matchmanager.controlador.ControladorResultados;
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.NodoParticipante;
import com.fidelitas.matchmanager.modelo.Partido;
import com.fidelitas.matchmanager.modelo.Usuario;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaPartidos {

    private final ListaEventos listaEventos;
    private final Usuario usuarioActual;

    private ControladorPartidos controladorPartidos;
    private ControladorResultados controladorResultados;
    private ControladorGrafo controladorGrafo;
    private ControladorClasificacion controladorClasificacion;

    private final String COLOR_FONDO = "#f4f6f8";
    private final String ESTILO_CARD =
        "-fx-background-color: white; -fx-background-radius: 8; "
      + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05),5,0,0,2); "
      + "-fx-padding: 20;";

    public VistaPartidos(ListaEventos listaEventos, Usuario usuarioActual) {
        this.listaEventos = listaEventos;
        this.usuarioActual = usuarioActual;
    }

    public VBox render() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + COLOR_FONDO);

        Label titulo = new Label("Partidos del Evento");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        // ---- SELECCIONAR EVENTO ----
        ComboBox<Evento> cbEventos = new ComboBox<>();
        NodoEvento ne = listaEventos.getCabeza();
        while (ne != null) {
            cbEventos.getItems().add(ne.getDato());
            ne = ne.getSiguiente();
        }
        cbEventos.setPromptText("Seleccione un evento");

        // ---- LISTA DE PARTIDOS PENDIENTES ----
        ListView<String> lista = new ListView<>();
        lista.setStyle(ESTILO_CARD);

        // ---- FORMULARIO PARA AGREGAR PARTIDO ----

        ComboBox<String> cbA = new ComboBox<>();
        ComboBox<String> cbB = new ComboBox<>();
        cbA.setPromptText("Equipo A");
        cbB.setPromptText("Equipo B");

        DatePicker dpFecha = new DatePicker();
        dpFecha.setPromptText("Fecha");

        TextField txtLugar = new TextField();
        txtLugar.setPromptText("Lugar");

        Button btnAgregar = new Button("Agregar Partido");
        Button btnJugar = new Button("Marcar como jugado");

        HBox form = new HBox(10, cbA, cbB, dpFecha, txtLugar, btnAgregar, btnJugar);

        // ---- BLOQUEAR PARA ESPECTADOR ----
        if (usuarioActual.getRol().equalsIgnoreCase("Espectador")) {
            btnAgregar.setDisable(true);
            btnJugar.setDisable(true);
            cbA.setDisable(true);
            cbB.setDisable(true);
            dpFecha.setDisable(true);
            txtLugar.setDisable(true);
        }

        // ---- AL SELECCIONAR EVENTO ----
        cbEventos.setOnAction(e -> {

            Evento ev = cbEventos.getValue();
            if (ev == null) return;

            // cargar controladores
            controladorPartidos = new ControladorPartidos(ev.getColaPartidos());
            controladorResultados = new ControladorResultados(ev.getPilaResultados());
            controladorGrafo = new ControladorGrafo(ev.getGrafoEquipos());
            controladorClasificacion = new ControladorClasificacion(ev.getBST());

            // cargar equipos desde participantes
            cbA.getItems().clear();
            cbB.getItems().clear();

            Set<String> equipos = new HashSet<>();
            NodoParticipante np = ev.getParticipantes().getCabeza();
            while (np != null) {
                equipos.add(np.getDato().getEquipo());
                np = np.getSiguiente();
            }

            cbA.getItems().addAll(equipos);
            cbB.getItems().addAll(equipos);

            cargarLista(lista);
        });

        // ---- AGREGAR PARTIDO (Admin) ----
        btnAgregar.setOnAction(e -> {

            if (controladorPartidos == null) return;

            String a = cbA.getValue();
            String b = cbB.getValue();
            LocalDate fecha = dpFecha.getValue();
            String lugar = txtLugar.getText().trim();

            if (a == null || b == null || fecha == null || lugar.isEmpty()) return;
            if (a.equals(b)) return;

            controladorPartidos.agregarPartido(a, b, fecha, lugar);

            cbA.setValue(null);
            cbB.setValue(null);
            dpFecha.setValue(null);
            txtLugar.clear();

            cargarLista(lista);
        });

        // ---- JUGAR PARTIDO (Admin) ----
        btnJugar.setOnAction(e -> {

            if (controladorPartidos == null) return;

            Partido p = controladorPartidos.jugarPartido();
            if (p == null) return;

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Resultado");
            d.setHeaderText("Ingrese marcador (ejemplo: 2-1)");
            d.setContentText("Marcador:");

            String entrada = d.showAndWait().orElse(null);
            if (entrada == null || !entrada.contains("-")) return;

            try {
                String[] x = entrada.split("-");
                int ga = Integer.parseInt(x[0].trim());
                int gb = Integer.parseInt(x[1].trim());

                // registrar resultado en la pila
                controladorResultados.agregarResultado(p, ga, gb);

                // actualizar grafo
                controladorGrafo.agregarEnfrentamiento(
                        p.getEquipoA().getNombre(),
                        p.getEquipoB().getNombre()
                );

                // actualizar clasificación
                if (ga > gb) {
                    controladorClasificacion.registrarVictoria(p.getEquipoA().getNombre());
                } else if (gb > ga) {
                    controladorClasificacion.registrarVictoria(p.getEquipoB().getNombre());
                }

                cargarLista(lista);

            } catch (Exception ex) {
                return;
            }
        });

        root.getChildren().addAll(
                titulo,
                cbEventos,
                lista,
                form
        );

        return root;
    }

    private void cargarLista(ListView<String> lista) {
        if (controladorPartidos == null) return;
        lista.getItems().setAll(controladorPartidos.obtenerLista());
    }
}

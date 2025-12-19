package com.fidelitas.matchmanager.controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fidelitas.matchmanager.modelo.Equipo;
import com.fidelitas.matchmanager.modelo.Evento;
import com.fidelitas.matchmanager.modelo.ListaEventos;
import com.fidelitas.matchmanager.modelo.NodoEvento;
import com.fidelitas.matchmanager.modelo.NodoParticipante;
import com.fidelitas.matchmanager.modelo.NodoPartido;
import com.fidelitas.matchmanager.modelo.NodoResultado;
import com.fidelitas.matchmanager.modelo.Participante;
import com.fidelitas.matchmanager.modelo.Partido;
import com.fidelitas.matchmanager.modelo.Resultado;

public class Persistencia {

    private static final String RUTA = "datos.json";

    public static void guardar(ListaEventos listaEventos) {
        try {
            FileWriter fw = new FileWriter(RUTA);
            fw.write(generarJSON(listaEventos));
            fw.close();
        } catch (Exception e) {
            System.out.println("Error guardando: " + e.getMessage());
        }
    }

    public static ListaEventos cargar() {
        try {
            File f = new File(RUTA);
            if (!f.exists()) return new ListaEventos();

            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null)
                sb.append(linea);

            br.close();
            return reconstruir(sb.toString());

        } catch (Exception e) {
            System.out.println("Error cargando: " + e.getMessage());
            return new ListaEventos();
        }
    }

    private static String generarJSON(ListaEventos lista) {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"eventos\":[");

        NodoEvento ne = lista.getCabeza();

        while (ne != null) {
            Evento ev = ne.getDato();

            sb.append("{");
            sb.append("\"nombre\":\"").append(ev.getNombre()).append("\",");
            sb.append("\"ubicacion\":\"").append(ev.getUbicacion()).append("\",");
            sb.append("\"fecha\":\"").append(ev.getFecha()).append("\",");

            sb.append("\"participantes\":[");
            NodoParticipante np = ev.getParticipantes().getCabeza();
            while (np != null) {
                Participante p = np.getDato();
                sb.append("{");
                sb.append("\"nombre\":\"").append(p.getNombre()).append("\",");
                sb.append("\"equipo\":\"").append(p.getEquipo()).append("\",");
                sb.append("\"rol\":\"").append(p.getRol()).append("\",");
                sb.append("\"estado\":\"").append(p.getEstado()).append("\"");
                sb.append("}");
                np = np.getSiguiente();
                if (np != null) sb.append(",");
            }
            sb.append("],");

            sb.append("\"partidosPendientes\":[");
            NodoPartido npar = ev.getColaPartidos().getFrente();
            while (npar != null) {
                Partido p = npar.getPartido();
                sb.append("{");
                sb.append("\"equipoA\":\"").append(p.getEquipoA().getNombre()).append("\",");
                sb.append("\"equipoB\":\"").append(p.getEquipoB().getNombre()).append("\",");
                sb.append("\"fecha\":\"").append(p.getFecha()).append("\",");
                sb.append("\"lugar\":\"").append(p.getLugar()).append("\"");
                sb.append("}");
                npar = npar.getSiguiente();
                if (npar != null) sb.append(",");
            }
            sb.append("],");

            sb.append("\"resultados\":[");
            NodoResultado nr = ev.getPilaResultados().getCima();
            while (nr != null) {
                Resultado r = nr.getResultado();
                sb.append("{");
                sb.append("\"equipoA\":\"").append(r.getPartido().getEquipoA().getNombre()).append("\",");
                sb.append("\"equipoB\":\"").append(r.getPartido().getEquipoB().getNombre()).append("\",");
                sb.append("\"golesA\":").append(r.getGolesA()).append(",");
                sb.append("\"golesB\":").append(r.getGolesB()).append(",");
                sb.append("\"fecha\":\"").append(r.getPartido().getFecha()).append("\",");
                sb.append("\"lugar\":\"").append(r.getPartido().getLugar()).append("\"");
                sb.append("}");
                nr = nr.getSiguiente();
                if (nr != null) sb.append(",");
            }
            sb.append("],");

            sb.append("\"grafo\":{");
            Map<String, Set<String>> g = ev.getGrafoEquipos().getAdyacencias();
            int c = 0;
            for (String eq : g.keySet()) {
                sb.append("\"").append(eq).append("\":[");
                sb.append("\"").append(String.join("\",\"", g.get(eq))).append("\"");
                sb.append("]");
                c++;
                if (c < g.size()) sb.append(",");
            }
            sb.append("},");

            sb.append("\"clasificacion\":[");
            List<String> clasif = ev.getBST().obtenerClasificacion();
            for (int i = 0; i < clasif.size(); i++) {
                String[] partes = clasif.get(i).split("—");
                String eq = partes[0].trim();
                int vict = Integer.parseInt(partes[1].replace("victorias", "").trim());

                sb.append("{\"equipo\":\"").append(eq).append("\",");
                sb.append("\"victorias\":").append(vict).append("}");
                if (i < clasif.size() - 1) sb.append(",");
            }
            sb.append("]");

            sb.append("}");

            ne = ne.getSiguiente();
            if (ne != null) sb.append(",");
        }

        sb.append("]}");
        return sb.toString();
    }

    private static ListaEventos reconstruir(String json) {

        ListaEventos lista = new ListaEventos();
        json = json.replace("\n", "").replace("\r", "");

        String arr = extraerArreglo(json, "eventos");
        List<String> eventos = separarObjetos(arr);

        for (String evJson : eventos) {

            String nombre = extraerString(evJson, "nombre");
            String ubic = extraerString(evJson, "ubicacion");
            LocalDate fecha = LocalDate.parse(extraerString(evJson, "fecha"));
            Evento ev = new Evento(nombre, ubic, fecha);

            for (String pJson : separarObjetos(extraerArreglo(evJson, "participantes"))) {
                String n = extraerString(pJson, "nombre");
                String eq = extraerString(pJson, "equipo");
                String rol = extraerString(pJson, "rol");
                String est = extraerString(pJson, "estado");
                ev.getParticipantes().agregar(new Participante(n, eq, rol, est));
            }

            for (String pJson : separarObjetos(extraerArreglo(evJson, "partidosPendientes"))) {
                String a = extraerString(pJson, "equipoA");
                String b = extraerString(pJson, "equipoB");
                LocalDate f = LocalDate.parse(extraerString(pJson, "fecha"));
                String l = extraerString(pJson, "lugar");
                ev.getColaPartidos().encolar(new Partido(new Equipo(a), new Equipo(b), f, l));
            }

            for (String rJson : separarObjetos(extraerArreglo(evJson, "resultados"))) {
                String a = extraerString(rJson, "equipoA");
                String b = extraerString(rJson, "equipoB");
                int ga = extraerInt(rJson, "golesA");
                int gb = extraerInt(rJson, "golesB");
                LocalDate f = LocalDate.parse(extraerString(rJson, "fecha"));
                String l = extraerString(rJson, "lugar");
                Partido p = new Partido(new Equipo(a), new Equipo(b), f, l);
                ev.getPilaResultados().apilar(new Resultado(p, ga, gb));
            }

            String gObj = extraerObjeto(evJson, "grafo");
            Map<String, String> gmap = extraerMapaListas(gObj);

            for (String eq : gmap.keySet()) {
                String contenido = gmap.get(eq).replace("\"", "");
                if (contenido.isBlank()) continue;
                for (String otro : contenido.split(",")) {
                    if (!otro.isBlank())
                        ev.getGrafoEquipos().agregarEnfrentamiento(eq, otro.trim());
                }
            }

            for (String cJson : separarObjetos(extraerArreglo(evJson, "clasificacion"))) {
                String eq = extraerString(cJson, "equipo");
                int vict = extraerInt(cJson, "victorias");
                ev.getBST().insertar(eq, vict);
            }

            lista.agregar(ev);
        }

        return lista;
    }

    private static String extraerString(String json, String clave) {
        String p = "\"" + clave + "\":\"";
        int i = json.indexOf(p);
        if (i == -1) return "";
        i += p.length();
        int j = json.indexOf("\"", i);
        return json.substring(i, j);
    }

    private static int extraerInt(String json, String clave) {
        String p = "\"" + clave + "\":";
        int i = json.indexOf(p);
        if (i == -1) return 0;
        i += p.length();
        int j = i;
        while (j < json.length() && Character.isDigit(json.charAt(j)))
            j++;
        return Integer.parseInt(json.substring(i, j));
    }

    private static String extraerArreglo(String json, String clave) {
        String p = "\"" + clave + "\":[";
        int i = json.indexOf(p);
        if (i == -1) return "";
        i += p.length();

        if (i < json.length() && json.charAt(i) == ']') return "";

        int j = buscarCierre(json, i - 1, '[', ']');
        return json.substring(i, j);
    }

    private static String extraerObjeto(String json, String clave) {
        String p = "\"" + clave + "\":{";
        int i = json.indexOf(p);
        if (i == -1) return "";
        i += p.length();

        if (i < json.length() && json.charAt(i) == '}') return "";

        int j = buscarCierre(json, i - 1, '{', '}');
        return json.substring(i, j);
    }

    private static List<String> separarObjetos(String json) {
        List<String> lista = new ArrayList<>();
        int i = 0;

        while (i < json.length()) {
            if (json.charAt(i) == '{') {
                int j = buscarCierre(json, i, '{', '}');
                lista.add(json.substring(i + 1, j));
                i = j + 1;
            }
            i++;
        }
        return lista;
    }

    private static Map<String, String> extraerMapaListas(String json) {
        Map<String, String> mapa = new HashMap<>();
        int i = 0;

        while (i < json.length()) {
            if (json.charAt(i) == '\"') {
                int j = json.indexOf("\"", i + 1);
                String clave = json.substring(i + 1, j);
                int arrStart = json.indexOf("[", j);
                int arrEnd = buscarCierre(json, arrStart, '[', ']');
                String contenido = json.substring(arrStart + 1, arrEnd);
                mapa.put(clave, contenido);
                i = arrEnd;
            }
            i++;
        }
        return mapa;
    }

    private static int buscarCierre(String str, int pos, char a, char b) {
        int c = 1;
        for (int i = pos + 1; i < str.length(); i++) {
            if (str.charAt(i) == a) c++;
            if (str.charAt(i) == b) c--;
            if (c == 0) return i;
        }
        return -1;
    }
}


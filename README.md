# SC-304 Proyecto Final: Sistema de Gestión de Eventos Deportivos

**Universidad Fidélitas**  
Curso: Estructura de Datos (SC-304, 2025-2C)  
Profesor: Pablo Rodríguez Guzmán  
Grupo: *[Número de grupo]*  
Integrantes:  
- Nombre 1  
- Nombre 2  
- Nombre 3  
- Nombre 4  

---

## 📌 Descripción del Proyecto
Este sistema permite la **gestión de eventos deportivos** con roles diferenciados para **administradores** (gestionan datos) y **espectadores** (consultan información).  
Se implementa siguiendo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)** y utilizando las estructuras de datos vistas en clase.

---

## 🛠️ Arquitectura MVC

- **Modelo (`com.fidelitas.matchmanager.modelo`)**  
  Contiene las clases de entidad (`Evento`, `Usuario`, `Participante`, etc.) y las estructuras de datos personalizadas:  
  - Lista Enlazada Simple → Eventos  
  - Lista Doblemente Enlazada → Participantes  
  - Cola Dinámica → Partidos programados  
  - Pila Dinámica → Resultados  
  - Grafo No Dirigido → Enfrentamientos entre equipos  
  - Árbol Binario de Búsqueda (BST) → Clasificación de equipos  

- **Vista (`com.fidelitas.matchmanager.vista`)**  
  Interfaz gráfica desarrollada con **JavaFX**, que incluye:  
  - `VistaLogin` → Pantalla de inicio de sesión  
  - `VistaDashboard` → Panel principal con menú lateral y pantallas de gestión  

- **Controlador (`com.fidelitas.matchmanager.controlador`)**  
  Maneja la lógica de conexión entre la vista y el modelo.  
  - `ServicioDatosSimulados` → Proveedor de datos de prueba (en el avance).  
  - En la versión final se reemplaza por persistencia en archivos JSON manuales.

---

## 🚀 Ejecución del Proyecto

### Requisitos
- Java 17
- Maven 3.8+
- JavaFX 17.0.8

### Compilar y ejecutar
```bash
mvn clean install
mvn javafx:run

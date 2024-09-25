package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

// Controlador
public class ControlEquipo {

    private ControlPrincipal control;
    private ArrayList<Equipo> equipos;
    private Capitan capitan;
    private Equipo equipo;
    private List<Jugador> jugadores;
    
    public ControlEquipo(){
        
    }
    
    public ControlEquipo(ControlPrincipal control) {
        this.control = control;
        this.equipos = new ArrayList<>();
    }

    public void ingresarTodosLosCampos() {
        control.getVentanaEmergente().ventanaError("Ingrese todos los datos obligatorios");
    }

    public void cedulaRepetida(int i) {
        control.getVentanaEmergente().ventanaAtención("La cédula "+i+" ya se encuentra en uso");
    }

    public void crearEquipo() {
        // Verificar si los campos del capitán están completos
        if (control.getCrearEquipo().cargarNombreEquipo().getText().isEmpty()
                || control.getCrearEquipo().cargarNombre().getText().isEmpty()
                || control.getCrearEquipo().cargarCedula().getText().isEmpty()
                || control.getCrearEquipo().cargarAñosExp().getText().isEmpty()) {
            ingresarTodosLosCampos();
            return;
        }

        // Inicializar jugadores
        jugadores = new ArrayList<>();
        String cedulaCapitan = control.getCrearEquipo().cargarCedula().getText();

        // Agregar jugadores y verificar datos
        for (int i = 1; i <= 5; i++) {
            String nombre = "";
            String cedula = "";
            String edad = "";

            // Usar if para cada jugador
            if (i == 1) {
                nombre = control.getCrearEquipo().cargarNombre1().getText();
                cedula = control.getCrearEquipo().cargarCedula1().getText();
                edad = control.getCrearEquipo().cargarEdad1().getText();
            } else if (i == 2) {
                nombre = control.getCrearEquipo().cargarNombre2().getText();
                cedula = control.getCrearEquipo().cargarCedula2().getText();
                edad = control.getCrearEquipo().cargarEdad2().getText();
            } else if (i == 3) {
                nombre = control.getCrearEquipo().cargarNombre3().getText();
                cedula = control.getCrearEquipo().cargarCedula3().getText();
                edad = control.getCrearEquipo().cargarEdad3().getText();
            } else if (i == 4) {
                nombre = control.getCrearEquipo().cargarNombre4().getText();
                cedula = control.getCrearEquipo().cargarCedula4().getText();
                edad = control.getCrearEquipo().cargarEdad4().getText();
            } else if (i == 5) {
                nombre = control.getCrearEquipo().cargarNombre5().getText();
                cedula = control.getCrearEquipo().cargarCedula5().getText();
                edad = control.getCrearEquipo().cargarEdad5().getText();
            }

            // Verificar si los campos del jugador están completos
            if (nombre.isEmpty() || cedula.isEmpty()) {
                ingresarTodosLosCampos();
                return;
            }

            // Verificar si la cédula ya está en uso
            if (cedulaRepetida(cedula, jugadores, cedulaCapitan)) {
                cedulaRepetida(i);
                return;
            }

            // Agregar jugador a la lista
            jugadores.add(new Jugador(cedula, nombre, edad));
        }

        // Verificar si hay al menos 5 jugadores
        if (jugadores.size() < 5) {
            control.getVentanaEmergente().ventanaError("El equipo debe tener al menos 5 jugadores.");
            return;
        }

        try {
            // Crear el capitán del equipo
            String nombreCapitan = control.getCrearEquipo().cargarNombre().getText();
            String edadCapitan = control.getCrearEquipo().cargarEdad().getText();
            String añosExperiencia = control.getCrearEquipo().cargarAñosExp().getText();

            capitan = new Capitan(añosExperiencia, cedulaCapitan, nombreCapitan, edadCapitan);

            // Crear el equipo
            equipo = new Equipo(control.getCrearEquipo().cargarNombreEquipo().getText(), "", capitan, jugadores);
            equipos.add(equipo);

        } catch (NumberFormatException e) {
            control.getVentanaEmergente().ventanaError("Error en los campos numéricos");
        }
    }

    // Método para validar si la cédula ya está en uso
    private boolean cedulaRepetida(String cedula, List<Jugador> jugadores, String cedulaCapitan) {
        if (cedula.equals(cedulaCapitan)) {
            return true; // La cédula del capitán no puede ser igual a la de ningún jugador
        }

        for (Jugador jugador : jugadores) {
            if (jugador.getCedula().equals(cedula)) {
                return true; // La cédula ya está en uso por otro jugador
            }
        }
        return false; // La cédula no está en uso
    }

    public ControlPrincipal getControl() {
        return control;
    }

    public void setControl(ControlPrincipal control) {
        this.control = control;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Capitan getCapitan() {
        return capitan;
    }

    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    
}
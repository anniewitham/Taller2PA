package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ControlEquipo {

    private ControlPrincipal control;
    private ArrayList<Equipo> equipos;
    private Capitan capitan;
    private Equipo equipo;
    private List<Jugador> jugadores;

    public ControlEquipo() {}

    public ControlEquipo(ControlPrincipal control) {
        this.control = control;
        this.equipos = new ArrayList<>();
    }

    public void ingresarTodosLosCampos() {
        control.getVentanaEmergente().ventanaError("Ingrese todos los datos obligatorios");
    }

    public void cedulaRepetida(int i) {
        control.getVentanaEmergente().ventanaAtención("La cédula " + i + " ya se encuentra en uso");
    }

    public void nombreRepetido() {
        control.getVentanaEmergente().ventanaAtención("El nombre del equipo ya existe");
    }

    public void crearEquipo() {
        if (control.getCrearEquipo().cargarNombreEquipo().getText().isEmpty()
                || control.getCrearEquipo().cargarNombre().getText().isEmpty()
                || control.getCrearEquipo().cargarCedula().getText().isEmpty()
                || control.getCrearEquipo().cargarAñosExp().getText().isEmpty()) {
            ingresarTodosLosCampos();
            return;
        }

        jugadores = new ArrayList<>();
        String cedulaCapitan = control.getCrearEquipo().cargarCedula().getText();

        for (int i = 1; i <= 5; i++) {
            String nombre = "";
            String cedula = "";
            String edad = "";

            // Usar if para cada jugador
            switch (i) {
                case 1:
                    nombre = control.getCrearEquipo().cargarNombre1().getText();
                    cedula = control.getCrearEquipo().cargarCedula1().getText();
                    edad = control.getCrearEquipo().cargarEdad1().getText();
                    break;
                case 2:
                    nombre = control.getCrearEquipo().cargarNombre2().getText();
                    cedula = control.getCrearEquipo().cargarCedula2().getText();
                    edad = control.getCrearEquipo().cargarEdad2().getText();
                    break;
                case 3:
                    nombre = control.getCrearEquipo().cargarNombre3().getText();
                    cedula = control.getCrearEquipo().cargarCedula3().getText();
                    edad = control.getCrearEquipo().cargarEdad3().getText();
                    break;
                case 4:
                    nombre = control.getCrearEquipo().cargarNombre4().getText();
                    cedula = control.getCrearEquipo().cargarCedula4().getText();
                    edad = control.getCrearEquipo().cargarEdad4().getText();
                    break;
                case 5:
                    nombre = control.getCrearEquipo().cargarNombre5().getText();
                    cedula = control.getCrearEquipo().cargarCedula5().getText();
                    edad = control.getCrearEquipo().cargarEdad5().getText();
                    break;
            }

            if (nombre.isEmpty() || cedula.isEmpty()) {
                ingresarTodosLosCampos();
                return;
            }

            if (cedulaRepetida(cedula, jugadores, cedulaCapitan)) {
                cedulaRepetida(i);
                return;
            }

            jugadores.add(new Jugador(cedula, nombre, edad));
        }

        if (jugadores.size() < 5) {
            control.getVentanaEmergente().ventanaError("El equipo debe tener al menos 5 jugadores.");
            return;
        }

        for (Equipo equipo : equipos) {
            if (control.getCrearEquipo().cargarNombreEquipo().getText().equals(equipo.getNombreEquipo())) {
                nombreRepetido();
                return;
            }
        }

        try {
            String nombreCapitan = control.getCrearEquipo().cargarNombre().getText();
            String edadCapitan = control.getCrearEquipo().cargarEdad().getText();
            String añosExperiencia = control.getCrearEquipo().cargarAñosExp().getText();

            capitan = new Capitan(añosExperiencia, cedulaCapitan, nombreCapitan, edadCapitan);

            equipo = new Equipo(control.getCrearEquipo().cargarNombreEquipo().getText(), "", capitan, jugadores);
            equipos.add(equipo);

            control.getCrearEquipo().resetearCamposCrearEquipo();
            control.getCrearEquipo().setVisible(false);
            control.getVistaInicio().setVisible(true);

        } catch (NumberFormatException e) {
            control.getVentanaEmergente().ventanaError("Error en los campos numéricos");
        }
    }

    private boolean cedulaRepetida(String cedula, List<Jugador> jugadores, String cedulaCapitan) {
        if (cedula.equals(cedulaCapitan)) {
            return true; 
        }

        for (Jugador jugador : jugadores) {
            if (jugador.getCedula().equals(cedula)) {
                return true; 
            }
        }
        return false; 
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
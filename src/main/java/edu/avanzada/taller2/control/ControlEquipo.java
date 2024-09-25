package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 * ControlEquipo es una clase que gestiona la creación y manejo de equipos en el juego.
 * Permite ingresar los datos de los capitanes y jugadores, asegurando que se cumplan
 * las validaciones necesarias, como la unicidad de cédulas y nombres de equipo.
 */
public class ControlEquipo {

    protected ControlPrincipal control; // Controlador principal del sistema
    protected ArrayList<Equipo> equipos; // Lista de equipos creados
    protected Capitan capitan; // Capitán del equipo
    protected Equipo equipo; // Equipo actual
    protected List<Jugador> jugadores; // Lista de jugadores en el equipo

    /**
     * Constructor por defecto de ControlEquipo.
     */
    public ControlEquipo() {}

    /**
     * Constructor de ControlEquipo que inicializa el controlador principal
     * y la lista de equipos.
     * 
     * @param control ControlPrincipal que gestiona la lógica del sistema.
     */
    public ControlEquipo(ControlPrincipal control) {
        this.control = control;
        this.equipos = new ArrayList<>();
    }

    /**
     * Método que muestra un mensaje de error si no se ingresan todos los datos obligatorios.
     */
    public void ingresarTodosLosCampos() {
        control.getVentanaEmergente().ventanaError("Ingrese todos los datos obligatorios");
    }

    /**
     * Método que muestra un mensaje de atención si se detecta una cédula repetida.
     * 
     * @param i Cédula que ya está en uso.
     */
    public void cedulaRepetida(int i) {
        control.getVentanaEmergente().ventanaAtención("La cédula " + i + " ya se encuentra en uso");
    }

    /**
     * Método que muestra un mensaje de atención si el nombre del equipo ya existe.
     */
    public void nombreRepetido() {
        control.getVentanaEmergente().ventanaAtención("El nombre del equipo ya existe");
    }

    /**
     * Método que crea un nuevo equipo después de validar los campos ingresados.
     * 
     * Si los datos son válidos, se crea el capitán y se agrega el equipo a la lista de equipos.
     * En caso de que haya un error, se muestra un mensaje correspondiente.
     */
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

    /**
     * Método que verifica si una cédula está repetida entre los jugadores.
     * 
     * @param cedula Cédula a verificar.
     * @param jugadores Lista de jugadores.
     * @param cedulaCapitan Cédula del capitán del equipo.
     * @return true si la cédula está repetida, false en caso contrario.
     */
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

    /**
     * Método para obtener el controlador principal.
     * 
     * @return ControlPrincipal asociado.
     */
    public ControlPrincipal getControl() {
        return control;
    }

    /**
     * Método para establecer el controlador principal.
     * 
     * @param control ControlPrincipal a establecer.
     */
    public void setControl(ControlPrincipal control) {
        this.control = control;
    }

    /**
     * Método para obtener la lista de equipos.
     * 
     * @return Lista de equipos creados.
     */
    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Método para establecer la lista de equipos.
     * 
     * @param equipos Lista de equipos a establecer.
     */
    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    /**
     * Método para obtener el capitán del equipo.
     * 
     * @return Capitán del equipo.
     */
    public Capitan getCapitan() {
        return capitan;
    }

    /**
     * Método para establecer el capitán del equipo.
     * 
     * @param capitan Capitán a establecer.
     */
    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

    /**
     * Método para obtener el equipo actual.
     * 
     * @return Equipo actual.
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * Método para establecer el equipo actual.
     * 
     * @param equipo Equipo a establecer.
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * Método para obtener la lista de jugadores.
     * 
     * @return Lista de jugadores del equipo.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Método para establecer la lista de jugadores.
     * 
     * @param jugadores Lista de jugadores a establecer.
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
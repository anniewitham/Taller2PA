package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

//Controlador
public class ControlEquipo {

    private ControlPrincipal control;
    private ArrayList<Equipo> equipos;
    private Equipo equipo;

    public ControlEquipo(ControlPrincipal control) {
        this.control = control;
        this.equipos = new ArrayList<>();
    }

    public void crearEquipo() {
        if (control.getCrearEquipo().cargarNombreEquipo().getText().isEmpty()
                || control.getCrearEquipo().cargarNombre().getText().isEmpty()
                || control.getCrearEquipo().cargarCedula().getText().isEmpty()
                || control.getCrearEquipo().cargarEdad().getText().isEmpty()
                || control.getCrearEquipo().cargarAñosExp().getText().isEmpty()) {
            control.getVentanaEmergente().ventanaError("Ingrese todos los datos obligatorios");
        } else {

            //MIRAR QUE ESTEN LOS JUGADORES COMPLETOS
            int numJugadores = 0;
            List<Jugador> jugadores = new ArrayList<>();
            //1      
            if (!control.getCrearEquipo().cargarNombre1().getText().isEmpty()
                    && !control.getCrearEquipo().cargarEdad1().getText().isEmpty()
                    && !control.getCrearEquipo().cargarCedula1().getText().isEmpty()) {
                numJugadores++;

                jugadores.add(new Jugador(control.getCrearEquipo().cargarCedula1().getText(),
                        control.getCrearEquipo().cargarNombre1().getText(),
                        control.getCrearEquipo().cargarEdad1().getText()));
            }
            //2     
            if (!control.getCrearEquipo().cargarNombre2().getText().isEmpty()
                    && !control.getCrearEquipo().cargarEdad2().getText().isEmpty()
                    && !control.getCrearEquipo().cargarCedula2().getText().isEmpty()) {
                numJugadores++;
                jugadores.add(new Jugador(control.getCrearEquipo().cargarCedula2().getText(),
                        control.getCrearEquipo().cargarNombre2().getText(),
                        control.getCrearEquipo().cargarEdad2().getText()));
            }

            //3     
            if (!control.getCrearEquipo().cargarNombre3().getText().isEmpty()
                    && !control.getCrearEquipo().cargarEdad3().getText().isEmpty()
                    && !control.getCrearEquipo().cargarCedula3().getText().isEmpty()) {
                numJugadores++;
                jugadores.add(new Jugador(control.getCrearEquipo().cargarCedula3().getText(),
                        control.getCrearEquipo().cargarNombre3().getText(),
                        control.getCrearEquipo().cargarEdad3().getText()));
            }
            //4    
            if (!control.getCrearEquipo().cargarNombre4().getText().isEmpty()
                    && !control.getCrearEquipo().cargarEdad4().getText().isEmpty()
                    && !control.getCrearEquipo().cargarCedula4().getText().isEmpty()) {
                numJugadores++;
                jugadores.add(new Jugador(control.getCrearEquipo().cargarCedula4().getText(),
                        control.getCrearEquipo().cargarNombre4().getText(),
                        control.getCrearEquipo().cargarEdad4().getText()));
            }
            //5
            if (!control.getCrearEquipo().cargarNombre5().getText().isEmpty()
                    && !control.getCrearEquipo().cargarEdad5().getText().isEmpty()
                    && !control.getCrearEquipo().cargarCedula5().getText().isEmpty()) {
                numJugadores++;
                jugadores.add(new Jugador(control.getCrearEquipo().cargarCedula5().getText(),
                        control.getCrearEquipo().cargarNombre5().getText(),
                        control.getCrearEquipo().cargarEdad5().getText()));
            }

            if (numJugadores < 5) {
                control.getVentanaEmergente().ventanaError("El equipo debe tener al menos 5 jugadores.");
            } else {
                try {
                    // Crear el capitán del equipo
                    String nombreCapitan = control.getCrearEquipo().cargarNombre().getText();
                    String cedulaCapitan = control.getCrearEquipo().cargarCedula().getText();
                    int edadCapitan = Integer.parseInt(control.getCrearEquipo().cargarEdad().getText());
                    String añosExperiencia = control.getCrearEquipo().cargarAñosExp().getText();

                    Capitan capitan = new Capitan(añosExperiencia, cedulaCapitan, nombreCapitan, cedulaCapitan);

                    // Crear el equipo
                    Equipo equipo = new Equipo(control.getCrearEquipo().cargarNombreEquipo().getText(), "", capitan, jugadores);
                    equipos.add(equipo);

                    // Ocultar la vista de creación de equipo
                    control.getCrearEquipo().setVisible(false);

                    // Volver a la vista inicial
                    control.getVistaInicio().setVisible(true);

                } catch (NumberFormatException e) {
                    control.getVentanaEmergente().ventanaError("Error en los campos numéricos");
                }
            }
        }
    }
}
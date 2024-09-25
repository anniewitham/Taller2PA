package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Persona;
import edu.avanzada.taller2.modelo.Serializacion;
import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.Juego;
import edu.avanzada.taller2.vista.VentanasEmergentes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlPrincipal implements ActionListener {

    private ControlEquipo controlEquipo;
    private ControlPartido controlPartido; 
    private VentanasEmergentes ventanaEmergente;
    private CrearEquipo vistaCrearEquipo;
    private Inicio vistaInicio;
    private Juego vistaJuego;

    public ControlPrincipal() throws IOException {
        controlEquipo = new ControlEquipo(this);
        ventanaEmergente = new VentanasEmergentes();

        vistaCrearEquipo = new CrearEquipo(this);
        vistaCrearEquipo.BotonSalir.addActionListener(this);
        vistaCrearEquipo.BotonVolver.addActionListener(this);
        vistaCrearEquipo.BotonRegistrar.addActionListener(this);

        vistaInicio = new Inicio(this);
        vistaInicio.BotonSalir.addActionListener(this);
        vistaInicio.BotonRegistrarEquipo.addActionListener(this);
        vistaInicio.BotonJugar.addActionListener(this);

        vistaJuego = new Juego(this);
        vistaJuego.BotonSalir.addActionListener(this);
        vistaJuego.BotonLanzarBola.addActionListener(this);
        vistaJuego.jRadioButton1.addActionListener(this);
    }

    public void iniciarPartido() throws IOException {
        ArrayList<Equipo> equipos = controlEquipo.getEquipos();
        if (equipos.size() >= 2) {
            controlPartido = new ControlPartido(equipos.get(0), equipos.get(1), this); 
            controlPartido.iniciarPartido();
        } else {
            ventanaEmergente.ventanaError("No hay suficientes equipos para iniciar el partido.");
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Salir":
                System.exit(0);
                break;
            case "Volver":
                vistaCrearEquipo.dispose();
                vistaCrearEquipo.resetearCamposCrearEquipo();
                vistaInicio.setVisible(true);
                break;
            case "Registrar Equipo":
                vistaInicio.dispose();
                vistaCrearEquipo.setVisible(true);
                break;
            case "Registrar":
                controlEquipo.crearEquipo();
                break;
            case "Jugar":
                try {
                    iniciarPartido(); // Llamar al método aquí para iniciar el partido
                } catch (IOException ex) {
                    Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    ventanaEmergente.ventanaError("Error al iniciar el partido.");
                }
                break;
            case "Lanzar":
                controlPartido.jugarTurno();
            default:
                break;
        }
    }

    /**
     * Obtiene la vista para crear un equipo.
     *
     * @
     * return La vista para crear un equipo
     *
     */
    public CrearEquipo getCrearEquipo() {
        return vistaCrearEquipo;
    }

    /**
     * Obtiene la ventana emergente.
     *
     * @return La ventana emergente.
     */
    public VentanasEmergentes getVentanaEmergente() {
        return ventanaEmergente;
    }

    /**
     * Obtiene la vista de inicio.
     *
     * @return La vista de inicio.
     */
    public Inicio getVistaInicio() {
        return vistaInicio;
    }

    /**
     * Obtiene la vista del juego.
     *
     * @return La vista del juego.
     */
    public Juego getVistaJuego() {
        return vistaJuego;
    }


    /**
     * Cargar personas deserializadas desde el archivo.
     *
     * @return Lista de personas deserializada o una lista vacía si ocurre un
     * error.
     */
    private List<Persona> cargarPersonas() {
        try {
            return Serializacion.deserializarPersonas();
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, "Error al cargar las personas: " + e.getMessage(), e);
            return new ArrayList<>(); // Retorna una lista vacía si hay un error.
        }
    }

    /**
     * Guardar lista de personas serializándola en el archivo.
     *
     * @param listaPersonas Lista de personas a serializar.
     * @throws IOException Si ocurre un error durante la serialización.
     */
    private void guardarPersonas(List<Persona> listaPersonas) throws IOException {
        try {
            Serializacion.serializarPersonas(listaPersonas);
        } catch (IOException e) {
            Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, "Error al guardar las personas: " + e.getMessage(), e);
            throw e; // Lanza la excepción para que sea manejada en otro nivel si es necesario.
        }
    }
}
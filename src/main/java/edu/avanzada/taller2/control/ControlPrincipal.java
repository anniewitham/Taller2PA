package edu.avanzada.taller2.control;

import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.Juego;
import edu.avanzada.taller2.vista.VentanasEmergentes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlPrincipal implements ActionListener {

    private ControlEquipo controlEquipo;
    private ControlPartido controlPartido;
    private VentanasEmergentes ventanaEmergente;
    private CrearEquipo vistaCrearEquipo;
    private Inicio vistaInicio;
    private Juego vistaJuego;
    private ControlPartido partido;

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
        vistaInicio.BotonJuez.addActionListener(this);
        vistaInicio.BotonJugar.addActionListener(this);
        
        vistaJuego = new Juego(this);
        vistaJuego.BotonSalir.addActionListener(this);
        vistaJuego.BotonLanzarTejo.addActionListener(this);
        vistaJuego.jRadioButton1.addActionListener(this);
        
        partido = new ControlPartido();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Salir".equals(e.getActionCommand())) {
            System.exit(0);
        }
        if ("Volver".equals(e.getActionCommand())) {
            vistaCrearEquipo.dispose();
            vistaCrearEquipo.resetearCamposCrearEquipo();
            vistaInicio.setVisible(true);
        }
        if ("Registrar Equipo".equals(e.getActionCommand())) {
            vistaInicio.dispose();
            vistaCrearEquipo.setVisible(true);
        }
        if ("Registrar".equals(e.getActionCommand())) {
            controlEquipo.crearEquipo();
            vistaCrearEquipo.dispose();
            vistaInicio.setVisible(true);
        }
        if ("Jugar".equals(e.getActionCommand())) {
            vistaJuego.setVisible(true);
            vistaInicio.dispose();
            try {
                partido.iniciarPartido();
            } catch (IOException ex) {
                Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public CrearEquipo getCrearEquipo() {
        return vistaCrearEquipo;
    }

    public VentanasEmergentes getVentanaEmergente() {
        return ventanaEmergente;
    }

    public Inicio getVistaInicio() {
        return vistaInicio;
    }

    public Juego getVistaJuego() {
        return vistaJuego;
    }
    
    

}

package edu.avanzada.taller2.control;

import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.RegistrarJuez;
import edu.avanzada.taller2.vista.VentanasEmergentes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPrincipal implements ActionListener {

    private ControlEquipo controlEquipo;
    private ControlPartido controlPartido;
    private VentanasEmergentes ventanaEmergente;

    private CrearEquipo vistaCrearEquipo;
    private Inicio vistaInicio;
    private RegistrarJuez vistaJuez;

    public ControlPrincipal() {
        controlEquipo = new ControlEquipo(this);
        
         ventanaEmergente = new VentanasEmergentes();

        vistaCrearEquipo = new CrearEquipo(this);
        vistaCrearEquipo.addSalirListener(this);
        vistaCrearEquipo.addVolverMenuListener(this);
        vistaCrearEquipo.addRegistarListener(this);

        vistaInicio = new Inicio(this);
        vistaInicio.addSalirListener(this);
        vistaInicio.addResgistrarEquipoListener(this);
        vistaInicio.addRegistarJuezListener(this);
        vistaInicio.addJugarListener(this);

        vistaJuez = new RegistrarJuez(this);
        vistaJuez.addSalirListener(this);
        vistaJuez.addVolverMenuListener(this);
        vistaJuez.addRegistarJuezListener(this);

        vistaInicio.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Salir".equals(e.getActionCommand())) {
            System.exit(0);
        }

        if ("Volver".equals(e.getActionCommand())) {
            vistaCrearEquipo.setVisible(false);
            vistaCrearEquipo.resetearCamposCrearEquipo();
            vistaJuez.setVisible(false);
            vistaJuez.resetearCamposRegistrarJuez();
        }

        if ("Registrar Equipo".equals(e.getActionCommand())) {
            vistaInicio.dispose();
            vistaCrearEquipo.setVisible(true);
        }

        if ("Registro Equipo".equals(e.getActionCommand())) {
            controlEquipo.crearEquipo();
        }

        if ("Registrar Juez".equals(e.getActionCommand())) {
            vistaInicio.dispose();
            vistaJuez.setVisible(true);
        }
        if ("Registro Juez".equals(e.getActionCommand())) {

        }

        if ("Jugar".equals(e.getActionCommand())) {

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

}

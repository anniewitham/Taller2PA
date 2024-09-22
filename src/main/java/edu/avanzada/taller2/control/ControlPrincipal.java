
package edu.avanzada.taller2.control;

import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.RegistrarJuez;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPrincipal implements ActionListener{
    private ControlEquipo controlEquipo;
    private ControlPartido controlPartido; 

    private CrearEquipo vistaCrearEquipo;
    private Inicio vistaInicio;
    private RegistrarJuez vistaJuez;

    public ControlPrincipal() {
        controlEquipo = new ControlEquipo(this);
        
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
       
       if ("Volver Menu".equals(e.getActionCommand())) {
           vistaCrearEquipo.setVisible(false);
           vistaCrearEquipo.resetearCamposCrearEquipo();
           vistaJuez.setVisible(false);
           vistaJuez.resetearCamposRegistrarJuez();
       }
    }
    
    public CrearEquipo getCrearEquipo() {
        return vistaCrearEquipo;
    }

}

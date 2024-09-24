
package edu.avanzada.taller2.control;

import edu.avanzada.taller2.vista.Inicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPrincipal implements ActionListener{
    private ControlEquipo controlEquipo;
    private ControlPartido controlPartido; 
    private Inicio inicio;
    
    public ControlPrincipal(){
        crearMenu();
        this.controlEquipo = new ControlEquipo();
    }
    
    public void crearMenu(){
        
        inicio = new Inicio(this);
        inicio.BotonJuez.addActionListener(this);
        inicio.BotonJugar.addActionListener(this);
        inicio.BotonRegistrarEquipo.addActionListener(this);
        inicio.BotonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Salir".equals(e.getActionCommand())){
            System.exit(0);
        }
        if ("Registrar Juez".equals(e.getActionCommand())){
            System.exit(0);
        }
        if ("Registrar Equipo".equals(e.getActionCommand())){
            controlEquipo.crearVistaCrearEquipo();
            inicio.dispose();
        }
        if ("Juega".equals(e.getActionCommand())){
            System.exit(0);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
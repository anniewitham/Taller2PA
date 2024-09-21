package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControlEquipo {

    private Properties arcProp;
    private ArrayList<Equipo> equipos;

    public ControlEquipo(Properties arcProp) {
        this.arcProp = arcProp;
        
    }


    public void cargarEquipos() {
        for (int i = 1; i <= 2; i++) {
            String nombreEquipo = arcProp.getProperty("equipo" + i + ".nombre");
            int numeroEquipo = Integer.parseInt(arcProp.getProperty("equipo" + i + ".numero"));
            
            // Crear capitán
            String capitanNombre = arcProp.getProperty("equipo" + i + ".capitan.nombre");
            int capitanEdad = Integer.parseInt(arcProp.getProperty("equipo" + i + ".capitan.edad"));
            String capitanCedula = arcProp.getProperty("equipo" + i + ".capitan.cedula");
            int aniosExp = Integer.parseInt(arcProp.getProperty("equipo" + i + ".capitan.añosExp"));
            Capitan capitan = new Capitan(aniosExp, capitanCedula, nombreEquipo, i);
            
            // Crear jugadores
            List<Jugador> jugadores = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String jugadorNombre = arcProp.getProperty("equipo" + i + ".jugador" + j + ".nombre");
                int jugadorEdad = Integer.parseInt(arcProp.getProperty("equipo" + i + ".jugador" + j + ".edad"));
                String jugadorCedula = arcProp.getProperty("equipo" + i + ".jugador" + j + ".cedula");
                int numeroPosicion = Integer.parseInt(arcProp.getProperty("equipo" + i + ".jugador" + j + ".numeroPo"));
                Jugador jugador = new Jugador(numeroPosicion, jugadorCedula, nombreEquipo, j);
                jugadores.add(jugador);
            }

            // Crear equipo y añadir a la lista
            Equipo equipo = new Equipo(nombreEquipo, numeroEquipo, capitan, jugadores);
            equipos.add(equipo);
        }
    }
    

   
}



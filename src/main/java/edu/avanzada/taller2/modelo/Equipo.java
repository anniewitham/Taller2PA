package edu.avanzada.taller2.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Representa a un equipo con sus atributos y métodos correspondientes.
 */
public class Equipo implements Serializable{

    private String nombreEquipo;
    private String numeroEquipo;
    private Capitan capitan;
    private List<Jugador> jugadores;

    /**
     * 
     *  Constructor que inicializa el equipo con su nombre, número, lista de jugadores y capitán.
     * 
     * @param nombreEquipo
     * @param numeroEquipo
     * @param capitan
     * @param jugadores 
     */
    public Equipo(String nombreEquipo, String numeroEquipo, Capitan capitan, List<Jugador> jugadores) {
        this.nombreEquipo = nombreEquipo;
        this.numeroEquipo = numeroEquipo;
        this.capitan = capitan;
        this.jugadores = jugadores;
    }
    
    //getters y setters
    public String getNombreEquipo() {
        return nombreEquipo;
    }

   
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

   
    public String getNumeroEquipo() {
        return numeroEquipo;
    }

    
    public void setNumeroEquipo(String numeroEquipo) {
        this.numeroEquipo = numeroEquipo;
    }

    
    public Capitan getCapitan() {
        return capitan;
    }

   
    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

   
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

}

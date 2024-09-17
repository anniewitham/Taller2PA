package edu.avanzada.taller2.modelo;

import java.util.List;

/**
 * Representa a un equipo con sus atributos y métodos correspondientes.
 */
public class Equipo {

    private String nombreEquipo;
    private int numeroEquipo;
    private Capitan capitan;
    private List<Jugador> jugadores;

    /**
     * Establece el nombre del equipo.
     *
     * @param nombreEquipo El nuevo nombre para el equipo.
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * Devuelve el número del equipo.
     *
     * @return El número del equipo.
     */
    public int getNumeroEquipo() {
        return numeroEquipo;
    }

    /**
     * Establece el número del equipo.
     *
     * @param numeroEquipo El nuevo número para el equipo.
     */
    public void setNumeroEquipo(int numeroEquipo) {
        this.numeroEquipo = numeroEquipo;
    }

    /**
     * Devuelve el capitán del equipo.
     *
     * @return El capitán del equipo.
     */
    public Capitan getCapitan() {
        return capitan;
    }

    /**
     * Establece el capitán del equipo.
     *
     * @param capitan El nuevo capitán para el equipo.
     */
    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

    /**
     * Devuelve la lista de jugadores del equipo.
     *
     * @return La lista de jugadores del equipo.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Establece la lista de jugadores del equipo.
     *
     * @param jugadores La nueva lista de jugadores para el equipo.
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

}

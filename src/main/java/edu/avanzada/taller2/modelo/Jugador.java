package edu.avanzada.taller2.modelo;

import java.io.Serializable;

/**
 * Representa a un jugador que extiende de la clase Persona, con número de
 * posición adicional.
 */
public class Jugador extends Persona implements Serializable{

    private String numeroPosicion;

    /**
     * Devuelve una representación en cadena del jugador.
     *
     * @return Una cadena con la información del jugador.
     */
    @Override
    public String toString() {
        return "Jugador: "
                + "/nNombre: " + getNombre()
                + "/nCedula: " + getCedula()
                + "/nEdad: " + getEdad()
                + "/nPosicion Numero: '" + numeroPosicion;
    }

    /**
     * Constructor que inicializa el jugador con su número de posición, cédula,
     * nombre y edad.
     *
     * @param numeroPosicion El número de posición del jugador.
     * @param cedula La cédula del jugador.
     * @param nombre El nombre del jugador.
     * @param edad La edad del jugador.
     */
    public Jugador(String numeroPosicion, String cedula, String nombre, String edad) {
        super(cedula, nombre, edad);
        this.numeroPosicion = numeroPosicion;
    }
    
    /**
     * Constructor que inicializa el jugador con su número de posición, cédula,
     * y nombre.
     * 
     * @param numeroPosicion
     * @param cedula
     * @param nombre 
     */
    public Jugador(String numeroPosicion, String cedula, String nombre) {
        super(cedula, nombre);
        this.numeroPosicion = numeroPosicion;
    }

    public String getNumeroPosicion() {
        return numeroPosicion;
    }

    public void setNumeroPosicion(String numeroPosicion) {
        this.numeroPosicion = numeroPosicion;
    }

    
    
    
}

package edu.avanzada.taller2.modelo;

/**
 * Representa a un jugador que extiende de la clase Persona, con número de
 * posición adicional.
 */
public class Jugador extends Persona {

    private int numeroPosicion;

    /**
     * Devuelve el número de posición del jugador.
     *
     * @return El número de posición del jugador.
     */
    public int getNumeroPosicion() {
        return numeroPosicion;
    }

    /**
     * Establece el número de posición del jugador.
     *
     * @param numeroPosicion El nuevo número de posición para el jugador.
     */
    public void setNumeroPosicion(int numeroPosicion) {
        this.numeroPosicion = numeroPosicion;
    }

}

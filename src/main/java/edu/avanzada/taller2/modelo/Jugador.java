package edu.avanzada.taller2.modelo;

/**
 * Representa a un jugador que extiende de la clase Persona, con número de
 * posición adicional.
 */
public class Jugador extends Persona {

    private int numeroPosicion;

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
    public Jugador(int numeroPosicion, String cedula, String nombre, int edad) {
        super(cedula, nombre, edad);
        this.numeroPosicion = numeroPosicion;
    }

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

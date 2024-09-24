package edu.avanzada.taller2.modelo;

/**
 * Representa a un jugador que extiende de la clase Persona, con número de
 * posición adicional.
 */
public class Jugador extends Persona {

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
    public Jugador( String cedula, String nombre, String edad) {
        super(cedula, nombre, edad);
    
    }

 

    
    
    /**
     * Devuelve el número de posición del jugador.
     *
     * @return El número de posición del jugador.
     */
    public String getNumeroPosicion() {
        return numeroPosicion;
    }

    /**
     * Establece el número de posición del jugador.
     *
     * @param numeroPosicion El nuevo número de posición para el jugador.
     */
    public void setNumeroPosicion(String numeroPosicion) {
        this.numeroPosicion = numeroPosicion;
    }
}

package edu.avanzada.taller2.modelo;

/**
 * Representa a un juez que extiende de la clase Persona, con número de tarjeta
 * profesional adicional.
 */
public class Juez extends Persona {

    private String numeroTarjetaProfesional;

    /**
     * Devuelve el número de tarjeta profesional del juez.
     *
     * @return El número de tarjeta profesional del juez.
     */
    public String getNumeroTarjetaProfesional() {
        return numeroTarjetaProfesional;
    }

    /**
     * Establece el número de tarjeta profesional del juez.
     *
     * @param numeroTarjetaProfesional El nuevo número de tarjeta profesional
     * para el juez.
     */
    public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional) {
        this.numeroTarjetaProfesional = numeroTarjetaProfesional;
    }

}

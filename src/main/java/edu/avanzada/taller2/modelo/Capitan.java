package edu.avanzada.taller2.modelo;

/**
 * Representa a un capitán que extiende de la clase Persona, con años de
 * experiencia adicionales.
 */
public class Capitan extends Persona {

    private int añosExperiencia;

    /**
     * Devuelve los años de experiencia del capitán.
     *
     * @return Los años de experiencia del capitán.
     */
    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    /**
     * Establece los años de experiencia del capitán.
     *
     * @param añosExperiencia Los nuevos años de experiencia para el capitán.
     */
    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

}

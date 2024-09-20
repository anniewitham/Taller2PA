package edu.avanzada.taller2.modelo;

/**
 * Representa a un capitán que extiende de la clase Persona, con años de
 * experiencia adicionales.
 */
public class Capitan extends Persona {

    private int añosExperiencia;

    /**
     * Devuelve una representación en cadena del capitan.
     *
     * @return Una cadena con la información del capitan.
     */
    @Override
    public String toString() {
        return "Capitan: "
                + "/nNombre: '" + getNombre()
                + "/nCedula: '" + getCedula()
                + "/nEdad: " + getEdad()
                + "/nAños de experiencia: '" + añosExperiencia;
    }

    /**
     * Constructor que inicializa el capitán con sus años de experiencia,
     * cédula, nombre y edad.
     *
     * @param añosExperiencia Los años de experiencia del capitán.
     * @param cedula La cédula del capitán.
     * @param nombre El nombre del capitán.
     * @param edad La edad del capitán.
     */
    public Capitan(int añosExperiencia, String cedula, String nombre, int edad) {
        super(cedula, nombre, edad);
        this.añosExperiencia = añosExperiencia;
    }

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

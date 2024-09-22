package edu.avanzada.taller2.modelo;

/**
 * Representa a un capitán que extiende de la clase Persona, con años de
 * experiencia adicionales.
 */
public class Capitan extends Persona {

    private String añosExperiencia;

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
    public Capitan(String añosExperiencia, String cedula, String nombre, String edad) {
        super(cedula, nombre, edad);
        this.añosExperiencia = añosExperiencia;
    }
    
    /**
     * Constructor que inicializa el capitán con sus años de experiencia,
     * cédula y nombre.
     * 
     * @param añosExperiencia
     * @param cedula
     * @param nombre 
     */
    public Capitan(String añosExperiencia, String cedula, String nombre) {
        super(cedula, nombre);
        this.añosExperiencia = añosExperiencia;
    }

    /**
     * Devuelve los años de experiencia del capitán.
     *
     * @return Los años de experiencia del capitán.
     */
    public String getAñosExperiencia() {
        return añosExperiencia;
    }

    /**
     * Establece los años de experiencia del capitán.
     *
     * @param añosExperiencia Los nuevos años de experiencia para el capitán.
     */
    public void setAñosExperiencia(String añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

}

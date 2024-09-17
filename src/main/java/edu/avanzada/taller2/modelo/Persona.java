package edu.avanzada.taller2.modelo;

/**
 * Representa a una persona con número de identificación, nombre y edad.
 */
public class Persona {

    private String cedula;
    private String nombre;
    private int edad;
    
    
    
    

    /**
     * Devuelve el número de identificación de la persona.
     *
     * @return El número de identificación de la persona.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece el número de identificación de la persona.
     *
     * @param cedula El nuevo número de identificación para la persona.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nuevo nombre para la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la edad de la persona.
     *
     * @return La edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la persona.
     *
     * @param edad La nueva edad para la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

}

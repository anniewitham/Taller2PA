package edu.avanzada.taller2.modelo;

import java.io.Serializable;

/**
 * Representa a un juez que extiende de la clase Persona, con número de tarjeta
 * profesional adicional.
 */
public class Juez extends Persona implements Serializable{

    private String numTarjetaProf;

    /**
     * Devuelve una representación en cadena del juez.
     *
     * @return Una cadena con la información del juez.
     */
    @Override
    public String toString() {
        return "Juez: "
                + "/nNombre: '" + getNombre()
                + "/nCedula: '" + getCedula()
                + "/nEdad: " + getEdad()
                + "/nDorsal: '" + numTarjetaProf + "'";
    }

    /**
     * Constructor que inicializa el juez con su número de tarjeta profesional,
     * cédula, nombre y edad.
     *
     * @param numeroTarjetaProfesional El número de tarjeta profesional del
     * juez.
     * @param cedula La cédula del juez.
     * @param nombre El nombre del juez.
     * @param edad La edad del juez.
     */
    public Juez(String numeroTarjetaProfesional, String cedula, String nombre, String edad) {
        super(cedula, nombre, edad);
        this.numTarjetaProf = numeroTarjetaProfesional;
    }
    
    /**
     * Constructor que inicializa el juez con su número de tarjeta profesional,
     * cédula y nombre.
     * 
     * @param numeroTarjetaProfesional
     * @param cedula
     * @param nombre 
     */
    public Juez(String numeroTarjetaProfesional, String cedula, String nombre) {
        super(cedula, nombre);
        this.numTarjetaProf = numeroTarjetaProfesional;
    }

    public String getNumTarjetaProf() {
        return numTarjetaProf;
    }

    public void setNumTarjetaProf(String numTarjetaProf) {
        this.numTarjetaProf = numTarjetaProf;
    }

    
    
    
}

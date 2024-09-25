package edu.avanzada.taller2.control;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ControlProperties es una clase encargada de gestionar la carga de propiedades
 * desde un archivo .properties. Proporciona métodos para obtener valores específicos
 * relacionados con el juego.
 */
public class ControlProperties {
    protected Properties properties;
 /**
     * Constructor de ControlProperties que inicializa el objeto Properties
     * y carga las propiedades desde el archivo especificado.
     * 
     * @throws IOException si ocurre un error al cargar el archivo de propiedades.
     */
    public ControlProperties() throws IOException {
        properties = new Properties();
        cargarProperties();
    }

     /**
     * Carga las propiedades desde el archivo "PrecargaJuego.properties".
     * 
     * @throws IOException si ocurre un error al leer el archivo.
     */
    
    private void cargarProperties() throws IOException {
        try (FileInputStream input = new FileInputStream("./specs/PrecargaJuego.properties")) {
            properties.load(input);
        }
    }
/**
     * Obtiene el valor de un hueco a partir de la clave proporcionada.
     * 
     * @param clave la clave de la propiedad que se desea obtener.
     * @param valorPorDefecto el valor por defecto que se devolverá si la clave no existe.
     * @return el valor del hueco como un entero.
     */
    public int obtenerValorHueco(String clave, String valorPorDefecto) {
        return Integer.parseInt(properties.getProperty(clave, valorPorDefecto));
    }
 /**
     * Obtiene el puntaje máximo a partir de la clave proporcionada.
     * 
     * @param clave la clave de la propiedad que se desea obtener.
     * @param valorPorDefecto el valor por defecto que se devolverá si la clave no existe.
     * @return el puntaje máximo como un entero.
     */
    public int obtenerPuntajeMaximo(String clave, String valorPorDefecto) {
        return Integer.parseInt(properties.getProperty(clave, valorPorDefecto));
    }
 /**
     * Obtiene el nombre del juez.
     * 
     * @return el nombre del juez, o "Desconocido" si no se encuentra.
     */
    public String obtenerNombreJuez() {
        return properties.getProperty("juez.nombre", "Desconocido");
    }
/**
     * Obtiene la cédula del juez.
     * 
     * @return la cédula del juez, o "0000000000" si no se encuentra.
     */
    public String obtenerCedulaJuez() {
        return properties.getProperty("juez.cedula", "0000000000");
    }
/**
     * Obtiene la edad del juez.
     * 
     * @return la edad del juez como un String, o "0" si no se encuentra.
     */
    public String obtenerEdadJuez() {
        return properties.getProperty("juez.edad", "0");
    }
/**
     * Obtiene el número de tarjeta del juez.
     * 
     * @return el número de tarjeta del juez, o "N/A" si no se encuentra.
     */
    public String obtenerNumeroTarjetaJuez() {
        return properties.getProperty("juez.numeroTarjeta", "N/A");
    }
}
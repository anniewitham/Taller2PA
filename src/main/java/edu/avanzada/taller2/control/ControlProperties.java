package edu.avanzada.taller2.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ControlProperties {
    private Properties properties;
    
    public ControlProperties() throws IOException{
        properties = new Properties();
        cargarProperties();
    }
    
    private void cargarProperties() throws IOException{
        try(FileInputStream input = new FileInputStream("./specs/PrecargaJuego.properties")){
            properties.load(input);
        }
    }
    
    public int obtenerValorHueco(String clave, String valorPorDefecto) {
        return Integer.parseInt(properties.getProperty(clave, valorPorDefecto));
    }
    
    public int obtenerPuntajeMaximo(String clave, String valorPorDefecto){
        return Integer.parseInt(properties.getProperty(clave, valorPorDefecto));
    }
}

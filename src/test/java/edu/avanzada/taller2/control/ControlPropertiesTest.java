
package edu.avanzada.taller2.control;

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ControlPropertiesTest {
    private ControlProperties instance;

    @BeforeEach
    public void setUp() throws IOException {
        instance = new ControlProperties(); // Inicializa el ControlProperties antes de cada prueba
    }
    
    @AfterEach
    public void tearDown() {
        instance = null; // Limpia la instancia después de cada prueba
    }

    /**
     * Test of obtenerValorHueco method, of class ControlProperties.
     */
    @Test
    public void testObtenerValorHueco_Existente() {
        // Caso exitoso: se debe retornar el valor correspondiente a una clave existente
        String clave = "hueco1"; // Asegúrate de que esta clave exista en tu archivo properties
        String valorPorDefecto = "0";
        int expResult = 10; // Cambia esto al valor que esperas
        int result = instance.obtenerValorHueco(clave, valorPorDefecto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerValorHueco_NoExistente() {
        // Caso de fallo: clave no existente debe retornar el valor por defecto
        String clave = "huecoInexistente"; // Clave que no existe
        String valorPorDefecto = "5";
        int expResult = 5; // Valor por defecto
        int result = instance.obtenerValorHueco(clave, valorPorDefecto);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerPuntajeMaximo method, of class ControlProperties.
     */
    @Test
    public void testObtenerPuntajeMaximo_Existente() {
        // Caso exitoso: se debe retornar el valor correspondiente a una clave existente
        String clave = "puntajeMaximo"; // Asegúrate de que esta clave exista en tu archivo properties
        String valorPorDefecto = "100";
        int expResult = 200; // Cambia esto al valor que esperas
        int result = instance.obtenerPuntajeMaximo(clave, valorPorDefecto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerPuntajeMaximo_NoExistente() {
        // Caso de fallo: clave no existente debe retornar el valor por defecto
        String clave = "puntajeInexistente"; // Clave que no existe
        String valorPorDefecto = "50";
        int expResult = 50; // Valor por defecto
        int result = instance.obtenerPuntajeMaximo(clave, valorPorDefecto);
        assertEquals(expResult, result);
    }
}

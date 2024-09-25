package edu.avanzada.taller2.control;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LauncherTest {
    
    public LauncherTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        // Configuraciones iniciales antes de todos los tests
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Limpieza después de todos los tests
    }
    
    @BeforeEach
    public void setUp() {
        // Configuraciones antes de cada test
    }
    
    @AfterEach
    public void tearDown() {
        // Limpieza después de cada test
    }

    
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        
        // Se usa try-catch para verificar que no lanza excepciones
        assertDoesNotThrow(() -> {
            Launcher.main(args);
        }, "El método main no debe lanzar excepciones.");
        
    }
}
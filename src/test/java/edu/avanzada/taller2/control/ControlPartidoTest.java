
package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Jugador;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase ControlPartido.
 */
public class ControlPartidoTest {

    private Equipo equipoA;
    private Equipo equipoB;
    private Juez juez;
    private ControlPartido controlPartido;
    
    @BeforeEach
public void setUp() {
    // Crear un capitán y un jugador para el equipo
    Capitan capitan = new Capitan("Capitan1", "123456", "Juan", "30");
    Jugador jugador = new Jugador("Jugador1", "654321", "Pedro", "25");

    // Crear equipos A y B con todos los parámetros necesarios
    equipoA = new Equipo("Equipo A", "1", capitan, Arrays.asList(jugador));
    equipoB = new Equipo("Equipo B", "2", capitan, Arrays.asList(jugador));

    // Crear un juez con un número de tarjeta profesional
    juez = new Juez("Juez123", "987654", "Juez 1");

    // Inicializar el controlador de partido
    controlPartido = new ControlPartido(equipoA, equipoB, juez, 300); // Puntaje máximo: 300
}

    /**
     * Verifica que el partido inicie correctamente.
     */
    @Test
    public void testIniciarPartidoCorrectamente() {
        controlPartido.iniciarPartido();
        assertTrue(controlPartido.isPartidoEnCurso(), "El partido debería haber iniciado.");
    }

    /**
     * Verifica que iniciar un partido en curso arroje una excepción.
     */
    @Test
    public void testIniciarPartidoYaIniciado() {
        controlPartido.iniciarPartido();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controlPartido.iniciarPartido();
        });
        assertEquals("El partido ya está en curso.", exception.getMessage());
    }

    /**
     * Verifica que simular el puntaje antes de iniciar el partido arroje una excepción.
     */
    @Test
    public void testSimularPuntajeSinIniciarPartido() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controlPartido.simularPuntaje();
        });
        assertEquals("El partido no ha comenzado aún.", exception.getMessage());
    }

    /**
     * Verifica que se simulen los puntajes correctamente.
     */
    @Test
    public void testSimularPuntajeCorrecto() {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje();
        assertTrue(controlPartido.getPuntajeA() > 0 || controlPartido.getPuntajeB() > 0, "Los puntajes deberían haberse actualizado.");
    }

    /**
     * Verifica que simular puntaje después de terminar el partido arroje una excepción.
     */
    @Test
    public void testSimularPuntajeDespuesDeTerminarPartido() {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje();
        controlPartido.finalizarPartido();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controlPartido.simularPuntaje();
        });
        assertEquals("El partido ya ha terminado.", exception.getMessage());
    }

    /**
     * Verifica que se finalice el partido correctamente.
     */
    @Test
    public void testFinalizarPartidoCorrectamente() {
        controlPartido.iniciarPartido();
        controlPartido.finalizarPartido();
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido debería haber terminado.");
    }

    /**
     * Verifica que finalizar un partido no iniciado arroje una excepción.
     */
    @Test
    public void testFinalizarPartidoSinIniciar() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controlPartido.finalizarPartido();
        });
        assertEquals("El partido no está en curso.", exception.getMessage());
    }

    /**
     * Verifica que se reinicie el partido correctamente.
     */
    @Test
    public void testReiniciarPartido() {
        controlPartido.iniciarPartido();
        controlPartido.reiniciarPartido();
        assertEquals(0, controlPartido.getPuntajeA(), "El puntaje de Equipo A debería ser 0.");
        assertEquals(0, controlPartido.getPuntajeB(), "El puntaje de Equipo B debería ser 0.");
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido no debería estar en curso después del reinicio.");
    }

    /**
     * Verifica que los métodos get y set de los equipos funcionen correctamente.
     */
   /**
 * Verifica que los métodos set y get de los equipos funcionen correctamente.
 */
@Test
public void testSetYGetEquipos() {
    // Crear un capitán
    Capitan capitan = new Capitan("Capitan1", "123456", "Juan", "30");

    // Crear un jugador
    Jugador jugador = new Jugador("654321", "Pedro", "25"); // Asegúrate de que el constructor sea correcto

    // Crear un nuevo equipo A
    Equipo nuevoEquipoA = new Equipo("Nuevo Equipo A", "1", capitan, Arrays.asList(jugador));
    controlPartido.setEquipoA(nuevoEquipoA);
    assertEquals(nuevoEquipoA, controlPartido.getEquipoA(), "El equipo A debería ser 'Nuevo Equipo A'.");

    // Crear un nuevo equipo B
    Equipo nuevoEquipoB = new Equipo("Nuevo Equipo B", "2", capitan, Arrays.asList(jugador));
    controlPartido.setEquipoB(nuevoEquipoB);
    assertEquals(nuevoEquipoB, controlPartido.getEquipoB(), "El equipo B debería ser 'Nuevo Equipo B'.");
}

/**
 * Verifica que no se permita asignar un equipo nulo.
 */
@Test
public void testSetEquipoNulo() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        controlPartido.setEquipoA(null);
    });
    assertEquals("El equipo A no puede ser nulo.", exception.getMessage());

    exception = assertThrows(IllegalArgumentException.class, () -> {
        controlPartido.setEquipoB(null);
    });
    assertEquals("El equipo B no puede ser nulo.", exception.getMessage());
}

/**
 * Verifica que los métodos set y get del juez funcionen correctamente.
 */
@Test
public void testSetYGetJuez() {
    // Asumiendo que el constructor de Juez requiere número de tarjeta, cédula y nombre
    Juez nuevoJuez = new Juez("T123", "987654", "Nuevo Juez");
    controlPartido.setJuez(nuevoJuez);
    assertEquals(nuevoJuez, controlPartido.getJuez(), "El juez debería ser 'Nuevo Juez'.");
}

/**
 * Verifica que no se permita asignar un juez nulo.
 */
@Test
public void testSetJuezNulo() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        controlPartido.setJuez(null);
    });
    assertEquals("El juez no puede ser nulo.", exception.getMessage());
}
}


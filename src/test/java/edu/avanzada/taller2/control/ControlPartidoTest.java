package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el controlador de partidos (ControlPartido).
 * Esta clase contiene métodos de prueba que verifican el comportamiento
 * del controlador de partidos en diferentes situaciones.
 */
class ControlPartidoTest {

    protected ControlPartido controlPartido; // Instancia del controlador del partido
    protected Equipo equipoA; // Equipo A para las pruebas
    protected Equipo equipoB; // Equipo B para las pruebas
    protected Juez juez; // Juez para el partido
    protected ControlPrincipal controlPrincipal; // Instancia del controlador principal

    /**
     * Configura los objetos necesarios antes de cada método de prueba.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @BeforeEach
    void setUp() throws IOException {
        List<Jugador> jugadoresA = new ArrayList<>(); // Lista de jugadores para el equipo A
        List<Jugador> jugadoresB = new ArrayList<>(); // Lista de jugadores para el equipo B

        // Crear instancias de capitanes
        Capitan capitanA = new Capitan("Capitán A", "Cédula A", "30");
        Capitan capitanB = new Capitan("Capitán B", "Cédula B", "32");

        // Crea las instancias de los equipos
        equipoA = new Equipo("Equipo A", "1", capitanA, jugadoresA);
        equipoB = new Equipo("Equipo B", "2", capitanB, jugadoresB);

        // Crea la instancia del juez
        juez = new Juez("Juez 1", "CédulaJuez", "EdadJuez", "12345");

        // Crea la instancia del controlador principal
        controlPrincipal = new ControlPrincipal();

        // Crea una nueva instancia de ControlPartido
        controlPartido = new ControlPartido(equipoA, equipoB, controlPrincipal);
        controlPartido.setJuez(juez); // Asigna el juez al controlador
    }

    /**
     * Prueba que el partido se inicia correctamente.
     * Verifica que el estado del partido se establece como en curso.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testIniciarPartido_Success() throws IOException {
        controlPartido.iniciarPartido();
        assertTrue(controlPartido.isPartidoEnCurso(), "El partido debería estar en curso.");
    }

    /**
     * Prueba que no se puede iniciar un partido que ya está en curso.
     * Verifica que se lanza una excepción con el mensaje adecuado.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testIniciarPartido_AlreadyInProgress() throws IOException {
        controlPartido.iniciarPartido();
        Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.iniciarPartido());
        assertEquals("El partido ya está en curso.", exception.getMessage());
    }

    /**
     * Prueba que no se puede iniciar el partido si el equipo A es nulo.
     * Verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    void testIniciarPartido_NullEquipoA() {
        controlPartido.setEquipoA(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> controlPartido.iniciarPartido());
        assertEquals("Equipo A, equipo B y juez no pueden ser nulos.", exception.getMessage());
    }

    /**
     * Prueba que se puede simular un puntaje después de iniciar el partido.
     * Verifica que al menos uno de los equipos tenga un puntaje mayor que cero.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testSimularPuntaje_Success() throws IOException {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje();
        assertTrue(controlPartido.getPuntajeA() > 0 || controlPartido.getPuntajeB() > 0, 
                   "El puntaje de al menos uno de los equipos debería ser mayor que cero.");
    }

    /**
     * Prueba que no se puede simular un puntaje si el partido no ha comenzado.
     * Verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    void testSimularPuntaje_NotInProgress() {
        Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.simularPuntaje());
        assertEquals("El partido no ha comenzado aún.", exception.getMessage());
    }

    /**
     * Prueba que se puede finalizar un partido iniciado.
     * Verifica que el estado del partido cambia a no en curso después de finalizarlo.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testFinalizarPartido_Success() throws IOException {
        controlPartido.iniciarPartido();
        controlPartido.finalizarPartido();
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido debería haber finalizado.");
    }

    /**
     * Prueba que no se puede finalizar un partido que no está en curso.
     * Verifica que se lanza una excepción con el mensaje adecuado.
     */
    @Test
    void testFinalizarPartido_NotInProgress() {
        Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.finalizarPartido());
        assertEquals("El partido no está en curso.", exception.getMessage());
    }

    /**
     * Prueba que se puede reiniciar un partido.
     * Verifica que los puntajes se restablezcan a cero después de reiniciar.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testReiniciarPartido() throws IOException {
        controlPartido.iniciarPartido();
        controlPartido.reiniciarPartido();
        assertEquals(0, controlPartido.getPuntajeA(), "El puntaje del equipo A debería ser 0.");
        assertEquals(0, controlPartido.getPuntajeB(), "El puntaje del equipo B debería ser 0.");
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido debería haber sido reiniciado.");
    }
}

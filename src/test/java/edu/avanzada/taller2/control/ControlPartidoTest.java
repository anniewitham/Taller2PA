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

class ControlPartidoTest {

    protected ControlPartido controlPartido; // Instancia del controlador del partido
    protected Equipo equipoA; // Equipo A para las pruebas
    protected Equipo equipoB; // Equipo B para las pruebas
    protected Juez juez; // Juez para el partido
    protected ControlPrincipal controlPrincipal; // Instancia del controlador principal

     @BeforeEach
    void setUp() throws IOException {
        // Inicializa los objetos necesarios para las pruebas antes de cada método de prueba
        List<Jugador> jugadoresA = new ArrayList<>(); // Lista de jugadores para el equipo A
        List<Jugador> jugadoresB = new ArrayList<>(); // Lista de jugadores para el equipo B

        // Crear instancias de capitanes
        Capitan capitanA = new Capitan("Capitán A", "Cédula A", "30"); // Asegúrate de ajustar la cédula y edad según sea necesario
        Capitan capitanB = new Capitan("Capitán B", "Cédula B", "32"); // Asegúrate de ajustar la cédula y edad según sea necesario

        // Crea las instancias de los equipos con nombre, número, capitán y lista de jugadores
        equipoA = new Equipo("Equipo A", "1", capitanA, jugadoresA);
        equipoB = new Equipo("Equipo B", "2", capitanB, jugadoresB);

        // Crea la instancia del juez con nombre, cédula, edad y número de tarjeta profesional
        juez = new Juez("Juez 1", "CédulaJuez", "EdadJuez", "12345");

        // Crea la instancia del controlador principal
        controlPrincipal = new ControlPrincipal();

        // Utiliza el constructor adecuado para ControlPartido
        controlPartido = new ControlPartido(equipoA, equipoB, controlPrincipal); // Crea una nueva instancia de ControlPartido
        controlPartido.setJuez(juez); // Asigna el juez al controlador
    }

    @Test
    void testIniciarPartido_Success() throws IOException {
        // Intenta iniciar el partido y verifica que se haya iniciado correctamente
        controlPartido.iniciarPartido();
        assertTrue(controlPartido.isPartidoEnCurso(), "El partido debería estar en curso.");
    }

    @Test
    void testIniciarPartido_AlreadyInProgress() throws IOException {
        // Inicia el partido y luego intenta iniciarlo de nuevo para verificar que lanza la excepción
        controlPartido.iniciarPartido();
        Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.iniciarPartido());
        assertEquals("El partido ya está en curso.", exception.getMessage());
    }

    @Test
    void testIniciarPartido_NullEquipoA() {
        // Establece el equipo A como nulo y verifica que lanza una excepción al intentar iniciar el partido
        controlPartido.setEquipoA(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> controlPartido.iniciarPartido());
        assertEquals("Equipo A, equipo B y juez no pueden ser nulos.", exception.getMessage());
    }

   @Test
void testSimularPuntaje_Success() throws IOException {
    // Inicia el partido y simula un puntaje
    controlPartido.iniciarPartido();
    controlPartido.simularPuntaje(); // No se pasa el equipo
    assertTrue(controlPartido.getPuntajeA() > 0 || controlPartido.getPuntajeB() > 0, 
               "El puntaje de al menos uno de los equipos debería ser mayor que cero.");
}

@Test
void testSimularPuntaje_NotInProgress() {
    // Intenta simular un puntaje sin que el partido haya comenzado y verifica que lanza la excepción
    Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.simularPuntaje());
    assertEquals("El partido no ha comenzado aún.", exception.getMessage());
}


    @Test
    void testFinalizarPartido_Success() throws IOException {
        // Inicia el partido, lo finaliza y verifica que el estado cambie a no en curso
        controlPartido.iniciarPartido();
        controlPartido.finalizarPartido();
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido debería haber finalizado.");
    }

    @Test
    void testFinalizarPartido_NotInProgress() {
        // Intenta finalizar un partido que no está en curso y verifica que lanza la excepción
        Exception exception = assertThrows(IllegalStateException.class, () -> controlPartido.finalizarPartido());
        assertEquals("El partido no está en curso.", exception.getMessage());
    }

    @Test
    void testReiniciarPartido() throws IOException {
        // Inicia el partido, lo reinicia y verifica que los puntajes se restablezcan a cero
        controlPartido.iniciarPartido();
        controlPartido.reiniciarPartido();
        assertEquals(0, controlPartido.getPuntajeA(), "El puntaje del equipo A debería ser 0.");
        assertEquals(0, controlPartido.getPuntajeB(), "El puntaje del equipo B debería ser 0.");
        assertFalse(controlPartido.isPartidoEnCurso(), "El partido debería haber sido reiniciado.");
    }
}

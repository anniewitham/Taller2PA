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

public class ControlPartidoTest {

    private ControlPartido controlPartido;
    private Equipo equipoA;
    private Equipo equipoB;
    private Juez juez;

    @BeforeEach
    public void setUp() throws IOException {
        Capitan capitanA = new Capitan("34", "3405969","jose", "49");
        Capitan capitanB = new Capitan("24", "890", "pedrito","23");
        List<Jugador> jugadoresA = new ArrayList<>();
        List<Jugador> jugadoresB = new ArrayList<>();

        equipoA = new Equipo("Equipo A", "001", capitanA, jugadoresA);
        equipoB = new Equipo("Equipo B", "002", capitanB, jugadoresB);
        juez = new Juez("Juez 1", "12345", "Pedro");
        controlPartido = new ControlPartido(equipoA, equipoB, juez, 100);
    }

    @Test
public void testConstructorConParametrosValidos() {
    // Asegurarse de que el controlPartido no sea nulo
    assertNotNull(controlPartido);

    // Verificar que los equipos y el juez se establecieron correctamente
    assertEquals(equipoA, controlPartido.getEquipoA());
    assertEquals(equipoB, controlPartido.getEquipoB());
    assertEquals(juez, controlPartido.getJuez());
    
    // Verificar que el puntaje máximo sea 5000
    assertEquals(5000, controlPartido.getPuntajeMaximo());
}

    @Test
    public void testConstructorConParametrosNulos() {
        assertThrows(IllegalArgumentException.class, () -> new ControlPartido(null, equipoB, juez, 100));
        assertThrows(IllegalArgumentException.class, () -> new ControlPartido(equipoA, null, juez, 100));
        assertThrows(IllegalArgumentException.class, () -> new ControlPartido(equipoA, equipoB, null, 100));
    }

    @Test
    public void testIniciarPartido() {
        controlPartido.iniciarPartido();
        assertTrue(controlPartido.isPartidoEnCurso());
    }

    @Test
    public void testIniciarPartidoYaEnCurso() {
        controlPartido.iniciarPartido();
        assertThrows(IllegalStateException.class, () -> controlPartido.iniciarPartido());
    }

    @Test
    public void testSimularPuntajeConPartidoNoIniciado() {
        assertThrows(IllegalStateException.class, () -> controlPartido.simularPuntaje(equipoA));
    }

    @Test
    public void testSimularPuntajeEquipoA() {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje(equipoA);
        assertTrue(controlPartido.getPuntajeA() > 0);
    }

    @Test
    public void testSimularPuntajeEquipoB() {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje(equipoB);
        assertTrue(controlPartido.getPuntajeB() > 0);
    }

    @Test
    public void testFinalizarPartido() {
        controlPartido.iniciarPartido();
        controlPartido.finalizarPartido();
        assertFalse(controlPartido.isPartidoEnCurso());
    }

    @Test
    public void testFinalizarPartidoNoEnCurso() {
        assertThrows(IllegalStateException.class, () -> controlPartido.finalizarPartido());
    }

    @Test
    public void testReiniciarPartido() {
        controlPartido.iniciarPartido();
        controlPartido.simularPuntaje(equipoA);
        controlPartido.reiniciarPartido();
        assertEquals(0, controlPartido.getPuntajeA());
        assertEquals(0, controlPartido.getPuntajeB());
        assertFalse(controlPartido.isPartidoEnCurso());
    }

    @Test
    public void testSetEquipoA() {
        Capitan nuevoCapitanA = new Capitan("34", "3405969","jose", "49");
        List<Jugador> nuevosJugadoresA = new ArrayList<>();
    nuevosJugadoresA.add(new Jugador("Jugador A1", "C1", "F", "20"));
    nuevosJugadoresA.add(new Jugador("Jugador A2", "C2", "G", "21"));
    nuevosJugadoresA.add(new Jugador("Jugador A3", "C3", "H", "22"));
    nuevosJugadoresA.add(new Jugador("Jugador A4", "C4", "I", "23"));
    nuevosJugadoresA.add(new Jugador("Jugador A5", "C5", "eJ", "24"));

        Equipo nuevoEquipoA = new Equipo("Nuevo Equipo A", "003", nuevoCapitanA, nuevosJugadoresA);
        controlPartido.setEquipoA(nuevoEquipoA);
        assertEquals(nuevoEquipoA, controlPartido.getEquipoA());
    }

    @Test
    public void testSetEquipoANulo() {
        assertThrows(IllegalArgumentException.class, () -> controlPartido.setEquipoA(null));
    }

    @Test
public void testSetEquipoB() {
    // Crear un nuevo capitán con todos los parámetros requeridos
    Capitan nuevoCapitanB = new Capitan("24", "890", "pedrito","23");

    // Crear una lista de nuevos jugadores con todos los parámetros requeridos
    List<Jugador> nuevosJugadoresB = new ArrayList<>();
    nuevosJugadoresB.add(new Jugador("Jugador B1", "C1", "a", "20"));
    nuevosJugadoresB.add(new Jugador("Jugador B2", "C2", "b", "21"));
    nuevosJugadoresB.add(new Jugador("Jugador B3", "C3", "c", "22"));
    nuevosJugadoresB.add(new Jugador("Jugador B4", "C4", "d", "23"));
    nuevosJugadoresB.add(new Jugador("Jugador B5", "C5", "e", "24"));

    // Crear un nuevo equipo con el capitán y la lista de jugadores
    Equipo nuevoEquipoB = new Equipo("Nuevo Equipo B", "004", nuevoCapitanB, nuevosJugadoresB);

    // Establecer el nuevo equipo B en controlPartido y verificar que se haya establecido correctamente
    controlPartido.setEquipoB(nuevoEquipoB);
    assertEquals(nuevoEquipoB, controlPartido.getEquipoB());
}

    @Test
    public void testSetEquipoBNulo() {
        assertThrows(IllegalArgumentException.class, () -> controlPartido.setEquipoB(null));
    }

    @Test
    public void testSetJuez() {
        Juez nuevoJuez = new Juez("Juez 2", "67890", "Juan");
        controlPartido.setJuez(nuevoJuez);
        assertEquals(nuevoJuez, controlPartido.getJuez());
    }

    @Test
    public void testSetJuezNulo() {
        assertThrows(IllegalArgumentException.class, () -> controlPartido.setJuez(null));
    }

    // Prueba para cargarHuecosDesdeProperties() podría incluirse aquí si se desea
}


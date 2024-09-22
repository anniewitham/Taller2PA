package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControlEquipoTest {

    private ControlEquipo control;
    private Equipo equipo;

    @BeforeEach
    public void setUp() {
        Capitan capitan = new Capitan("5", "123456789", "Capitan", "30"); // Ajuste de parámetros de Capitan
        List<Jugador> jugadores = new ArrayList<>(); // Lista vacía de jugadores
        this.equipo = new Equipo("Equipo A", "1", capitan, jugadores); // Creación de un nuevo equipo con nombres adecuados
        this.control = new ControlEquipo(equipo); // Instanciación del controlador
    }

    @Test
    public void testAgregarJugadorExito() {
        Jugador jugador = new Jugador("1", "987654321", "Jugador A", "25");
        boolean result = control.agregarJugador(jugador);
        assertTrue(result); 
        assertEquals(1, control.obtenerJugadores().size()); 
    }

    @Test
    public void testAgregarJugadorFalloEquipoCompleto() {
        for (int i = 1; i <= 5; i++) {
            control.agregarJugador(new Jugador(String.valueOf(i), "jugador" + i, "Jugador " + i, "20"));
        }
        Jugador jugadorExtra = new Jugador("6", "999999999", "Jugador Extra", "22");
        boolean result = control.agregarJugador(jugadorExtra);
        assertFalse(result);
        assertEquals(5, control.obtenerJugadores().size()); // Verifica que se mantienen los 5 jugadores originales
    }

    @Test
    public void testEliminarJugadorExito() {
        Jugador jugador = new Jugador("1", "987654321", "Jugador A", "25");
        control.agregarJugador(jugador);
        boolean result = control.eliminarJugador(jugador);
        assertTrue(result); 
        assertEquals(0, control.obtenerJugadores().size());
    }

    @Test
    public void testEliminarJugadorFalloNoEstaEnEquipo() {
        Jugador jugador1 = new Jugador("1", "987654321", "Jugador A", "25");
        Jugador jugador2 = new Jugador("2", "123456789", "Jugador B", "22");
        control.agregarJugador(jugador1);
        boolean result = control.eliminarJugador(jugador2);
        assertFalse(result);
        assertEquals(1, control.obtenerJugadores().size());
    }

    @Test
    public void testEliminarJugadorFalloNulo() {
        boolean result = control.eliminarJugador(null);
        assertFalse(result); 
    }

    @Test
    public void testAsignarCapitanExito() {
        Capitan nuevoCapitan = new Capitan("3", "222222222", "Nuevo Capitan", "35");
        control.asignarCapitan(nuevoCapitan);
        assertEquals(nuevoCapitan, control.obtenerCapitan()); 
    }

    @Test
    public void testAsignarCapitanFalloNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.asignarCapitan(null);
        });
        assertEquals("El capitán no puede ser nulo.", exception.getMessage());
    }

    @Test
    public void testAsignarCapitanFalloYaHayUno() {
        Capitan nuevoCapitan = new Capitan("3", "222222222", "Nuevo Capitan", "35");
        control.asignarCapitan(nuevoCapitan);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.asignarCapitan(nuevoCapitan); 
        });
        assertEquals("Ya hay un capitán asignado al equipo.", exception.getMessage());
    }

    @Test
    public void testCambiarCapitanExito() {
        Capitan nuevoCapitan = new Capitan("3", "222222222", "Nuevo Capitan", "35");
        boolean result = control.cambiarCapitan(nuevoCapitan);
        assertTrue(result);
        assertEquals(nuevoCapitan, control.obtenerCapitan());
    }

    @Test
    public void testCambiarCapitanFalloMismoCapitan() {
        Capitan actualCapitan = equipo.getCapitan();
        boolean result = control.cambiarCapitan(actualCapitan);
        assertFalse(result);
    }

    @Test
    public void testCambiarCapitanFalloNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.cambiarCapitan(null);
        });
        assertEquals("El nuevo capitán no puede ser nulo.", exception.getMessage());
    }

    @Test
    public void testEstaCompletoFalso() {
        assertFalse(control.estaCompleto()); 
        control.agregarJugador(new Jugador("1", "123456789", "Jugador A", "25"));
        assertFalse(control.estaCompleto());
    }

    @Test
    public void testEstaCompletoVerdadero() {
        for (int i = 1; i <= 5; i++) {
            control.agregarJugador(new Jugador(String.valueOf(i), "jugador" + i, "Jugador " + i, "20"));
        }
        assertTrue(control.estaCompleto());
    }

    @Test
    public void testObtenerJugadores() {
        Jugador jugador = new Jugador("1", "987654321", "Jugador A", "25");
        control.agregarJugador(jugador);
        List<Jugador> jugadores = control.obtenerJugadores();
        assertEquals(1, jugadores.size());
        assertEquals(jugador, jugadores.get(0));
    }

    @Test
    public void testObtenerCapitan() {
        Capitan capitan = equipo.getCapitan(); 
        assertEquals(capitan, control.obtenerCapitan());
    }

    @Test
    public void testObtenerCapitanFalloSinCapitan() {
        equipo.setCapitan(null);
        assertNull(control.obtenerCapitan());
    }
}




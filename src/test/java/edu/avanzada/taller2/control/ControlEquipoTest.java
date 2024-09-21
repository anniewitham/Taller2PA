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

    private ControlEquipo control; // Controlador que se probará
    private Equipo equipo; // Equipo que se usará en las pruebas

    @BeforeEach
    public void setUp() {
        // Inicializa un capitán y una lista vacía de jugadores antes de cada prueba
        Capitan capitan = new Capitan(5, "123456789", "Equipo A", 1);
        List<Jugador> jugadores = new ArrayList<>(); // Lista vacía de jugadores
        this.equipo = new Equipo("Equipo A", 1, capitan, jugadores); // Crea un nuevo equipo
        this.control = new ControlEquipo(equipo); // Crea una instancia del controlador
    }

    @Test
    public void testAgregarJugadorExito() {
        // Prueba agregar un jugador exitosamente
        Jugador jugador = new Jugador(1, "987654321", "Equipo A", 1);
        boolean result = control.agregarJugador(jugador);
        assertTrue(result); // Verifica que la operación fue exitosa
        assertEquals(1, control.obtenerJugadores().size()); // Verifica que el jugador fue agregado
    }

    @Test
    public void testAgregarJugadorFalloEquipoCompleto() {
        // Prueba agregar un jugador cuando el equipo ya está completo
        for (int i = 1; i <= 5; i++) {
            control.agregarJugador(new Jugador(i, "jugador" + i, "Equipo A", i));
        }
        Jugador jugador = new Jugador(6, "999999999", "Equipo A", 6); // Jugador extra que no se debería agregar
        boolean result = control.agregarJugador(jugador);
        assertFalse(result); // Verifica que la operación falló
    }

   @Test
public void testEliminarJugadorExito() {
    // Prueba eliminar un jugador exitosamente
    Jugador jugador = new Jugador(1, "987654321", "Equipo A", 1);
    control.agregarJugador(jugador); // Agrega el jugador al equipo
    boolean result = control.eliminarJugador(jugador); // Intenta eliminarlo
    assertTrue(result); // Verifica que la operación fue exitosa
    assertEquals(0, control.obtenerJugadores().size()); // Verifica que el jugador fue eliminado
}

@Test
public void testEliminarJugadorFalloNoEstaEnEquipo() {
    // Prueba eliminar un jugador que no está en el equipo
    Jugador jugador1 = new Jugador(1, "987654321", "Equipo A", 1); // Jugador que se agregará
    Jugador jugador2 = new Jugador(2, "123456789", "Equipo A", 2); // Jugador que no se agregará
    control.agregarJugador(jugador1); // Agrega solo jugador1 al equipo
    boolean result = control.eliminarJugador(jugador2); // Intenta eliminar jugador2, que no está en el equipo
    assertFalse(result); // Verifica que la operación falló
    assertEquals(1, control.obtenerJugadores().size()); // Verifica que el número de jugadores no cambió
}


    @Test
    public void testEliminarJugadorFalloNulo() {
        // Prueba eliminar un jugador nulo
        boolean result = control.eliminarJugador(null);
        assertFalse(result); // Verifica que la operación falló
    }

    
    @Test
    public void testAsignarCapitanExito() {
        // Prueba asignar un capitán exitosamente
        Capitan nuevoCapitan = new Capitan(3, "222222222", "Equipo A", 1);
        control.asignarCapitan(nuevoCapitan); // Asigna el nuevo capitán
        assertEquals(nuevoCapitan, control.obtenerCapitan()); // Verifica que el nuevo capitán fue asignado
    }

    @Test
    public void testAsignarCapitanFalloNulo() {
        // Prueba asignar un capitán nulo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.asignarCapitan(null); // Intenta asignar un capitán nulo
        });
        assertEquals("El capitán no puede ser nulo.", exception.getMessage()); // Verifica que se lanza la excepción correcta
    }

    @Test
    public void testAsignarCapitanFalloYaHayUno() {
        // Prueba asignar un capitán cuando ya hay uno
        Capitan nuevoCapitan = new Capitan(3, "222222222", "Equipo A", 1);
        control.asignarCapitan(nuevoCapitan); // Asigna un capitán
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.asignarCapitan(nuevoCapitan); // Intenta asignar el mismo capitán
        });
        assertEquals("Ya hay un capitán asignado al equipo.", exception.getMessage()); // Verifica la excepción
    }

    @Test
    public void testCambiarCapitanExito() {
        // Prueba cambiar al capitán exitosamente
        Capitan nuevoCapitan = new Capitan(3, "222222222", "Equipo A", 1);
        control.cambiarCapitan(nuevoCapitan); // Cambia al capitán
        assertEquals(nuevoCapitan, control.obtenerCapitan()); // Verifica que el nuevo capitán fue asignado
    }

    @Test
    public void testCambiarCapitanFalloMismoCapitan() {
        // Prueba cambiar al capitán por el mismo capitán
        Capitan actualCapitan = equipo.getCapitan();
        boolean result = control.cambiarCapitan(actualCapitan); // Intenta cambiar al mismo capitán
        assertFalse(result); // Verifica que la operación falló
    }

    @Test
    public void testCambiarCapitanFalloNulo() {
        // Prueba cambiar a un capitán nulo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            control.cambiarCapitan(null); // Intenta cambiar a un capitán nulo
        });
        assertEquals("El nuevo capitán no puede ser nulo.", exception.getMessage()); // Verifica la excepción
    }

    @Test
    public void testEstaCompletoFalso() {
        // Prueba que el equipo no esté completo
        assertFalse(control.estaCompleto()); // Verifica que no está completo
        control.agregarJugador(new Jugador(1, "123456789", "Equipo A", 1)); // Agrega un jugador
        assertFalse(control.estaCompleto()); // Verifica que sigue sin estar completo
    }

    @Test
    public void testEstaCompletoVerdadero() {
        // Prueba que el equipo esté completo
        for (int i = 1; i <= 5; i++) {
            control.agregarJugador(new Jugador(i, "jugador" + i, "Equipo A", i)); // Agrega jugadores
        }
        assertTrue(control.estaCompleto()); // Verifica que el equipo está completo
    }

    @Test
    public void testObtenerJugadores() {
        // Prueba obtener la lista de jugadores
        Jugador jugador = new Jugador(1, "987654321", "Equipo A", 1);
        control.agregarJugador(jugador); // Agrega un jugador
        List<Jugador> jugadores = control.obtenerJugadores(); // Obtiene la lista de jugadores
        assertEquals(1, jugadores.size()); // Verifica que se devuelve un jugador
        assertEquals(jugador, jugadores.get(0)); // Verifica que el jugador es el correcto
    }

    @Test
    public void testObtenerCapitan() {
        // Prueba obtener el capitán asignado
        Capitan capitan = equipo.getCapitan(); // Obtiene al capitán
        assertEquals(capitan, control.obtenerCapitan()); // Verifica que se obtiene el capitán correcto
    }

    @Test
    public void testObtenerCapitanFalloSinCapitan() {
        // Prueba obtener el capitán cuando no hay uno
        equipo.setCapitan(null); // Elimina al capitán
        assertNull(control.obtenerCapitan()); // Verifica que no hay capitán
    }
}




package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.Juego;
import edu.avanzada.taller2.vista.VentanasEmergentes;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de prueba para el controlador principal del juego.
 * Esta clase verifica el correcto funcionamiento de las acciones del controlador
 * y la interacción con las vistas.
 */
public class ControlPrincipalTest {

    protected ControlPrincipal controlPrincipal; // Controlador principal a probar
    protected CrearEquipo vistaCrearEquipo; // Vista de crear equipo mockeada
    protected Inicio vistaInicio; // Vista de inicio mockeada
    protected Juego vistaJuego; // Vista de juego mockeada
    protected VentanasEmergentes ventanaEmergente; // Ventana emergente mockeada

    /**
     * Configura el entorno de prueba antes de cada método de prueba.
     * Inicializa los mocks de las vistas y crea una instancia del controlador.
     * 
     * @throws IOException si ocurre un error durante la inicialización.
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Inicializa los mocks de las vistas
        vistaCrearEquipo = mock(CrearEquipo.class);
        vistaInicio = mock(Inicio.class);
        vistaJuego = mock(Juego.class);
        ventanaEmergente = mock(VentanasEmergentes.class);

        // Crea una instancia de ControlPrincipal y asigna las vistas mockeadas
        controlPrincipal = new ControlPrincipal() {
            {
                this.vistaCrearEquipo = vistaCrearEquipo;
                this.vistaInicio = vistaInicio;
                this.vistaJuego = vistaJuego;
                this.ventanaEmergente = ventanaEmergente;
            }
        };
    }

    /**
     * Limpia el entorno de prueba después de cada método de prueba.
     */
    @AfterEach
    public void tearDown() {
        // Restablece el control principal a null después de cada prueba
        controlPrincipal = null;
    }

    /**
     * Prueba el evento de acción para salir.
     * Verifica que el evento de salir no lanza excepciones.
     */
    @Test
    public void testActionPerformedSalir() {
        ActionEvent e = new ActionEvent(new Object(), 0, "Salir");
        assertDoesNotThrow(() -> controlPrincipal.actionPerformed(e));
    }

    /**
     * Prueba el evento de acción para volver a la vista de inicio.
     * Verifica que se cierren los campos de la vista de crear equipo
     * y que la vista de inicio sea visible.
     */
    @Test
    public void testActionPerformedVolver() {
        when(vistaCrearEquipo.isVisible()).thenReturn(true);
        ActionEvent e = new ActionEvent(new Object(), 0, "Volver");
        controlPrincipal.actionPerformed(e);
        
        verify(vistaCrearEquipo).dispose(); // Se debe cerrar la vista de crear equipo
        verify(vistaCrearEquipo).resetearCamposCrearEquipo(); // Se deben resetear los campos
        verify(vistaInicio).setVisible(true); // La vista de inicio debe ser visible
    }

    /**
     * Prueba el evento de acción para registrar un equipo.
     * Verifica que se cierre la vista de inicio y que se abra la vista de crear equipo.
     */
    @Test
    public void testActionPerformedRegistrarEquipo() {
        when(vistaInicio.isVisible()).thenReturn(true);
        ActionEvent e = new ActionEvent(new Object(), 0, "Registrar Equipo");
        controlPrincipal.actionPerformed(e);
        
        verify(vistaInicio).dispose(); // Se debe cerrar la vista de inicio
        verify(vistaCrearEquipo).setVisible(true); // La vista de crear equipo debe ser visible
    }

    /**
     * Prueba el evento de acción para registrar un equipo.
     * Verifica que se llame al método de crear equipo y se cierren las vistas correspondientes.
     */
    @Test
    public void testActionPerformedRegistrar() {
        ActionEvent e = new ActionEvent(new Object(), 0, "Registrar");
        controlPrincipal.actionPerformed(e);
        
        verify(controlPrincipal.controlEquipo).crearEquipo(); // Se debe llamar a crearEquipo
        verify(vistaCrearEquipo).dispose(); // Se debe cerrar la vista de crear equipo
        verify(vistaInicio).setVisible(true); // La vista de inicio debe ser visible
    }

    /**
     * Prueba el evento de acción para iniciar un juego.
     * Verifica que las vistas se configuren correctamente y que se inicie el partido.
     * 
     * @throws IOException si ocurre un error durante la inicialización.
     */
    @Test
    public void testActionPerformedJugar() throws IOException {
        // Prepara el entorno para el juego
        List<Jugador> jugadoresA = new ArrayList<>(); // Lista de jugadores para el equipo A
        List<Jugador> jugadoresB = new ArrayList<>(); // Lista de jugadores para el equipo B

        jugadoresA.add(mock(Jugador.class)); // Agrega un jugador mockeado
        jugadoresB.add(mock(Jugador.class)); // Agrega otro jugador mockeado

        Capitan capitanA = mock(Capitan.class);
        Capitan capitanB = mock(Capitan.class);

        when(controlPrincipal.controlEquipo.getEquipos()).thenReturn(new ArrayList<>()); // Asegúrate de tener al menos 2 equipos
        controlPrincipal.controlEquipo.getEquipos().add(new Equipo("Equipo A", "1", capitanA, jugadoresA)); // Agrega el equipo A
        controlPrincipal.controlEquipo.getEquipos().add(new Equipo("Equipo B", "2", capitanB, jugadoresB)); // Agrega el equipo B

        ActionEvent e = new ActionEvent(new Object(), 0, "Jugar");
        controlPrincipal.actionPerformed(e);
        
        verify(vistaJuego).setVisible(true); // La vista de juego debe ser visible
        verify(vistaInicio).dispose(); // Se debe cerrar la vista de inicio
        verify(controlPrincipal.controlPartido).iniciarPartido(); // Asegúrate de que controlPartido esté inicializado
    }

    /**
     * Prueba el método que obtiene la vista de crear equipo.
     * Verifica que el resultado sea el esperado.
     */
    @Test
    public void testGetCrearEquipo() {
        CrearEquipo result = controlPrincipal.getCrearEquipo();
        assertEquals(vistaCrearEquipo, result); // Verifica que el resultado sea el esperado
    }

    /**
     * Prueba el método que obtiene la ventana emergente.
     * Verifica que el resultado sea el esperado.
     */
    @Test
    public void testGetVentanaEmergente() {
        VentanasEmergentes result = controlPrincipal.getVentanaEmergente();
        assertEquals(ventanaEmergente, result); // Verifica que el resultado sea el esperado
    }

    /**
     * Prueba el método que obtiene la vista de inicio.
     * Verifica que el resultado sea el esperado.
     */
    @Test
    public void testGetVistaInicio() {
        Inicio result = controlPrincipal.getVistaInicio();
        assertEquals(vistaInicio, result); // Verifica que el resultado sea el esperado
    }

    /**
     * Prueba el método que obtiene la vista de juego.
     * Verifica que el resultado sea el esperado.
     */
    @Test
    public void testGetVistaJuego() {
        Juego result = controlPrincipal.getVistaJuego();
        assertEquals(vistaJuego, result); // Verifica que el resultado sea el esperado 
    }
}
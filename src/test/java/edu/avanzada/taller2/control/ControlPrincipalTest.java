package edu.avanzada.taller2.control;

import edu.avanzada.taller2.vista.CrearEquipo;
import edu.avanzada.taller2.vista.Inicio;
import edu.avanzada.taller2.vista.Juego;
import edu.avanzada.taller2.vista.VentanasEmergentes;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControlPrincipalTest {

    private ControlPrincipal controlPrincipal; // Controlador principal a probar
    private CrearEquipo vistaCrearEquipo; // Vista de crear equipo mockeada
    private Inicio vistaInicio; // Vista de inicio mockeada
    private Juego vistaJuego; // Vista de juego mockeada
    private VentanasEmergentes ventanaEmergente; // Ventana emergente mockeada

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

    @AfterEach
    public void tearDown() {
        // Restablece el control principal a null después de cada prueba
        controlPrincipal = null;
    }

    @Test
    public void testActionPerformedSalir() {
        // Prueba que el evento de salir no lanza excepciones
        ActionEvent e = new ActionEvent(new Object(), 0, "Salir");
        assertDoesNotThrow(() -> controlPrincipal.actionPerformed(e));
    }

    @Test
    public void testActionPerformedVolver() {
        // Prepara el mock para verificar la lógica al volver
        when(vistaCrearEquipo.isVisible()).thenReturn(true);
        ActionEvent e = new ActionEvent(new Object(), 0, "Volver");
        controlPrincipal.actionPerformed(e);
        
        // Verifica que se llamen los métodos esperados en las vistas
        verify(vistaCrearEquipo).dispose(); // Se debe cerrar la vista de crear equipo
        verify(vistaCrearEquipo).resetearCamposCrearEquipo(); // Se deben resetear los campos
        verify(vistaInicio).setVisible(true); // La vista de inicio debe ser visible
    }

    @Test
    public void testActionPerformedRegistrarEquipo() {
        // Prepara el mock para verificar la lógica al registrar un equipo
        when(vistaInicio.isVisible()).thenReturn(true);
        ActionEvent e = new ActionEvent(new Object(), 0, "Registrar Equipo");
        controlPrincipal.actionPerformed(e);
        
        // Verifica que se llamen los métodos esperados en las vistas
        verify(vistaInicio).dispose(); // Se debe cerrar la vista de inicio
        verify(vistaCrearEquipo).setVisible(true); // La vista de crear equipo debe ser visible
    }

    @Test
    public void testActionPerformedRegistrar() {
        // Prueba el evento de registrar un equipo
        ActionEvent e = new ActionEvent(new Object(), 0, "Registrar");
        controlPrincipal.actionPerformed(e);
        
        // Verifica que se llame al método de crear equipo y a la vista
        verify(controlPrincipal.controlEquipo).crearEquipo(); // Se debe llamar a crearEquipo
        verify(vistaCrearEquipo).dispose(); // Se debe cerrar la vista de crear equipo
        verify(vistaInicio).setVisible(true); // La vista de inicio debe ser visible
    }

    @Test
    public void testActionPerformedJugar() throws IOException {
        // Prueba el evento de iniciar un juego
        ActionEvent e = new ActionEvent(new Object(), 0, "Jugar");
        controlPrincipal.actionPerformed(e);
        
        // Verifica que se llame a los métodos esperados
        verify(vistaJuego).setVisible(true); // La vista de juego debe ser visible
        verify(vistaInicio).dispose(); // Se debe cerrar la vista de inicio
        verify(controlPrincipal.partido).iniciarPartido(); // Se debe iniciar el partido
    }

    @Test
    public void testGetCrearEquipo() {
        // Prueba el método que obtiene la vista de crear equipo
        CrearEquipo result = controlPrincipal.getCrearEquipo();
        assertEquals(vistaCrearEquipo, result); // Verifica que el resultado sea el esperado
    }

    @Test
    public void testGetVentanaEmergente() {
        // Prueba el método que obtiene la ventana emergente
        VentanasEmergentes result = controlPrincipal.getVentanaEmergente();
        assertEquals(ventanaEmergente, result); // Verifica que el resultado sea el esperado
    }

    @Test
    public void testGetVistaInicio() {
        // Prueba el método que obtiene la vista de inicio
        Inicio result = controlPrincipal.getVistaInicio();
        assertEquals(vistaInicio, result); // Verifica que el resultado sea el esperado
    }

    @Test
    public void testGetVistaJuego() {
        // Prueba el método que obtiene la vista de juego
        Juego result = controlPrincipal.getVistaJuego();
        assertEquals(vistaJuego, result); // Verifica que el resultado sea el esperado
    }
}
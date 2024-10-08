package edu.avanzada.taller2.control;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para ControlEquipo.
 * Esta clase contiene pruebas unitarias para verificar el comportamiento de 
 * la clase ControlEquipo al crear equipos con diferentes condiciones.
 */
public class ControlEquipoTest {

    protected ControlPrincipal control;
    protected ControlEquipo controlEquipo;

    /**
     * Configuración inicial antes de cada prueba.
     * Se crea una instancia de ControlPrincipal y ControlEquipo.
     *
     * @throws IOException si ocurre un error al inicializar ControlPrincipal.
     */
    @BeforeEach
    public void setUp() throws IOException {
        control = new ControlPrincipal();
        controlEquipo = new ControlEquipo(control);
    }

    /**
     * Prueba la creación de un equipo con datos válidos.
     * Se simulan datos válidos para el capitán y cinco jugadores,
     * y se llama al método crearEquipo para verificar su correcto funcionamiento.
     */
    @Test
    public void testCrearEquipoConDatosValidos() {
        // Simular el ingreso de datos válidos
        control.getCrearEquipo().cargarNombreEquipo().setText("Equipo 1");
        control.getCrearEquipo().cargarNombre().setText("Capitán 1");
        control.getCrearEquipo().cargarCedula().setText("123456");
        control.getCrearEquipo().cargarEdad().setText("30");
        control.getCrearEquipo().cargarAñosExp().setText("5");

        // Crear jugadores directamente
        control.getCrearEquipo().cargarNombre1().setText("Jugador 1");
        control.getCrearEquipo().cargarCedula1().setText("654321");
        control.getCrearEquipo().cargarEdad1().setText("25");

        control.getCrearEquipo().cargarNombre2().setText("Jugador 2");
        control.getCrearEquipo().cargarCedula2().setText("654322");
        control.getCrearEquipo().cargarEdad2().setText("26");

        control.getCrearEquipo().cargarNombre3().setText("Jugador 3");
        control.getCrearEquipo().cargarCedula3().setText("654323");
        control.getCrearEquipo().cargarEdad3().setText("27");

        control.getCrearEquipo().cargarNombre4().setText("Jugador 4");
        control.getCrearEquipo().cargarCedula4().setText("654324");
        control.getCrearEquipo().cargarEdad4().setText("28");

        control.getCrearEquipo().cargarNombre5().setText("Jugador 5");
        control.getCrearEquipo().cargarCedula5().setText("654325");
        control.getCrearEquipo().cargarEdad5().setText("29");

        // Llamar al método para crear el equipo
        controlEquipo.crearEquipo();

        // Aquí debes validar que se ha creado el equipo correctamente.
        // Como no tienes un método para obtener los equipos, aquí podrías
        // verificar algún efecto secundario en ControlPrincipal.
    }

    /**
     * Prueba la creación de un equipo con menos de cinco jugadores.
     * Intenta crear un equipo con solo un jugador y verifica que se 
     * maneje el error adecuadamente.
     */
    @Test
    public void testCrearEquipoConMenosDeCincoJugadores() {
        // Intentar crear equipo con solo un jugador
        control.getCrearEquipo().cargarNombreEquipo().setText("Equipo 2");
        control.getCrearEquipo().cargarNombre().setText("Capitán 2");
        control.getCrearEquipo().cargarCedula().setText("123457");
        control.getCrearEquipo().cargarEdad().setText("31");
        control.getCrearEquipo().cargarAñosExp().setText("6");

        // Solo se agrega un jugador
        control.getCrearEquipo().cargarNombre1().setText("Jugador 1");
        control.getCrearEquipo().cargarCedula1().setText("654321");
        control.getCrearEquipo().cargarEdad1().setText("25");

        // Llamar al método para crear el equipo
        controlEquipo.crearEquipo();

        // Aquí debes validar que se ha mostrado un mensaje de error.
        // Como no tienes acceso a la ventana, puedes verificar si se
        // manejó el error adecuadamente en ControlPrincipal.
    }

    /**
     * Prueba la creación de un equipo con campos vacíos.
     * Se intenta crear un equipo sin llenar los campos requeridos
     * y se verifica el comportamiento esperado.
     */
    @Test
    public void testCrearEquipoConCamposVacios() {
        // Intentar crear un equipo sin llenar los campos
        control.getCrearEquipo().cargarNombreEquipo().setText("");
        control.getCrearEquipo().cargarNombre().setText("");
        control.getCrearEquipo().cargarCedula().setText("");
        control.getCrearEquipo().cargarEdad().setText("");
        control.getCrearEquipo().cargarAñosExp().setText("");

        // Llamar al método para crear el equipo
        controlEquipo.crearEquipo();
    }
}

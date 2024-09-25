package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * ControlPartido es la clase que gestiona el control del partido,
 * incluyendo el manejo de los equipos, el puntaje, y la lógica del juego.
 */
public class ControlPartido {
    protected Equipo equipoA;
    protected Equipo equipoB;
    protected int puntajeA;
    protected int puntajeB;
    protected boolean partidoEnCurso;
    protected Random random;
    protected int[] huecos;
    protected Juez juez; // Juez es ahora una instancia de la clase Juez
    protected int puntajeMaximo;
    protected ArrayList<Equipo> equipos;
    protected String nombreA;
    protected String nombreB;
    protected int turno;
    protected int jugadorTurno;
    protected int turnoActual = 0;
    protected int lanzamientosRestantes = 5;
    protected int puntajeAcumulado = 0;

    protected ControlProperties properties;
    protected ControlPrincipal control;
    protected ControlEquipo controlEquipo;

    /**
     * Constructor de ControlPartido.
     * 
     * @param equipoA El primer equipo que participa en el partido.
     * @param equipoB El segundo equipo que participa en el partido.
     * @param control Control principal que maneja la interfaz.
     * @throws IOException Si ocurre un error al cargar el juez.
     */
    public ControlPartido(Equipo equipoA, Equipo equipoB, ControlPrincipal control) throws IOException {
        if (equipoA == null || equipoB == null) {
            control.getVentanaEmergente().ventanaError("¡No se puede iniciar el juego sin ambos equipos!");
            throw new IllegalArgumentException("Equipo A y equipo B no pueden ser nulos.");
        }

        this.properties = new ControlProperties();
        this.control = control;
        this.controlEquipo = new ControlEquipo(control);

        cargarJuez(); // Cargar el juez desde las propiedades

        this.equipos = controlEquipo.getEquipos();
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.nombreA = equipoA.getNombreEquipo();
        this.nombreB = equipoB.getNombreEquipo();
        this.puntajeA = 0;
        this.puntajeB = 0;
        this.partidoEnCurso = false;
        this.random = new Random();
        this.huecos = new int[10];
        this.puntajeMaximo = properties.obtenerPuntajeMaximo("puntuacion.maxima", "0");
        this.turno = 1;
        this.jugadorTurno = 1;

        cargarValores();
    }

    /**
     * Carga los datos del juez desde las propiedades.
     * 
     * @throws IOException Si ocurre un error al cargar los datos del juez.
     */
    private void cargarJuez() throws IOException {
        String nombre = properties.obtenerNombreJuez();
        String cedula = properties.obtenerCedulaJuez();
        String edad = properties.obtenerEdadJuez();
        String numeroTarjeta = properties.obtenerNumeroTarjetaJuez();

        // Crear el objeto juez con los valores obtenidos
        this.juez = new Juez(numeroTarjeta, cedula, nombre, edad);
    }

    /**
     * Carga los valores de los huecos desde las propiedades.
     * 
     * @throws IOException Si ocurre un error al cargar los valores de los huecos.
     */
    private void cargarValores() throws IOException {
        for (int i = 0; i < huecos.length; i++) {
            String clave = "orificio.hueco" + (i + 1);
            huecos[i] = properties.obtenerValorHueco(clave, "0");
        }
    }

    /**
     * Inicia el partido y muestra la interfaz correspondiente.
     * 
     * @throws IOException Si ocurre un error al iniciar el partido.
     */
    public void iniciarPartido() throws IOException {
        if (partidoEnCurso) {
            throw new IllegalStateException("El partido ya está en curso.");
        }

        control.getVistaInicio().dispose();
        control.getVistaJuego().setVisible(true);
        partidoEnCurso = true;
        reiniciarPartido();
        control.getVentanaEmergente().ventanaPlana("¡Ha comenzado el partido entre " + nombreA + " y " + nombreB + "!");
    }

    /**
     * Reinicia el puntaje de ambos equipos.
     */
    public void reiniciarPartido() {
        puntajeA = 0;
        puntajeB = 0;
    }

    /**
     * Maneja la lógica de un turno de juego.
     * 
     * @throws IllegalStateException Si el partido no ha comenzado.
     */
    public void jugarTurno() throws IllegalStateException {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no ha comenzado aún.");
        }

        // Comprobar si hay jugadores en el equipo A
        if (turnoActual < equipoA.getJugadores().size() || turnoActual < equipoB.getJugadores().size()) {
            if (turnoActual % 2 == 0 && turnoActual / 2 < equipoA.getJugadores().size()) {
                Jugador jugadorA = equipoA.getJugadores().get(turnoActual / 2);
                if (jugadorDetrasDeLineaDeJuego()) {
                    realizarLanzamiento(jugadorA, equipoA);
                    lanzamientosRestantes--;

                    // Verificar si el equipo A ha alcanzado 5000 puntos
                    if (puntajeA >= 5000) {
                        finalizarPartido(); // Terminar el partido si el equipo A gana
                        return; // Salir del método
                    }

                    // Si quedan lanzamientos, sigue en el turno actual
                    if (lanzamientosRestantes > 0) {
                        return; // Esperar al siguiente lanzamiento
                    }
                } else {
                    control.getVentanaEmergente().ventanaError("El jugador " + jugadorA.getNombre() + " no está detrás de la línea de juego.");
                }
            } else if (turnoActual % 2 == 1 && turnoActual / 2 < equipoB.getJugadores().size()) {
                Jugador jugadorB = equipoB.getJugadores().get(turnoActual / 2);
                if (jugadorDetrasDeLineaDeJuego()) {
                    realizarLanzamiento(jugadorB, equipoB);
                    lanzamientosRestantes--;

                    // Verificar si el equipo B ha alcanzado 5000 puntos
                    if (puntajeB >= 5000) {
                        finalizarPartido(); // Terminar el partido si el equipo B gana
                        return; // Salir del método
                    }

                    // Si quedan lanzamientos, sigue en el turno actual
                    if (lanzamientosRestantes > 0) {
                        return; // Esperar al siguiente lanzamiento
                    }
                } else {
                    control.getVentanaEmergente().ventanaError("El jugador " + jugadorB.getNombre() + " no está detrás de la línea de juego.");
                }
            }

            // Al terminar los 5 lanzamientos de un jugador
            if (lanzamientosRestantes == 0) {
                // Mostrar el puntaje acumulado del jugador
                String mensajeFinal = "El jugador " + (turnoActual % 2 == 0 ? equipoA.getJugadores().get(turnoActual / 2).getNombre() : equipoB.getJugadores().get(turnoActual / 2).getNombre()) + 
                                       " ha terminado sus lanzamientos. Acumulado: " + puntajeAcumulado + " puntos. \n" +
                                       "Presione 'Lanzar' para continuar con el siguiente jugador.";

                // Mostrar el mensaje final
                control.getVentanaEmergente().ventanaPlana(mensajeFinal);

                // Preparar para el siguiente jugador
                turnoActual++;
                lanzamientosRestantes = 5; // Reiniciar lanzamientos restantes
                puntajeAcumulado = 0; // Reiniciar puntaje acumulado para el siguiente jugador
            }
        } else {
            // Si no hay más jugadores, finalizar el partido
            finalizarPartido();
        }
    }

    /**
     * Realiza un lanzamiento para un jugador específico.
     * 
     * @param jugador El jugador que realiza el lanzamiento.
     * @param equipo El equipo al que pertenece el jugador.
     */
    private void realizarLanzamiento(Jugador jugador, Equipo equipo) {
        // Seleccionar un hueco al azar y obtener su valor
        int huecoSeleccionado = random.nextInt(10); // Random entre 0 y 9
        int puntajeObtenido = huecos[huecoSeleccionado]; // Valor del hueco seleccionado

        // Acumular el puntaje del turno
        puntajeAcumulado += puntajeObtenido; // Acumular el puntaje en el puntaje acumulado

        // Mostrar solo el puntaje del lanzamiento actual
        control.getVentanaEmergente().ventanaPlana("El jugador " + jugador.getNombre() + " del equipo " + equipo.getNombreEquipo() +
                " anotó " + puntajeObtenido + " puntos en este lanzamiento.");
        
        // Actualizar el puntaje total del equipo correspondiente
        if (equipo.equals(equipoA)) {
            puntajeA += puntajeObtenido;
        } else {
            puntajeB += puntajeObtenido;
        }

        // Mostrar puntaje acumulado
        mostrarPuntajeAcumulado();
    }

    /**
     * Verifica si el jugador está detrás de la línea de juego.
     * 
     * @return true si el jugador está detrás de la línea de juego; false en caso contrario.
     */
    private boolean jugadorDetrasDeLineaDeJuego() {
        return control.getVistaJuego().getjRadioButton1().isSelected();
    }

    /**
     * Indica el turno del jugador actual.
     * 
     * @param jugador El índice del jugador cuyo turno se está indicando.
     */
    public void turnoJugador(int jugador) {
    // Verificar el turno actual para determinar de qué equipo es el jugador
    switch (turno) {
        case 1:
            // Verificar si el equipo A tiene jugadores
            if (equipoA.getJugadores() == null || equipoA.getJugadores().isEmpty()) {
                control.getVentanaEmergente().ventanaError("El equipo A no tiene jugadores.");
                return; // Salir si el equipo A no tiene jugadores
            }

            // Validar que el índice del jugador sea válido para el equipo A
            if (jugador < 1 || jugador > equipoA.getJugadores().size()) {
                control.getVentanaEmergente().ventanaError("El índice del jugador es inválido para el equipo A.");
                return; // Salir si el índice es inválido
            }

            // Obtener el jugador del equipo A
            Jugador jugadorA = equipoA.getJugadores().get(jugador - 1);
            String nombreJugadorA = jugadorA.getNombre(); // Obtener el nombre del jugador

            // Verificar si el nombre del jugador A es válido
            if (nombreJugadorA == null || nombreJugadorA.isEmpty()) {
                control.getVentanaEmergente().ventanaError("El nombre del jugador A es vacío.");
            } else {
                // Mostrar el turno del jugador A
                control.getVentanaEmergente().ventanaPlana("Es el turno del jugador " + nombreJugadorA + " del equipo " + nombreA);
            }
            break;

        case 2:
            // Verificar si el equipo B tiene jugadores
            if (equipoB.getJugadores() == null || equipoB.getJugadores().isEmpty()) {
                control.getVentanaEmergente().ventanaError("El equipo B no tiene jugadores.");
                return; // Salir si el equipo B no tiene jugadores
            }

            // Validar que el índice del jugador sea válido para el equipo B
            if (jugador < 1 || jugador > equipoB.getJugadores().size()) {
                control.getVentanaEmergente().ventanaError("El índice del jugador es inválido para el equipo B.");
                return; // Salir si el índice es inválido
            }

            // Obtener el jugador del equipo B
            Jugador jugadorB = equipoB.getJugadores().get(jugador - 1);
            String nombreJugadorB = jugadorB.getNombre(); // Obtener el nombre del jugador

            // Verificar si el nombre del jugador B es válido
            if (nombreJugadorB == null || nombreJugadorB.isEmpty()) {
                control.getVentanaEmergente().ventanaError("El nombre del jugador B es vacío.");
            } else {
                // Mostrar el turno del jugador B
                control.getVentanaEmergente().ventanaPlana("Es el turno del jugador " + nombreJugadorB + " del equipo " + nombreB);
            }
            break;

        default:
            control.getVentanaEmergente().ventanaError("Turno no válido.");
            break; // Salir si el turno no es válido
}
}

    /**
     * Simula el puntaje para el jugador actual, permitiendo obtener un puntaje aleatorio.
     * 
     * @throws IllegalStateException Si el partido no ha comenzado.
     * @throws IllegalArgumentException Si el jugador no es válido.
     */
    public void simularPuntaje() throws IllegalStateException, IllegalArgumentException {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no ha comenzado.");
        }

        if (turnoActual >= equipoA.getJugadores().size() + equipoB.getJugadores().size()) {
            throw new IllegalArgumentException("El jugador no es válido.");
        }

        // Simular puntaje aleatorio para el jugador actual
        int puntajeSimulado = random.nextInt(100); // Ejemplo de puntaje aleatorio
        puntajeAcumulado += puntajeSimulado;

        // Mostrar el puntaje acumulado
        mostrarPuntajeAcumulado();
    }

    /**
     * Muestra el puntaje acumulado de ambos equipos.
     */
    private void mostrarPuntajeAcumulado() {
        String mensaje = "Puntaje acumulado: \n" +
                         nombreA + ": " + puntajeA + "\n" +
                         nombreB + ": " + puntajeB;
        control.getVentanaEmergente().ventanaPlana(mensaje); // Mostrar puntaje en la ventana emergente
    }

    /**
     * Finaliza el partido y muestra el equipo ganador.
     * 
     * @throws IllegalStateException Si el partido no está en curso.
     */
    public void finalizarPartido() {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no está en curso.");
        }

        partidoEnCurso = false; // Marcar partido como no en curso
        mostrarEquipoGanador(); // Mostrar equipo ganador
    }

    /**
     * Muestra el equipo ganador al finalizar el partido.
     */
    private void mostrarEquipoGanador() {
        String ganador = (puntajeA > puntajeB) ? nombreA : nombreB;
        control.getVentanaEmergente().ventanaPlana("El equipo ganador es " + ganador + " con un puntaje de " + Math.max(puntajeA, puntajeB) + " puntos!");
    }

    // Getters y Setters

    /**
     * Obtiene el equipo A.
     * 
     * @return El equipo A.
     */
    public Equipo getEquipoA() {
        return equipoA;
    }

    /**
     * Establece el equipo A.
     * 
     * @param equipoA El nuevo equipo A.
     * @throws IllegalArgumentException Si el equipo A es nulo.
     */
    public void setEquipoA(Equipo equipoA) {
        if (equipoA == null) {
            throw new IllegalArgumentException("El equipo A no puede ser nulo.");
        }
        this.equipoA = equipoA;
    }

    /**
     * Obtiene el equipo B.
     * 
     * @return El equipo B.
     */
    public Equipo getEquipoB() {
        return equipoB;
    }

    /**
     * Establece el equipo B.
     * 
     * @param equipoB El nuevo equipo B.
     * @throws IllegalArgumentException Si el equipo B es nulo.
     */
    public void setEquipoB(Equipo equipoB) {
        if (equipoB == null) {
            throw new IllegalArgumentException("El equipo B no puede ser nulo.");
        }
        this.equipoB = equipoB;
    }

    /**
     * Obtiene el juez del partido.
     * 
     * @return El juez.
     */
    public Juez getJuez() {
        return juez;
    }

    /**
     * Establece el juez del partido.
     * 
     * @param juez El nuevo juez.
     * @throws IllegalArgumentException Si el juez es nulo.
     */
    public void setJuez(Juez juez) {
        if (juez == null) {
            throw new IllegalArgumentException("El juez no puede ser nulo.");
        }
        this.juez = juez;
    }

    /**
     * Obtiene el puntaje del equipo A.
     * 
     * @return El puntaje del equipo A.
     */
    public int getPuntajeA() {
        return puntajeA;
    }

    /**
     * Obtiene el puntaje del equipo B.
     * 
     * @return El puntaje del equipo B.
     */
    public int getPuntajeB() {
        return puntajeB;
    }

    /**
     * Verifica si el partido está en curso.
     * 
     * @return true si el partido está en curso; false en caso contrario.
     */
    public boolean isPartidoEnCurso() {
        return partidoEnCurso;
    }

    /**
     * Obtiene el puntaje máximo permitido.
     * 
     * @return El puntaje máximo.
     */
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
}
package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ControlPartido {
    private Equipo equipoA;
    private Equipo equipoB;
    private int puntajeA;
    private int puntajeB;
    private boolean partidoEnCurso;
    private Random random;
    private int[] huecos;
    private Juez juez; // Juez es ahora una instancia de la clase Juez
    private int puntajeMaximo;
    private ArrayList<Equipo> equipos;
    private String nombreA;
    private String nombreB;
    private int turno;
    private int jugadorTurno;
    private int turnoActual = 0;
    private int lanzamientosRestantes = 5;
    private int puntajeAcumulado = 0;

    private ControlProperties properties;
    private ControlPrincipal control;
    private ControlEquipo controlEquipo;

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

    private void cargarJuez() throws IOException {
        String nombre = properties.obtenerNombreJuez();
        String cedula = properties.obtenerCedulaJuez();
        String edad = properties.obtenerEdadJuez();
        String numeroTarjeta = properties.obtenerNumeroTarjetaJuez();

        // Crear el objeto juez con los valores obtenidos
        this.juez = new Juez(numeroTarjeta, cedula, nombre, edad);
    }

    private void cargarValores() throws IOException {
        for (int i = 0; i < huecos.length; i++) {
            String clave = "orificio.hueco" + (i + 1);
            huecos[i] = properties.obtenerValorHueco(clave, "0");
        }
    }

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

    public void reiniciarPartido() {
        puntajeA = 0;
        puntajeB = 0;
    }

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

private void realizarLanzamiento(Jugador jugador, Equipo equipo) {
    // Seleccionar un hueco al azar y obtener su valor
    int huecoSeleccionado = random.nextInt(10); // Random entre 0 y 9
    int puntajeObtenido = huecos[huecoSeleccionado]; // Valor del hueco seleccionado

    // Acumular el puntaje del turno
    puntajeAcumulado += puntajeObtenido; // Acumular el puntaje en el puntaje acumulado

    // Mostrar solo el puntaje del lanzamiento actual
    control.getVentanaEmergente().ventanaPlana("El jugador " + jugador.getNombre() + " del equipo " + equipo.getNombreEquipo() +
            " anotó " + puntajeObtenido + " puntos en este lanzamiento. Puntaje acumulado: " + puntajeAcumulado + " puntos.");
}

    private boolean jugadorDetrasDeLineaDeJuego() {
        return control.getVistaJuego().getjRadioButton1().isSelected();
    }

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
            break; // Salir si el turno no es válido
    }
}


    public void simularPuntaje() throws IllegalStateException, IllegalArgumentException {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no ha comenzado aún.");
        }

        // Seleccionar un hueco al azar y obtener su valor
        int huecoSeleccionado = random.nextInt(10); // Random entre 0 y 9
        int puntajeObtenido = huecos[huecoSeleccionado]; // Valor del hueco seleccionado

        switch (turno) {
            case 1:
                puntajeA += puntajeObtenido;
                control.getVentanaEmergente().ventanaPlana("El jugador " + equipoA.getJugadores().get(jugadorTurno - 1).getNombre() +
                        " del equipo " + nombreA + " ha anotado: " + puntajeObtenido + " puntos!");
                break;
            case 2:
                puntajeB += puntajeObtenido;
                control.getVentanaEmergente().ventanaPlana("El jugador " + equipoB.getJugadores().get(jugadorTurno - 1).getNombre() +
                        " del equipo " + nombreB + " ha anotado: " + puntajeObtenido + " puntos!");
                break;
        }

        // Verificar si alguno de los equipos ha alcanzado el puntaje máximo
        if (puntajeA >= puntajeMaximo || puntajeB >= puntajeMaximo) {
            finalizarPartido();
        }
    }
    
    private void mostrarPuntajeAcumulado() {
        control.getVentanaEmergente().ventanaPlana("Puntaje acumulado: \n" +
                nombreA + ": " + puntajeA + "\n" +
                nombreB + ": " + puntajeB);
    }

    public void finalizarPartido() {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no está en curso.");
        }

        partidoEnCurso = false;
        mostrarEquipoGanador();
        control.getVentanaEmergente().ventanaPlana("¡El partido ha llegado a su fin!");
        control.getVistaJuego().dispose();
        control.getVistaInicio().setVisible(true);
    }
    
    private void mostrarEquipoGanador() {
    String ganador;
    Equipo equipoGanador;

    // Determinar el equipo ganador
    if (puntajeA > puntajeB) {
        equipoGanador = equipoA;
        ganador = "¡El equipo " + equipoA.getNombreEquipo() + " ha ganado con " + puntajeA + " puntos!";
    } else if (puntajeB > puntajeA) {
        equipoGanador = equipoB;
        ganador = "¡El equipo " + equipoB.getNombreEquipo() + " ha ganado con " + puntajeB + " puntos!";
    } else {
        ganador = "¡El partido terminó en empate con " + puntajeA + " puntos cada uno!";
        control.getVentanaEmergente().ventanaPlana(ganador);
        return; // Salir si hay empate
    }

    // Mostrar datos del equipo ganador
    StringBuilder datosEquipo = new StringBuilder();
    datosEquipo.append(ganador).append("\n");
    datosEquipo.append("Datos del equipo:\n");
    datosEquipo.append("Nombre del equipo: ").append(equipoGanador.getNombreEquipo()).append("\n");

    // Mostrar datos del capitán
    Capitan capitan = equipoGanador.getCapitan();
    if (capitan != null) {
        datosEquipo.append("Capitan del equipo:\n");
        datosEquipo.append("Nombre: ").append(capitan.getNombre())
                .append(", Edad: ").append(capitan.getEdad())
                .append(", Cedula: ").append(capitan.getCedula()).append("\n");
    } else {
        datosEquipo.append("No hay capitán disponible.\n");
    }

    // Mostrar datos de los jugadores
    for (Jugador jugador : equipoGanador.getJugadores()) {
        datosEquipo.append("Jugador: ").append(jugador.getNombre()) // Muestra el nombre del jugador
                   .append(", Edad: ").append(jugador.getEdad())
                   .append(", Cedula: ").append(jugador.getCedula()).append("\n");
    }

    // Mostrar datos del juez
    datosEquipo.append("Datos del juez:\n");
    datosEquipo.append("Nombre: ").append(juez.getNombre())
               .append(", Cédula: ").append(juez.getCedula())
               .append(", Edad: ").append(juez.getEdad())
               .append(", Número de tarjeta: ").append(juez.getNumTarjetaProf()).append("\n");

    // Mostrar toda la información en la ventana emergente
    control.getVentanaEmergente().ventanaPlana(datosEquipo.toString());
}

    // Getters y Setters

    public Equipo getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        if (equipoA == null) {
            throw new IllegalArgumentException("El equipo A no puede ser nulo.");
        }
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        if (equipoB == null) {
            throw new IllegalArgumentException("El equipo B no puede ser nulo.");
        }
        this.equipoB = equipoB;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setJuez(Juez juez) {
        if (juez == null) {
            throw new IllegalArgumentException("El juez no puede ser nulo.");
        }
        this.juez = juez;
    }

    public int getPuntajeA() {
        return puntajeA;
    }

    public int getPuntajeB() {
        return puntajeB;
    }

    public boolean isPartidoEnCurso() {
        return partidoEnCurso;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
}
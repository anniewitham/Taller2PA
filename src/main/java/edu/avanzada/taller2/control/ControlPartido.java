package edu.avanzada.taller2.control;

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

    public void jugarTurno() {
        // Verificar si el partido ha comenzado
        if (!partidoEnCurso) {
            control.getVentanaEmergente().ventanaError("El partido no ha comenzado aún. Inicie el partido antes de jugar.");
            return; // Salir si el partido no ha comenzado
        }

        // Verificar si el jugador está detrás de la línea de juego
        if (!jugadorDetrasDeLineaDeJuego()) {
            control.getVentanaEmergente().ventanaError("El jugador debe estar detrás de la línea de juego para lanzar.");
            return; // Salir si no está detrás de la línea
        }

        // Verificar si el número de jugador es válido
        if (jugadorTurno < 1 || jugadorTurno > 5) {
            control.getVentanaEmergente().ventanaError("Número de jugador no válido: " + jugadorTurno);
            return;
        }

        turnoJugador(jugadorTurno);
        if (jugadorTurno < 5){
            jugadorTurno++;
        } else {
            jugadorTurno = 1;
        }
        simularPuntaje();
    }

    private boolean jugadorDetrasDeLineaDeJuego() {
        return control.getVistaJuego().getjRadioButton1().isSelected();
    }

    public void turnoJugador(int jugador) {
        switch (turno) {
            case 1:
                // Verificar si el equipo A tiene jugadores
                if (equipoA.getJugadores() == null || equipoA.getJugadores().isEmpty()) {
                    control.getVentanaEmergente().ventanaError("El equipo A no tiene jugadores.");
                    return;
                }

                // Validar que el índice del jugador sea válido para el equipo A
                if (jugador < 1 || jugador > equipoA.getJugadores().size()) {
                    control.getVentanaEmergente().ventanaError("El índice del jugador es inválido para el equipo A.");
                    return;
                }

                // Obtener el nombre del jugador del equipo A
                control.getVentanaEmergente().ventanaPlana("Es el turno del jugador " + equipoA.getJugadores().get(jugador - 1).getNombre() + " del equipo " + nombreA);
                break;

            case 2:
                // Verificar si el equipo B tiene jugadores
                if (equipoB.getJugadores() == null || equipoB.getJugadores().isEmpty()) {
                    control.getVentanaEmergente().ventanaError("El equipo B no tiene jugadores.");
                    return;
                }

                // Validar que el índice del jugador sea válido para el equipo B
                if (jugador < 1 || jugador > equipoB.getJugadores().size()) {
                    control.getVentanaEmergente().ventanaError("El índice del jugador es inválido para el equipo B.");
                    return;
                }

                // Obtener el nombre del jugador del equipo B
                control.getVentanaEmergente().ventanaPlana("Es el turno del jugador " + equipoB.getJugadores().get(jugador - 1).getNombre() + " del equipo " + nombreB);
                break;

            default:
                control.getVentanaEmergente().ventanaError("Turno no válido.");
                break;
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
                control.getVentanaEmergente().ventanaPlana("El equipo " + nombreA + " ha anotado: " + puntajeObtenido + " puntos y con esto suma "+puntajeA+"!");
                turno = 2; // Cambiar el turno
                break;
            case 2:
                puntajeB += puntajeObtenido;
                control.getVentanaEmergente().ventanaPlana("El equipo " + nombreB + " ha anotado: " + puntajeObtenido + " puntos y con esto suma "+puntajeB+"!");
                turno = 1; // Cambiar el turno
                break;
        }

        // Verificar si alguno de los equipos ha alcanzado el puntaje máximo
        if (puntajeA >= puntajeMaximo || puntajeB >= puntajeMaximo) {
            finalizarPartido();
        }
    }

    public void finalizarPartido() {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no está en curso.");
        }

        partidoEnCurso = false;
        mostrarEquipoGanador();
        control.getVentanaEmergente().ventanaPlana("¡El partido ha llegado a su fin!");
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
    
        // Mostrar datos de los jugadores
        for (Jugador jugador : equipoGanador.getJugadores()) {
            datosEquipo.append("Jugador: ").append(jugador.getNombre())
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
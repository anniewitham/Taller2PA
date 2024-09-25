package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
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
    private int[] huecos; // Atributos para los huecos del tablero
    private Juez juez;
    private int puntajeMaximo;
    private ArrayList<Equipo> equipos;
    private String nombreA;
    private String nombreB;
    
    private ControlProperties properties;
    private ControlPrincipal control;
    private ControlEquipo controlEquipo;

    /**
     * Constructor que inicializa el partido con dos equipos y un juez.
     *
     * @param equipoA El equipo A.
     * @param equipoB El equipo B.
     * @param juez El juez asignado al partido.
     * @param puntajeMaximo El puntaje máximo para ganar el partido.
     * @throws IllegalArgumentException si alguno de los parámetros es nulo.
     */
    public ControlPartido() throws IOException {
        
        this.properties = new ControlProperties();
        this.control = new ControlPrincipal();
        this.controlEquipo = new ControlEquipo();
        
        this.equipos = controlEquipo.getEquipos();
        this.equipoA = equipos.get(0);
        this.equipoB = equipos.get(1);
        this.nombreA = equipoA.getNombreEquipo();
        this.nombreB = equipoB.getNombreEquipo();
        this.puntajeA = 0;
        this.puntajeB = 0;
        this.partidoEnCurso = false;
        this.random = new Random();
        this.huecos = new int[10];
        this.puntajeMaximo = properties.obtenerPuntajeMaximo("puntuacion.maxima", "0");
        
        cargarValores();
    }

    /**
     * Método para cargar los valores de los huecos desde un archivo properties.
     */
    private void cargarValores() throws IOException {
        for(int i=0;i<huecos.length;i++){
            String clave = "orificio.hueco" + (i + 1);
            huecos[i] = properties.obtenerValorHueco(clave, "0");
        }
    }

    /**
     * Inicia la simulación del partido.
     */
    public void iniciarPartido() throws IOException {
        if (partidoEnCurso) {
            throw new IllegalStateException("El partido ya está en curso.");
        }
        if (equipoA == null || equipoB == null || juez == null) {
            throw new IllegalArgumentException("Equipo A, equipo B y juez no pueden ser nulos.");
        }
        
        control.getVistaInicio().dispose();
        control.getVistaJuego().setVisible(true);
        partidoEnCurso = true;
        reiniciarPartido();
        control.getVentanaEmergente().ventanaPlana("¡Ha comenzado el partido entre "+nombreA+" y "+nombreB+" ha comenzado!");
    }

    /**
     * Simula la asignación de puntajes para el equipo que está lanzando.
     *
     * @param equipoQueLanza El equipo que está lanzando (equipo A o equipo B).
     */
    public void simularPuntaje(Equipo equipoQueLanza) throws IllegalStateException, IllegalArgumentException {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no ha comenzado aún.");
        }

        // Seleccionar un hueco al azar y obtener su valor
        int huecoSeleccionado = (random.nextInt(10))-1; // Random entre 0 y 9
        int puntajeObtenido = huecos[huecoSeleccionado]; // Valor del hueco seleccionado

        // Sumamos el puntaje al equipo que lanza
        if (equipoQueLanza.equals(equipoA)) {
            puntajeA += puntajeObtenido;
            control.getVentanaEmergente().ventanaPlana("Equipo A (" + nombreA + ") ha anotado: " + puntajeObtenido + " puntos.");
        } else if (equipoQueLanza.equals(equipoB)) {
            puntajeB += puntajeObtenido;
            control.getVentanaEmergente().ventanaPlana("Equipo B (" + nombreB + ") ha anotado: " + puntajeObtenido + " puntos.");
        }

        // Verificar si alguno de los equipos ha alcanzado el puntaje máximo
        if (puntajeA >= puntajeMaximo || puntajeB >= puntajeMaximo) {
            finalizarPartido();
        }
    }

    /**
     * Finaliza el partido y determina el equipo ganador, o si hubo un empate.
     */
    public void finalizarPartido() {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no está en curso.");
        }

        partidoEnCurso = false;
        control.getVentanaEmergente().ventanaPlana("¡El partido ha llegado a su fin!");
    }

    /**
     * Reinicia el partido, restableciendo los puntajes a 0.
     */
    public void reiniciarPartido() {
        puntajeA = 0;
        puntajeB = 0;
        partidoEnCurso = false;
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
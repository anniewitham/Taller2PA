package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ControlPartido {

    private Equipo equipoA;
    private Equipo equipoB;
    private Juez juez;
    private int puntajeA;
    private int puntajeB;
    private boolean partidoEnCurso;
    private Random random = new Random();
    private int puntajeMaximo; // Puntaje máximo para ganar
    private int[] huecos = new int[10]; // Atributos para los huecos del tablero

    /**
     * Constructor que inicializa el partido con dos equipos y un juez.
     *
     * @param equipoA El equipo A.
     * @param equipoB El equipo B.
     * @param juez El juez asignado al partido.
     * @param puntajeMaximo El puntaje máximo para ganar el partido.
     * @throws IllegalArgumentException si alguno de los parámetros es nulo.
     */
    public ControlPartido(Equipo equipoA, Equipo equipoB, Juez juez, int puntajeMaximo) throws IOException {
        if (equipoA == null || equipoB == null || juez == null) {
            throw new IllegalArgumentException("Equipo A, equipo B y juez no pueden ser nulos.");
        }
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.juez = juez;
        this.puntajeMaximo = puntajeMaximo;
        this.puntajeA = 0;
        this.puntajeB = 0;
        this.partidoEnCurso = false;
        
        // Cargar valores de los huecos desde el archivo properties
        cargarHuecosDesdeProperties();
    }

    /**
     * Método para cargar los valores de los huecos desde un archivo properties.
     */
    private void cargarHuecosDesdeProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("config.properties");
        properties.load(input);

        // Asignar los valores a los huecos
        huecos[0] = Integer.parseInt(properties.getProperty("orificio1", "20"));
        huecos[1] = Integer.parseInt(properties.getProperty("orificio2", "20"));
        huecos[2] = Integer.parseInt(properties.getProperty("orificio3", "30"));
        huecos[3] = Integer.parseInt(properties.getProperty("orificio4", "30"));
        huecos[4] = Integer.parseInt(properties.getProperty("orificio5", "40"));
        huecos[5] = Integer.parseInt(properties.getProperty("orificio6", "40"));
        huecos[6] = Integer.parseInt(properties.getProperty("orificio7", "150"));
        huecos[7] = Integer.parseInt(properties.getProperty("orificio8", "150"));
        huecos[8] = Integer.parseInt(properties.getProperty("orificio9", "200"));
        huecos[9] = Integer.parseInt(properties.getProperty("orificio10", "300"));

        input.close();
    }

    /**
     * Inicia la simulación del partido.
     */
    public void iniciarPartido() {
        if (partidoEnCurso) {
            throw new IllegalStateException("El partido ya está en curso.");
        }

        partidoEnCurso = true;
        System.out.println("El partido entre " + equipoA.getNombreEquipo() + " y " + equipoB.getNombreEquipo() + " ha comenzado.");
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
        int huecoSeleccionado = random.nextInt(10); // Random entre 0 y 9
        int puntajeObtenido = huecos[huecoSeleccionado]; // Valor del hueco seleccionado

        // Sumamos el puntaje al equipo que lanza
        if (equipoQueLanza.equals(equipoA)) {
            puntajeA += puntajeObtenido;
            System.out.println("Equipo A (" + equipoA.getNombreEquipo() + ") ha anotado: " + puntajeA + " puntos.");
        } else if (equipoQueLanza.equals(equipoB)) {
            puntajeB += puntajeObtenido;
            System.out.println("Equipo B (" + equipoB.getNombreEquipo() + ") ha anotado: " + puntajeB + " puntos.");
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
        System.out.println("El partido ha terminado.");

        if (puntajeA > puntajeB) {
            System.out.println("¡El equipo A (" + equipoA.getNombreEquipo() + ") ha ganado con " + puntajeA + " puntos!");
        } else if (puntajeB > puntajeA) {
            System.out.println("¡El equipo B (" + equipoB.getNombreEquipo() + ") ha ganado con " + puntajeB + " puntos!");
        } else {
            System.out.println("El partido ha terminado en empate con " + puntajeA + " puntos cada uno.");
        }
    }

    /**
     * Reinicia el partido, restableciendo los puntajes a 0.
     */
    public void reiniciarPartido() {
        puntajeA = 0;
        puntajeB = 0;
        partidoEnCurso = false;
        System.out.println("El partido ha sido reiniciado.");
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


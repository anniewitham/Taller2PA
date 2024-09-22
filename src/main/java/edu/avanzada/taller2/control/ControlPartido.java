package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import java.util.Random;

public class ControlPartido {

    private Equipo equipoA;
    private Equipo equipoB;
    private Juez juez;
    private int puntajeA;
    private int puntajeB;
    private boolean partidoEnCurso;
    private Random random = new Random(); // Para generar puntajes aleatorios
    private int puntajeMaximo; // Puntaje máximo para ganar

    /**
     * Constructor que inicializa el partido con dos equipos y un juez.
     *
     * @param equipoA El equipo A.
     * @param equipoB El equipo B.
     * @param juez El juez asignado al partido.
     * @param puntajeMaximo El puntaje máximo para ganar el partido.
     * @throws IllegalArgumentException si alguno de los parámetros es nulo.
     */
    public ControlPartido(Equipo equipoA, Equipo equipoB, Juez juez, int puntajeMaximo) {
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
     * Simula la asignación de puntajes aleatorios entre 50 y 150 para los dos equipos.
     */
   public void simularPuntaje() throws IllegalStateException, IllegalArgumentException {
    // Verifica si el partido ha comenzado
    if (!partidoEnCurso) {
        throw new IllegalStateException("El partido no ha comenzado aún.");
    }

    // Verifica si el partido ya ha terminado
    if (puntajeA >= puntajeMaximo || puntajeB >= puntajeMaximo) {
        throw new IllegalArgumentException("El partido ya ha terminado.");
    }

    // Probabilidades basadas en 1/8 y 5/8
    int resultado = random.nextInt(8); // Genera un número entre 0 y 7
    int puntajeEquipoA;
    int puntajeEquipoB;

    if (resultado < 3) {
        // Probabilidad 1/8 para tres de los orificios
        puntajeEquipoA = 150;
        puntajeEquipoB = 150;
    } else {
        // Probabilidad 5/8 para el otro orificio
        puntajeEquipoA = random.nextInt(71) + 30; // Puntaje entre 30 y 100
        puntajeEquipoB = random.nextInt(71) + 30; // Puntaje entre 30 y 100
    }

    // Actualizamos los puntajes
    puntajeA += puntajeEquipoA;
    puntajeB += puntajeEquipoB;

    System.out.println("Equipo A (" + equipoA.getNombreEquipo() + ") ha anotado: " + puntajeA + " puntos.");
    System.out.println("Equipo B (" + equipoB.getNombreEquipo() + ") ha anotado: " + puntajeB + " puntos.");

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

    // Getters y Setters para acceder a los atributos de la clase

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
}


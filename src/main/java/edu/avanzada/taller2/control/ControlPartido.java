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
    private int lanzamientosA; // Contador de lanzamientos para el equipo A
    private int lanzamientosB; // Contador de lanzamientos para el equipo B
    private final int maxLanzamientos = 5; // Número máximo de lanzamientos

    /**
     * Constructor que inicializa el partido con dos equipos y un juez.
     *
     * @param equipoA El equipo A.
     * @param equipoB El equipo B.
     * @param juez El juez asignado al partido.
     * @throws IllegalArgumentException si alguno de los parámetros es nulo.
     */
    public ControlPartido(Equipo equipoA, Equipo equipoB, Juez juez) {
        if (equipoA == null || equipoB == null || juez == null) {
            throw new IllegalArgumentException("Equipo A, equipo B y juez no pueden ser nulos.");
        }
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.juez = juez;
        this.puntajeA = 0;
        this.puntajeB = 0;
        this.partidoEnCurso = false;
        this.lanzamientosA = 0;
        this.lanzamientosB = 0;
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
     * Simula la asignación de puntajes aleatorios entre 0, 50 y 150 para los dos
     * equipos.
     */
    public void simularPuntaje() {
        if (!partidoEnCurso) {
            throw new IllegalStateException("El partido no ha comenzado aún.");
        }

        // Verifica si alguno de los equipos ha alcanzado los 5000 puntos
        if (puntajeA >= 5000 || puntajeB >= 5000) {
            System.out.println("El partido ya ha terminado. No se pueden realizar más lanzamientos.");
            return;
        }

        if (lanzamientosA < maxLanzamientos) {
            puntajeA += generarPuntaje(); // Genera un puntaje para el equipo A
            lanzamientosA++;
            System.out.println("Equipo A (" + equipoA.getNombreEquipo() + ") ha anotado: " + puntajeA + " puntos.");
        }

        if (lanzamientosB < maxLanzamientos) {
            puntajeB += generarPuntaje(); // Genera un puntaje para el equipo B
            lanzamientosB++;
            System.out.println("Equipo B (" + equipoB.getNombreEquipo() + ") ha anotado: " + puntajeB + " puntos.");
        }

        // Verifica si alguno de los equipos ha alcanzado los 5000 puntos
        if (puntajeA >= 5000 || puntajeB >= 5000) {
            finalizarPartido();
        }
    }

    /**
     * Genera un puntaje aleatorio entre 0, 50 y 150.
     *
     * @return un puntaje aleatorio.
     */
    private int generarPuntaje() {
        int tipo = random.nextInt(3); // 0, 1, o 2
        switch (tipo) {
            case 0:
                return 0; // 0 puntos
            case 1:
            case 2:
                return random.nextInt(101) + 50; // Puntaje entre 50 y 150
            default:
                return 0; // Esto nunca debería ocurrir
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
        lanzamientosA = 0;
        lanzamientosB = 0;
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

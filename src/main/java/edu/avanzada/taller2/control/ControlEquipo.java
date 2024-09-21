package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import edu.avanzada.taller2.modelo.Capitan;
import java.util.List;

/**
 * Controlador para gestionar las operaciones del equipo.
 */
public class ControlEquipo {

    private Equipo equipo;

    /**
     * Constructor que inicializa el ControlEquipo con un equipo.
     *
     * @param equipo El equipo a gestionar.
     */
    public ControlEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * Agrega un jugador al equipo si no se ha alcanzado el límite.
     *
     * @param jugador El jugador a agregar.
     * @return true si se agregó correctamente, false si el equipo está completo.
     */
    public boolean agregarJugador(Jugador jugador) {
        if (equipo.getJugadores().size() < 5) { // El máximo es 5 jugadores
            equipo.getJugadores().add(jugador);
            return true;
        } else {
            return false; // El equipo ya está completo
        }
    }

    /**
 * Elimina un jugador del equipo.
 *
 * @param jugador El jugador a eliminar.
 * @return true si se eliminó correctamente, false si el jugador no está en el equipo o si no se puede eliminar.
 */
public boolean eliminarJugador(Jugador jugador) {
    // Verifica si el jugador es nulo
    if (jugador == null) {
        System.out.println("No se puede eliminar un jugador nulo.");
        return false;
    }

    // Verifica si el jugador está en la lista
    if (!equipo.getJugadores().contains(jugador)) {
        System.out.println("El jugador no está en el equipo.");
        return false;
    }

    // Si el capitán es el jugador a eliminar
    if (equipo.getCapitan() != null && equipo.getCapitan().equals(jugador)) {
        // Aquí podrías decidir si quieres permitir la eliminación del capitán
        System.out.println("No se puede eliminar al capitán del equipo.");
        return false;
    }

    // Elimina el jugador y devuelve el resultado
    boolean eliminado = equipo.getJugadores().remove(jugador);
    if (eliminado) {
        System.out.println("Jugador eliminado exitosamente.");
    }
    return eliminado;
}

    /**
 * Asigna un capitán al equipo.
 *
 * @param capitan El capitán a asignar.
 * @throws IllegalArgumentException si el capitán es nulo o si ya hay un capitán asignado.
 */
public void asignarCapitan(Capitan capitan) throws IllegalArgumentException {
    // Verifica si el capitán es nulo
    if (capitan == null) {
        throw new IllegalArgumentException("El capitán no puede ser nulo.");
    }

    // Verifica si ya hay un capitán asignado
    if (equipo.getCapitan() != null) {
        throw new IllegalArgumentException("Ya hay un capitán asignado al equipo.");
    }

    // Asigna el capitán
    equipo.setCapitan(capitan);
    System.out.println("Capitán asignado exitosamente: " + capitan.getNombre());
}


    /**
     * Cambia el capitán del equipo por uno nuevo.
     *
     * @param nuevoCapitan El nuevo capitán a asignar.
     * @return true si el cambio fue exitoso, false si el nuevo capitán es el mismo que el actual.
     */
   /**
 * Cambia el capitán del equipo por uno nuevo.
 *
 * @param nuevoCapitan El nuevo capitán a asignar.
 * @return true si el cambio fue exitoso; false si el nuevo capitán es el mismo que el actual.
 * @throws IllegalArgumentException si el nuevo capitán es nulo.
 */
public boolean cambiarCapitan(Capitan nuevoCapitan) throws IllegalArgumentException {
    // Verifica si el nuevo capitán es nulo
    if (nuevoCapitan == null) {
        throw new IllegalArgumentException("El nuevo capitán no puede ser nulo.");
    }

    // Verifica si el nuevo capitán es el mismo que el actual
    if (equipo.getCapitan() != null && equipo.getCapitan().equals(nuevoCapitan)) {
        return false; // No se realiza el cambio
    }

    // Cambia el capitán
    equipo.setCapitan(nuevoCapitan);
    System.out.println("Capitán cambiado exitosamente a: " + nuevoCapitan.getNombre());
    return true; // Cambio exitoso
}


    /**
     * Verifica si el equipo está completo.
     *
     * @return true si el equipo tiene el máximo de jugadores, false en caso contrario.
     */
    public boolean estaCompleto() {
        return equipo.getJugadores().size() >= 5; // Verificamos si el equipo tiene 5 jugadores
    }

    /**
     * Devuelve la lista de jugadores del equipo.
     *
     * @return La lista de jugadores.
     */
    public List<Jugador> obtenerJugadores() {
        return equipo.getJugadores();
    }

    /**
     * Devuelve el capitán del equipo.
     *
     * @return El capitán del equipo.
     */
    public Capitan obtenerCapitan() {
        return equipo.getCapitan();
    }
}


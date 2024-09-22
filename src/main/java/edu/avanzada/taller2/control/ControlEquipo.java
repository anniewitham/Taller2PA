package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

//Controlador
public class ControlEquipo {

    private ControlPrincipal control;
    private ArrayList<Equipo> equipos;
    private Equipo equipo;

    public ControlEquipo(ControlPrincipal control) {
        this.control = control;
        this.equipos = new ArrayList<>();
    }

   
   public void crearEquipo(){
       if(control.getCrearEquipo().cargarNombreEquipo().getText().isEmpty())
   }

//
//    public void crearEquipo(String nombreEquipo, List<Jugador> jugadores) {
//        Equipo equipo = new Equipo(nombreEquipo, nombreEquipo, capitan, jugadores);
//        equipo.setJugadores(jugadores);
//        equipos.add(equipo);
//    }
//
//    public void RegistrarEquipo() {
//        try {
//            // Cargar los datos del equipo desde la vista
//            String nombreEquipo = control.getCrearEquipo().cargarNombreEquipo().getText();
//
//            // Validar que se haya ingresado un nombre de equipo
//            if (nombreEquipo.isEmpty()) {
//                throw new IllegalArgumentException("El nombre del equipo no puede estar vacío.");
//            }
//
//            ArrayList<Jugador> jugadores = new ArrayList<>();
//
//            // Cargar y asignar el capitán
//            String nombreCapitan = control.getCrearEquipo().cargarNombre().getText();
//            String cedulaCapitan = control.getCrearEquipo().cargarCedula().getText();
//            String edadCapitan = control.getCrearEquipo().cargarEdad().getText();
//            String añosExp = control.getCrearEquipo().cargarAñosExp().getText();
//
//            Capitan capitan = new Capitan(nombreCapitan, cedulaCapitan, edadCapitan, añosExp);
//          //  jugadores.add(capitan);
//
//           
//            
//            // Verifica si hay al menos 5 jugadores
//            if (jugadores.size() < 5) {
//                throw new IllegalArgumentException("El equipo debe tener al menos 5 jugadores.");
//            }
//        }
//    }
//    
//
//    // Agrega un jugador al equipo si no se ha alcanzado el límite.
//    public boolean agregarJugador(Jugador jugador) {
//        if (equipo.getJugadores().size() < 5) { // El máximo es 5 jugadores
//            equipo.getJugadores().add(jugador);
//            return true;
//        } else {
//            return false; // El equipo ya está completo
//        }
//    }
//
//// Elimina un jugador del equipo.
//    public boolean eliminarJugador(Jugador jugador) {
//        // Verifica si el jugador es nulo
//        if (jugador == null) {
//            System.out.println("No se puede eliminar un jugador nulo.");
//            return false;
//        }
//
//        // Verifica si el jugador está en la lista de jugadores del equipo
//        if (!equipo.getJugadores().contains(jugador)) {
//            System.out.println("El jugador no está en el equipo.");
//            return false;
//        }
//
//        // Verifica si el jugador es el capitán
//        if (equipo.getCapitan() != null && equipo.getCapitan().equals(jugador)) {
//            System.out.println("No se puede eliminar al capitán del equipo.");
//            return false;
//        }
//
//        // Elimina al jugador de la lista y devuelve el resultado
//        boolean eliminado = equipo.getJugadores().remove(jugador);
//
//        if (eliminado) {
//            System.out.println("Jugador eliminado exitosamente.");
//        } else {
//            System.out.println("Error al eliminar el jugador.");
//        }
//
//        return eliminado;
//    }
//
//    //Asigna un capitán al equipo
//    public void asignarCapitan(Capitan capitan) throws IllegalArgumentException {
//        // Verifica si el capitán es nulo
//        if (capitan == null) {
//            throw new IllegalArgumentException("El capitán no puede ser nulo.");
//        }
//
//        // Verifica si ya hay un capitán asignado
//        if (equipo.getCapitan() != null) {
//            throw new IllegalArgumentException("Ya hay un capitán asignado al equipo.");
//        }
//
//        // Asigna el capitán
//        equipo.setCapitan(capitan);
//        System.out.println("Capitán asignado exitosamente: " + capitan.getNombre());
//    }
//
//    // Cambia el capitán del equipo por uno nuevo.
//    public boolean cambiarCapitan(Capitan nuevoCapitan) throws IllegalArgumentException {
//        // Verifica si el nuevo capitán es nulo
//        if (nuevoCapitan == null) {
//            throw new IllegalArgumentException("El nuevo capitán no puede ser nulo.");
//        }
//
//        // Verifica si el nuevo capitán es el mismo que el actual
//        if (equipo.getCapitan() != null && equipo.getCapitan().equals(nuevoCapitan)) {
//            return false; // No se realiza el cambio
//        }
//
//        // Cambia el capitán
//        equipo.setCapitan(nuevoCapitan);
//        System.out.println("Capitán cambiado exitosamente a: " + nuevoCapitan.getNombre());
//        return true; // Cambio exitoso
//    }
//
//    //Verifica si el equipo está completo.
//    public boolean estaCompleto() {
//        return equipo.getJugadores().size() >= 5; // Verificamos si el equipo tiene 5 jugadores
//    }
//
//    //Devuelve la lista de jugadores del equipo.
//    public List<Jugador> obtenerJugadores() {
//        return equipo.getJugadores();
//    }
//
//    // Devuelve el capitán del equipo.
//    public Capitan obtenerCapitan() {
//        return equipo.getCapitan();
//    }
//
//}
}

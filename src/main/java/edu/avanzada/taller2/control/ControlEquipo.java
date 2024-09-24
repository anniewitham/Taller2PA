package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Jugador;
import edu.avanzada.taller2.vista.CrearEquipo2;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionListener;

//Controlador
public class ControlEquipo implements ActionListener{

    private Properties arcProp;
    private ArrayList<Equipo> equipos;
    private Equipo equipo;
    private CrearEquipo2 vista;
    
    public ControlEquipo(){
        
    }

    public ControlEquipo(Properties arcProp) {
        this.arcProp = arcProp;
        this.equipos = new ArrayList<>(); // Inicializa la lista de equipos
    }

    public void cargarEquipos() {
        for (int i = 1; i <= 2; i++) {
            String nombreEquipo = arcProp.getProperty("equipo" + i + ".nombre");
            String numeroEquipo = arcProp.getProperty("equipo" + i + ".numero");

            // Crear capitán
            String capitanNombre = arcProp.getProperty("equipo" + i + ".capitan.nombre");
            String capitanEdad = arcProp.getProperty("equipo" + i + ".capitan.edad");
            String capitanCedula = arcProp.getProperty("equipo" + i + ".capitan.cedula");
            String aniosExp = arcProp.getProperty("equipo" + i + ".capitan.añosExp");
            Capitan capitan = new Capitan(aniosExp, capitanCedula, nombreEquipo, capitanEdad);

            // Crear jugadores
            List<Jugador> jugadores = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String jugadorNombre = arcProp.getProperty("equipo" + i + ".jugador" + j + ".nombre");
                String jugadorEdad = arcProp.getProperty("equipo" + i + ".jugador" + j + ".edad");
                String jugadorCedula = arcProp.getProperty("equipo" + i + ".jugador" + j + ".cedula");
                String numeroPosicion = arcProp.getProperty("equipo" + i + ".jugador" + j + ".numeroPo");
                Jugador jugador = new Jugador(numeroPosicion, jugadorCedula, nombreEquipo, jugadorEdad);
                jugadores.add(jugador);
            }

            // Crear equipo y añadir a la lista
            Equipo equipo = new Equipo(nombreEquipo, numeroEquipo, capitan, jugadores);
            equipos.add(equipo);
        }
    }

    //Constructor que inicializa el ControlEquipo con un equipo.
    
    public ControlEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // Agrega un jugador al equipo si no se ha alcanzado el límite.
     
    public boolean agregarJugador(Jugador jugador) {
        if (equipo.getJugadores().size() < 5) { // El máximo es 5 jugadores
            equipo.getJugadores().add(jugador);
            return true;
        } else {
            return false; // El equipo ya está completo
        }
    }

// Elimina un jugador del equipo.
public boolean eliminarJugador(Jugador jugador) {
    // Verifica si el jugador es nulo
    if (jugador == null) {
        System.out.println("No se puede eliminar un jugador nulo.");
        return false;
    }

    // Verifica si el jugador está en la lista de jugadores del equipo
    if (!equipo.getJugadores().contains(jugador)) {
        System.out.println("El jugador no está en el equipo.");
        return false;
    }

    // Verifica si el jugador es el capitán
    if (equipo.getCapitan() != null && equipo.getCapitan().equals(jugador)) {
        System.out.println("No se puede eliminar al capitán del equipo.");
        return false;
    }

    // Elimina al jugador de la lista y devuelve el resultado
    boolean eliminado = equipo.getJugadores().remove(jugador);
    
    if (eliminado) {
        System.out.println("Jugador eliminado exitosamente.");
    } else {
        System.out.println("Error al eliminar el jugador.");
    }
    
    return eliminado;
}


    //Asigna un capitán al equipo
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

    // Cambia el capitán del equipo por uno nuevo.
    
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

    
     //Verifica si el equipo está completo.
     
    public boolean estaCompleto() {
        return equipo.getJugadores().size() >= 5; // Verificamos si el equipo tiene 5 jugadores
    }

    
   //Devuelve la lista de jugadores del equipo.
    
    public List<Jugador> obtenerJugadores() {
        return equipo.getJugadores();
    }

    // Devuelve el capitán del equipo.
    
    public Capitan obtenerCapitan() {
        return equipo.getCapitan();
    }
    
    public void crearVistaCrearEquipo(){
        vista = new CrearEquipo2(this);
        vista.BotonRegistrar.addActionListener(this);
        vista.BotonSalir.addActionListener(this);
        vista.BotonVolver.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}



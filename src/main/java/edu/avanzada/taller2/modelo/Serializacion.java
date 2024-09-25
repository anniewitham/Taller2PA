
package edu.avanzada.taller2.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializacion {
    private static final String RUTA_ARCHIVO = "files/specs/lista_personas.bin";

    /**
     * Serializa una lista de personas en el archivo especificado.
     *
     * @param listaPersonas La lista de personas a serializar.
     * @throws IOException Si ocurre un error durante la serialización.
     */
    public static void serializarPersonas(List<Persona> listaPersonas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(listaPersonas);
        }
    }

    /**
     * Deserializa la lista de personas desde el archivo especificado.
     *
     * @return La lista de personas deserializada.
     * @throws IOException Si ocurre un error durante la deserialización.
     * @throws ClassNotFoundException Si la clase no se encuentra.
     */
    public static List<Persona> deserializarPersonas() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            return (List<Persona>) ois.readObject();
        }
    }
    
    
    public static void serializarEquipo(Equipo equipo) {
        try (FileOutputStream fileOut = new FileOutputStream("equipo_" + equipo.getNombreEquipo() + ".ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(equipo);
            System.out.println("Equipo serializado correctamente.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
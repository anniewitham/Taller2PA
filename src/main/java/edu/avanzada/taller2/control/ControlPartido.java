
package edu.avanzada.taller2.control;

import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Capitan;
import java.io.*;
import java.util.Properties;

public class ControlPartido {

    private Equipo equipo1;
    private Equipo equipo2;
    private Juez juez;
    
    // Constructor para inicializar equipos y juez
    public ControlPartido(Equipo equipo1, Equipo equipo2, Juez juez) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.juez = juez;
    }

    // Método para simular un partido
    public String simularPartido() throws Exception {
        // Simula el partido entre equipo1 y equipo2
        int puntaje1 = equipo1.calcularPuntaje();
        int puntaje2 = equipo2.calcularPuntaje();
        
        // Determinar el resultado
        String resultado;
        if (puntaje1 > puntaje2) {
            resultado = "El equipo " + equipo1.getNombre() + " gana con " + puntaje1 + " puntos.";
        } else if (puntaje1 < puntaje2) {
            resultado = "El equipo " + equipo2.getNombre() + " gana con " + puntaje2 + " puntos.";
        } else {
            resultado = "El partido terminó en empate con " + puntaje1 + " puntos cada uno.";
        }
        
        guardarResultado(resultado); // Guarda el resultado
        return resultado;
    }

    // Método para guardar el resultado del partido en un archivo
    private void guardarResultado(String resultado) throws IOException {
        try (FileWriter writer = new FileWriter("resultado_partido.txt", true)) {
            writer.write(resultado + "\n");
        }
    }

    // Método para cargar las configuraciones del partido desde un archivo de properties
    public void cargarConfiguracion(String archivoConfig) throws IOException {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(archivoConfig)) {
            props.load(reader);
        }
        
        // Cargar configuraciones específicas, por ejemplo, reglas del juez
        String reglasJuez = props.getProperty("reglas.juez");
        juez.setReglas(reglasJuez);
    }

    // Método para serializar un partido (guarda el estado del partido en un archivo)
    public void serializarPartido(String archivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(equipo1);
            out.writeObject(equipo2);
            out.writeObject(juez);
        }
    }

    // Método para deserializar un partido (carga un estado de partido guardado)
    public void deserializarPartido(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            equipo1 = (Equipo) in.readObject();
            equipo2 = (Equipo) in.readObject();
            juez = (Juez) in.readObject();
        }
    }
}


package edu.avanzada.taller2.control;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JFileChooser;

public class ControlConexion {

    private Properties misPropiedades;
    private File archivoPropiedades;

    public ControlConexion() {
        misPropiedades = new Properties();
        archivoPropiedades = seleccionarArchivo();
        cargarPropiedades();
    }

    // Método para seleccionar el archivo de propiedades
    private File seleccionarArchivo() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.showOpenDialog(fc);
        return fc.getSelectedFile();
    }

    // Método para cargar el archivo de propiedades
    private void cargarPropiedades() {
        try {
            if (archivoPropiedades != null) {
                FileInputStream archivo = new FileInputStream(archivoPropiedades);
                misPropiedades.load(archivo);
                archivo.close();
            } else {
                System.out.println("No se seleccionó ningún archivo.");
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo properties: " + e.getMessage());
        }
    }

    // Método para obtener las propiedades cargadas
    public Properties getPropiedades() {
        return misPropiedades;
    }

}

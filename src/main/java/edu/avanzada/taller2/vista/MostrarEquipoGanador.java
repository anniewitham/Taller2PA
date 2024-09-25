package edu.avanzada.taller2.vista;

import edu.avanzada.taller2.control.ControlPrincipal;
import edu.avanzada.taller2.modelo.Capitan;
import edu.avanzada.taller2.modelo.Equipo;
import edu.avanzada.taller2.modelo.Juez;
import edu.avanzada.taller2.modelo.Jugador;

/**
 *
 * @author Personal
 */
public class MostrarEquipoGanador extends javax.swing.JFrame {

     private ControlPrincipal control;
    /**
     * Creates new form MostrarEquipoGanador
     */
    public MostrarEquipoGanador(ControlPrincipal aThis) {
        initComponents();
        control = aThis;
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaMostrarEquipoGanador = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel26.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 153));
        jLabel26.setText("Equipo Ganador");

        BotonSalir.setBackground(new java.awt.Color(186, 186, 249));
        BotonSalir.setText("Salir");
        BotonSalir.setBorder(null);

        AreaMostrarEquipoGanador.setColumns(20);
        AreaMostrarEquipoGanador.setRows(5);
        jScrollPane1.setViewportView(AreaMostrarEquipoGanador);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostrarDatosEquipoGanador(Equipo equipoGanador, Juez juez) {
    StringBuilder info = new StringBuilder();
    
    info.append("Equipo Ganador:\n");
    info.append("Nombre del Equipo: ").append(equipoGanador.getNombreEquipo()).append("\n");
    info.append("Número del Equipo: ").append(equipoGanador.getNumeroEquipo()).append("\n");
    
    // Información del capitán
    Capitan capitan = equipoGanador.getCapitan();
    info.append("Capitán: ").append(capitan.toString()).append("\n");
    
    // Información de los jugadores
    for (Jugador jugador : equipoGanador.getJugadores()) {
        info.append(jugador.toString()).append("\n");
    }
    
    // Información del juez
    info.append("Juez: ").append(juez.toString()).append("\n");

    // Mostrar la información en el JTextArea
    AreaMostrarEquipoGanador.setText(info.toString());
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaMostrarEquipoGanador;
    public javax.swing.JButton BotonSalir;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

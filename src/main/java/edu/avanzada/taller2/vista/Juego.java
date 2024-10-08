package edu.avanzada.taller2.vista;

import edu.avanzada.taller2.control.ControlPrincipal;
import javax.swing.JRadioButton;

public class Juego extends javax.swing.JFrame {
    
     private ControlPrincipal control;
     
    public Juego(ControlPrincipal aThis) {
        initComponents();
        control = aThis;
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BotonLanzarBola = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        BotonLanzarBola.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        BotonLanzarBola.setText("Lanzar");
        BotonLanzarBola.setBorder(null);
        BotonLanzarBola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLanzarBolaActionPerformed(evt);
            }
        });
        jPanel1.add(BotonLanzarBola);
        BotonLanzarBola.setBounds(150, 190, 38, 15);

        jRadioButton1.setBackground(new java.awt.Color(204, 204, 255));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1);
        jRadioButton1.setBounds(100, 160, 30, 20);

        jLabel26.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 153));
        jLabel26.setText("Cancha de Bolirana");
        jPanel1.add(jLabel26);
        jLabel26.setBounds(46, 15, 180, 22);

        BotonSalir.setBackground(new java.awt.Color(186, 186, 249));
        BotonSalir.setText("Salir");
        BotonSalir.setBorder(null);
        jPanel1.add(BotonSalir);
        BotonSalir.setBounds(262, 7, 57, 30);

        jLabel1.setText("O");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(78, 85, 9, 16);

        jLabel2.setText("<))>");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 100, 24, 16);

        jLabel3.setText("_/\\_");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(70, 120, 20, 16);

        jLabel4.setText("l");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 120, 20, 16);

        jLabel5.setText("----------");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(220, 70, 50, 16);

        jLabel6.setText("----------");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(220, 130, 50, 16);

        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("l");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(110, 140, 20, 16);

        jLabel8.setText("l");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(220, 90, 20, 16);

        jLabel9.setText("l");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(220, 100, 20, 16);

        jLabel10.setText("l");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(220, 110, 20, 16);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("🐸");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(270, 90, 30, 30);

        jLabel12.setText("🐸");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(250, 80, 30, 30);

        jLabel13.setText("🐸");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(250, 110, 30, 30);

        jLabel14.setText("l");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(220, 80, 20, 16);

        jLabel15.setForeground(new java.awt.Color(0, 153, 0));
        jLabel15.setText("l");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(110, 80, 20, 16);

        jLabel16.setForeground(new java.awt.Color(0, 153, 0));
        jLabel16.setText("l");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(110, 100, 20, 16);

        jLabel17.setForeground(new java.awt.Color(0, 153, 0));
        jLabel17.setText("l");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(110, 120, 20, 16);

        jLabel18.setForeground(new java.awt.Color(0, 153, 0));
        jLabel18.setText("l");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(110, 60, 20, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void BotonLanzarBolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLanzarBolaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonLanzarBolaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonLanzarBola;
    public javax.swing.JButton BotonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables

    public JRadioButton getjRadioButton1() {
        return jRadioButton1;
    }
}

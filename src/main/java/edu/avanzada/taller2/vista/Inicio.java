/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.avanzada.taller2.vista;

import edu.avanzada.taller2.control.ControlPrincipal;

/**
 *
 * @author Personal
 */
public class Inicio extends javax.swing.JFrame {

    private ControlPrincipal control;

    /**
     * Creates new form Inicio
     */
    public Inicio(ControlPrincipal aThis) {
        initComponents();
        setLocationRelativeTo(null);
        control = aThis;
        setVisible(true);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BotonRegistrarEquipo = new javax.swing.JButton();
        BotonJugar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();
        BotonJuez = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Bienvenido ");

        BotonRegistrarEquipo.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonRegistrarEquipo.setForeground(new java.awt.Color(102, 102, 102));
        BotonRegistrarEquipo.setText("Registrar Equipo");
        BotonRegistrarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarEquipoActionPerformed(evt);
            }
        });

        BotonJugar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonJugar.setForeground(new java.awt.Color(102, 102, 102));
        BotonJugar.setText("Jugar");
        BotonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonJugarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Al juego de bolirana");

        BotonSalir.setBackground(new java.awt.Color(186, 186, 249));
        BotonSalir.setText("Salir");
        BotonSalir.setBorder(null);

        BotonJuez.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        BotonJuez.setForeground(new java.awt.Color(102, 102, 102));
        BotonJuez.setText("Registrar Juez");
        BotonJuez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonJuezActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 87, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(BotonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonRegistrarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonJuez, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)
                        .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(BotonJuez, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(BotonRegistrarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonRegistrarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonRegistrarEquipoActionPerformed

    private void BotonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonJugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonJugarActionPerformed

    private void BotonJuezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonJuezActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonJuezActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BotonJuez;
    public javax.swing.JButton BotonJugar;
    public javax.swing.JButton BotonRegistrarEquipo;
    public javax.swing.JButton BotonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

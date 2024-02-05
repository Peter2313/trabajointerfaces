/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.Vista;

import com.mycompany.Controlador.sqlhelperactujugador;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author pedro Garcia vicente
 */
public class actujugador extends javax.swing.JPanel {

  private sqlhelperactujugador sql = new sqlhelperactujugador();
  /**
   * Creates new form inicio
   */
  Fondopanel fondo = new Fondopanel();
  Fondopanelmedio fondop = new Fondopanelmedio();

  public actujugador() {
    //this.setContentPane(fondo);
    initComponents();
    UIManager.put("Button.arc", 10);

    //tfpassword.putClientProperty( "JComponent.roundRect", true );
    tfnombre.putClientProperty("JTextField.placeholderText", "Introduzca nombre nuevo a cambiar");
    tfnombre.putClientProperty("FlatLaf.style", "arc:" + 12);
    jTfcambion.putClientProperty("JTextField.placeholderText", "Introduzca nombre jugador");
    jTfcambion.putClientProperty("FlatLaf.style", "arc:" + 12);

  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new Fondopanel();
    jLabel1 = new javax.swing.JLabel();
    jPanel2 = new Fondopanelmedio();
    jLabel5 = new javax.swing.JLabel();
    btnactujugador = new javax.swing.JButton();
    tfnombre = new javax.swing.JTextField();
    jTfcambion = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();

    setPreferredSize(new java.awt.Dimension(1160, 900));

    jPanel1.setPreferredSize(new java.awt.Dimension(1160, 900));

    jLabel1.setBackground(new java.awt.Color(255, 255, 255));
    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("ACTUALIZAR JUGADOR");

    jPanel2.setBackground(new java.awt.Color(0, 0, 0));
    jPanel2.setPreferredSize(new java.awt.Dimension(853, 780));

    jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setText("Nombre Jugador a cambiar");

    btnactujugador.setBackground(new java.awt.Color(98, 75, 255));
    btnactujugador.setForeground(new java.awt.Color(255, 255, 255));
    btnactujugador.setText("Actualizar Jugador");
    btnactujugador.setBorder(null);
    btnactujugador.setBorderPainted(false);
    btnactujugador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnactujugador.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnactujugadorActionPerformed(evt);
      }
    });

    tfnombre.setToolTipText("nombre nuevo");
    tfnombre.setPreferredSize(new java.awt.Dimension(263, 42));

    jTfcambion.setToolTipText("nombre del jugador ya inscrito");

    jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("Nombre Jugador");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(btnactujugador, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(364, 364, 364))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addGap(144, 144, 144)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(tfnombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jTfcambion))
        .addGap(96, 96, 96))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGap(226, 226, 226)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(tfnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(12, 12, 12)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTfcambion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(79, 79, 79)
        .addComponent(btnactujugador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(343, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(142, 142, 142)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(371, 371, 371)
            .addComponent(jLabel1)))
        .addContainerGap(165, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addGap(26, 26, 26)
        .addComponent(jLabel1)
        .addGap(18, 18, 18)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(28, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents
/**
   * Este boton es para actualizar el equipo le paso los datos a un controlador
   *
   * @param evt
   */
    private void btnactujugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactujugadorActionPerformed
    // TODO add your handling code here:

    //if por los campos vacios
    if (tfnombre.getText().toString().isEmpty() || jTfcambion.getText().toString().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
    } else {
      String njugador = tfnombre.getText();
      String njugadorcambiar = jTfcambion.getText();

      sql.actualizarJugador(njugadorcambiar, njugador);

      tfnombre.setText("");
      jTfcambion.setText("");
    }
    }//GEN-LAST:event_btnactujugadorActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnactujugador;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JTextField jTfcambion;
  private javax.swing.JTextField tfnombre;
  // End of variables declaration//GEN-END:variables

  /**
   * Esta clase es para meter una foto en un panel
   */
  class Fondopanel extends JPanel {

    private Image imagen;

    public void paint(Graphics g) {
      imagen = new ImageIcon(getClass().getResource("/img/fondopaneles2.jpg")).getImage();
      g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

      setOpaque(false);

      super.paint(g);
    }
  }

  /**
   * Esta clase es para meter una foto en un panel
   */
  class Fondopanelmedio extends JPanel {

    private Image imagen;

    public void paint(Graphics g) {
      imagen = new ImageIcon(getClass().getResource("/img/panelformu2.png")).getImage();
      g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

      setOpaque(false);

      super.paint(g);
    }
  }
}

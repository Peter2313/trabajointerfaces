/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Vista;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.mycompany.Controlador.sqlhelperlogin;
import com.mycompany.Modelo.Usuarios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 *
 * @author pedro Garcia vicente
 */
public class iniciosesion extends javax.swing.JFrame {

  private Registro regis;
  private sqlhelperlogin sql = new sqlhelperlogin();
  private Dashboard dash;
  private recuemail recu;

  /**
   * Creates new form iniciosesion
   */
  public iniciosesion() {
    initComponents();
    tfusername.putClientProperty("FlatLaf.style", "arc:" + 12);
    // UIManager.put( "TextComponent.arc", 999 );
    tfusername.putClientProperty("JTextField.placeholderText", "Introduzca usuario");

    tfpassword.putClientProperty("FlatLaf.style", "arc:" + 12);
    tfpassword.putClientProperty("JTextField.placeholderText", "Introduzca contraseña");
    btnini.putClientProperty("JButton.buttonType", "roundRect");
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labeltitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelregistro = new javax.swing.JLabel();
        tfpassword = new javax.swing.JTextField();
        tfusername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnini = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(34, 34, 34));

        labeltitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labeltitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NGALAXYSTARb.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No tienes cuenta");

        labelregistro.setForeground(new java.awt.Color(204, 0, 204));
        labelregistro.setText("<html><u>Registrate</u></html>");
        labelregistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelregistroMouseClicked(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel2.setForeground(new java.awt.Color(204, 0, 204));
        jLabel2.setText("<html><u>Olvidastes la Contraseña</u></html>");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        btnini.setBackground(new java.awt.Color(75, 6, 99));
        btnini.setForeground(new java.awt.Color(255, 255, 255));
        btnini.setText("Iniciar Sesión");
        btnini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labeltitulo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfusername, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnini, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(labeltitulo)
                .addGap(32, 32, 32)
                .addComponent(tfusername, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(tfpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnini, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  /**
   * cuando clickes en el label que pone registrar te llevara al frame (Pantalla) de registrar
   *
   * @param evt
   */
  private void labelregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelregistroMouseClicked
    // TODO add your handling code here:
    regis = new Registro();
    regis.setVisible(true);
    dispose();
  }//GEN-LAST:event_labelregistroMouseClicked

  /**
   * Este metodo se encarga de mandar el usuario y contraseña al controlador para poder mirar si esta en la base de datos e iniciar sesion.
   *
   * @param evt
   */
    private void btniniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniActionPerformed
    // TODO add your handling code here:
    String usu = tfusername.getText();
    String cont = tfpassword.getText();
    try {
      // Obtener la contraseña almacenada del usuario
      String contrasenaAlmacenada = sql.obtenerContrasenaDeUsuario(usu);

      // Verificar si la contraseña almacenada coincide con la ingresada por el usuario
      boolean comprueba = sql.verificarContrasena(cont, contrasenaAlmacenada);

      if (!comprueba) {
        JOptionPane.showMessageDialog(rootPane, "Credenciales no válidas", "Error en el inicio de sesión", JOptionPane.ERROR_MESSAGE);
      } else {
        //// Si el inicio de sesión es exitoso, guarda el usuario
        /*Usuarios user = new Usuarios();
        user.setUsuario(usu);*/
        File f = new File("src/main/resources/nombreusuario.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
          // Escribir en el archivo
          writer.write(usu);
          writer.newLine();
          System.out.println("Información guardada en el archivo: " + f);
        } catch (IOException e) {
          e.printStackTrace();
        }
        // Acceder al usuario guardado esto en otro frame
        //String usuario = MiClase.getUsuarioActual();
        // Usa el valor del usuario como sea necesario en este otro frame
        dispose();
        // para ir a la otra ventana
        Dashboard dash = new Dashboard();
        // cerrar o esconder antes de abrir principal
        dash.setVisible(true);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    }//GEN-LAST:event_btniniActionPerformed

  /**
   * Este label se utiliza para ir al frame de recuperar contraseña
   *
   * @param evt
   */
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
    // TODO add your handling code here:
    recu = new recuemail();
    recu.setVisible(true);
    dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(iniciosesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(iniciosesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(iniciosesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(iniciosesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    FlatLightFlatIJTheme.setup();

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new iniciosesion().setVisible(true);
      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelregistro;
    private javax.swing.JLabel labeltitulo;
    private javax.swing.JTextField tfpassword;
    private javax.swing.JTextField tfusername;
    // End of variables declaration//GEN-END:variables
}

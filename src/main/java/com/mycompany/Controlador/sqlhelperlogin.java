/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Usuarios;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperlogin {

   /**
    * Método para verificar si existe un usuario con un nombre de usuario específico
    * @param username nombre usuario
    * @return true o flase si existe o no
    */
    public boolean existeUsuario(String username) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sesion = sessionFactory.openSession();

        try {
            String hql = "SELECT COUNT(u.usuario) FROM Usuarios u WHERE u.usuario = :usuario";
            Query<Long> query = sesion.createQuery(hql, Long.class);
            query.setParameter("usuario", username);

            Long count = query.uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del usuario.");
            return false;
        } finally {
            sesion.close();
        }
}

    
  /**
   * Método para obtener la contraseña de un usuario específico
   * @param username nombre de usuario
   * @return devuelve la contraseña del usuario
   */
  public String obtenerContrasenaDeUsuario(String username) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session sesion = sessionFactory.openSession();

    try {
      String hql = "SELECT u.contrasena FROM Usuarios u WHERE u.usuario = :usuario";
      Query<String> query = sesion.createQuery(hql, String.class);
      query.setParameter("usuario", username);

      return query.uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
      return null;
    } finally {
      sesion.close();
    }
  }

  /**
   * Método para verificar si la contraseña coincide (puedes usar BCrypt)
   * @param contrasenaInput contraseña introducida por el usuario en el login
   * @param contrasenaAlmacenada contraseña de la base de datos
   * @return devuelve si son iguales o no para poder inciar sesion
   */
  public boolean verificarContrasena(String contrasenaInput, String contrasenaAlmacenada) {
    // Implementa aquí la lógica para verificar la contraseña (puedes usar BCrypt)
    return BCrypt.checkpw(contrasenaInput, contrasenaAlmacenada);
  }
}

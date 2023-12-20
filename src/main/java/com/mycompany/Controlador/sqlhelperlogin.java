/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Usuarios;
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

  /*// Método para obtener un usuario por nombre de usuario
    private Usuarios obtenerUsuarioPorUsername(String username) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sesion = sessionFactory.openSession();

        try {
            String hql = "FROM usuarios WHERE usuario = :usuario";
            Query<Usuarios> query = sesion.createQuery(hql, Usuarios.class);
            query.setParameter("usuario", username);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sesion.close();
        }
    }*/
  // Método para obtener la contraseña de un usuario específico
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
      return null;
    } finally {
      sesion.close();
    }
  }

  // Método para verificar si la contraseña coincide (puedes usar BCrypt)
  public boolean verificarContrasena(String contrasenaInput, String contrasenaAlmacenada) {
    // Implementa aquí la lógica para verificar la contraseña (puedes usar BCrypt)
    return BCrypt.checkpw(contrasenaInput, contrasenaAlmacenada); // Esto es solo un ejemplo, debes utilizar una función de verificación segura
  }
}

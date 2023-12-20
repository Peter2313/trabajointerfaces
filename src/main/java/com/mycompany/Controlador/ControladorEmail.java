/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Controlador.HibernateUtil;
import com.mycompany.Modelo.Usuarios;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.security.SecureRandom;
import java.util.Properties;
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
public class ControladorEmail {

  public boolean verificarEmail(String email) {
    boolean emailExiste = false;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session sesion = sessionFactory.openSession();
    Transaction tx = sesion.beginTransaction();

    try {
      // Crear la consulta HQL
      String hql = "FROM Usuarios WHERE email = :email";
      Query<Usuarios> query = sesion.createQuery(hql, Usuarios.class);
      query.setParameter("email", email);

      // Obtener el resultado de la consulta
      Usuarios usuario = query.uniqueResult();

      // Verificar si se encontró un usuario con las credenciales proporcionadas
      if (usuario != null) {
        emailExiste = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      tx.commit();
      sesion.close();
    }

    return emailExiste;
  }

  public String generarContrasena(int longitudMin, int longitudMax) {
    String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    longitudMin = Math.max(1, longitudMin);
    // Método para generar una contraseña aleatoria
    int longitud = longitudMin + new SecureRandom().nextInt(longitudMax - longitudMin + 1);

    StringBuilder contraseñaGenerada = new StringBuilder();
    SecureRandom aleatorio = new SecureRandom();

    for (int i = 0; i < longitud; i++) {
      int indice = aleatorio.nextInt(CARACTERES.length());
      contraseñaGenerada.append(CARACTERES.charAt(indice));
    }

    return contraseñaGenerada.toString();
  }

  public void actualizarContrasena(String email, String contrasena) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    // Comienza una transacción
    Transaction transaction = null;

    try {
      // Comienza la transacción
      transaction = session.beginTransaction();

      // Busca al usuario por el nombre de usuario
      String hql = "FROM Usuarios WHERE email = :email";
      Query<Usuarios> query = session.createQuery(hql, Usuarios.class);
      query.setParameter("email", email);

      // Obtener el resultado de la consulta
      Usuarios usuario = query.uniqueResult();

      // Verifica si el usuario existe
      if (usuario != null) {
        // Encripta la nueva contraseña antes de almacenarla
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        usuario.setContrasena(hashedPassword);

        // Actualiza el usuario en la base de datos
        session.update(usuario);

        // Confirma la transacción
        transaction.commit();
      } else {
        System.out.println("Usuario no encontrado.");
      }
    } catch (Exception e) {
      // Si hay algún error, realiza un rollback de la transacción
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace(); // Trata el error según tus necesidades
    } finally {
      // Cierra la sesión de Hibernate
      session.close();
    }
  }
}

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
public class sqlhelperloginregister {
/**
 * Metodo para verificar el correo,usuario y contraseña
 * @param username nombre de usuario
 * @param email email
 * @param contrasena contraseña
 * @return  devuelve true o false
 */
  public boolean verificarCredenciales(String username, String email, String contrasena) {
    boolean credencialesValidas = false;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session sesion = sessionFactory.openSession();
    Transaction tx = sesion.beginTransaction();

    try {
      // Crear la consulta HQL
      String hql = "FROM Usuarios WHERE usuario = :usuario OR email = :email OR contrasena = :contrasena";
      Query<Usuarios> query = sesion.createQuery(hql, Usuarios.class);
      query.setParameter("usuario", username);
      query.setParameter("email", email);
      query.setParameter("contrasena", contrasena);

      // Obtener el resultado de la consulta
      Usuarios usuario = query.uniqueResult();

      // Verificar si se encontró un usuario con las credenciales proporcionadas
      if (usuario != null) {
        credencialesValidas = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      tx.commit();
      sesion.close();
    }

    return credencialesValidas;
  }

  /**
   * Metodo para registrar usuario
   * @param username nombre de usuario
   * @param password contraseña
   * @param email email
   * @param nombre nombre del presidente
   * @param apellido apellidos del presidente
   */
  public void introducirUsuario(String username, String password, String email, String nombre, String apellido) {
    // Obtén la sesión de Hibernate
    Session session = HibernateUtil.getSessionFactory().openSession();

    // Comienza una transacción
    Transaction transaction = null;

    try {
      // Comienza la transacción
      transaction = session.beginTransaction();

      // Crea un nuevo usuario y establece sus propiedades
      Usuarios user = new Usuarios();
      user.setUsuario(username);

      // Encripta la contraseña antes de almacenarla
      String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
      user.setContrasena(hashedPassword);

      user.setEmail(email);

      user.setNombre(nombre);

      user.setApellidos(apellido);
      // Guarda el usuario en la base de datos
      session.save(user);

      // Confirma la transacción
      transaction.commit();
    } catch (Exception e) {
      // Si hay algún error, realiza un rollback de la transacción
      if (transaction != null) {
        transaction.rollback();
       
      }
      e.printStackTrace(); 
    } finally {
      // Cierra la sesión de Hibernate
      session.close();
    }
  }
}

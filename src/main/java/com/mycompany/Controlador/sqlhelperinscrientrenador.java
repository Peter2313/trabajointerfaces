/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Entrenadores;
import com.mycompany.Modelo.Equipo;
import com.mycompany.Modelo.Jugadores;
import com.mycompany.Modelo.Usuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperinscrientrenador {

  private String nombreArchivo = "src/main/resources/nombreusuario.txt";
  private String contenidoLeido = leerDesdeArchivo(nombreArchivo);
  private int idusuario;

  private static String leerDesdeArchivo(String nombreArchivo) {
    StringBuilder contenido = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
      String linea;
      while ((linea = reader.readLine()) != null) {
        contenido.append(linea);
      }
      System.out.println("Información leída desde el archivo: " + nombreArchivo);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return contenido.toString();
  }

  public int obtenerIdUsuarioPorNombre() {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    try {
      // Consulta HQL para obtener el idUsuario por nombre de usuario
      String hql = "SELECT u.id FROM Usuarios u WHERE usuario = :nombreusuario";
      Query<Integer> query = session.createQuery(hql, Integer.class);
      query.setParameter("nombreusuario", contenidoLeido);

      // Obtener el resultado de la consulta
      Integer idUsuario = query.uniqueResult();
      idusuario = idUsuario;
      // System.out.println(idusuario);
      return idusuario; // Devolver -1 si no se encuentra el usuario
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    } finally {
      session.close();
    }
  }

  public static Usuarios findById(int id) {
    System.out.println(id + "metodo find");
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // Consulta HQL para obtener el usuario por su id
      String hql = "FROM Usuarios u WHERE u.id = :id";
      return session.createQuery(hql, Usuarios.class)
        .setParameter("id", id)
        .uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void inscribirentrenador(String nombreentre, String clocal, String cvisitante) {
    // Obtener el equipo utilizando el idEquipo proporcionado
    Equipo equipo = obtenerEquipoPorId();

    if (equipo != null) {
      Transaction transaction = null;
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();

      try {
        // Comenzar transacción
        transaction = session.beginTransaction();

        // Crear un objeto Jugador con los parámetros proporcionados
        Entrenadores entre = new Entrenadores();
        entre.setEquipo(equipo);
        entre.setNombreCompletoentrenador(nombreentre);
        entre.setClocalColor(clocal);
        entre.setCvisitanteColor(cvisitante);

        // Guardar el jugador en la base de datos
        session.save(entre);

        // Commit de la transacción
        transaction.commit();
        JOptionPane.showMessageDialog(null, "Entreandor inscrito.");
      } catch (Exception e) {
        if (transaction != null) {
          // Rollback en caso de error
          transaction.rollback();
          JOptionPane.showMessageDialog(null, "Entrenador no  inscrito.");
        }
        e.printStackTrace();
      } finally {
        session.close();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
    }
  }

// Método para obtener un equipo por su id
  private Equipo obtenerEquipoPorId() {
    Transaction transaction = null;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    try {
      // Comenzar transacción
      transaction = session.beginTransaction();

      Usuarios usuario = findById(obtenerIdUsuarioPorNombre());
      // Obtener el equipo por su id
      //Equipo equipo = session.get(Equipo.class, idEquipo);
      Equipo equipo = Usuarios.findByEquipo(usuario);
      // Commit de la transacción
      transaction.commit();

      return equipo;
    } catch (Exception e) {
      if (transaction != null) {
        // Rollback en caso de error
        transaction.rollback();
      }
      e.printStackTrace();
      return null;
    } finally {
      session.close();
    }
  }

}

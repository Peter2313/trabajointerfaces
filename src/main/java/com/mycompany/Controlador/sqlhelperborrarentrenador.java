/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import static com.mycompany.Controlador.sqlhelperactujugador.findById;
import com.mycompany.Modelo.Entrenadores;
import com.mycompany.Modelo.Equipo;

import com.mycompany.Modelo.Usuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperborrarentrenador {

  private String nombreArchivo = "src/main/resources/nombreusuario.txt";
  private String contenidoLeido = leerDesdeArchivo(nombreArchivo);
  private int idusuario;

  /**
   * Este metodo es para leer desde un archivo txt el nombre del usuario iniciado
   * @param nombreArchivo archivo txt con el nombre de usuario logeado
   * @return  devuelve lo escrito en le txt
   */
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

  /**
   * metodo para obtener id de usuario
   * @return devuelve el id del usuario
   */
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
  
    /**
   * Este metodo es para buscar el usuario por el id 
   * @param id se pasa el id del usuario
   * @return devuelve el usuario
   */
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

  /**
   * Este metodo es para borrar un entrenador
   * @param nombreEntrenador nombre entrenador a borrar 
   */
  public void borrarEntrenador(String nombreEntrenador) {
    Transaction transaction = null;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    try {
      // Comenzar la transacción
      transaction = session.beginTransaction();

      // Obtener el equipo por ID
      Equipo equipo = obtenerEquipoPorId();

      if (equipo != null) {
        // Obtener el entrenador por nombre y perteneciente al equipo
        Entrenadores entrenador = findEntrenadorByNombreYEquipo(nombreEntrenador, equipo);

        if (entrenador != null) {
          // Borrar el entrenador
          session.delete(entrenador);
          transaction.commit();
          JOptionPane.showMessageDialog(null, "Entrenador borrado correctamente");
        } else {
          JOptionPane.showMessageDialog(null, "Entrenador no encontrado en el equipo especificado.");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Entrenador no encontrado");
      }
    } catch (Exception e) {
      // Manejar cualquier excepción
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      // Cerrar la transacción y la sesión de Hibernate
      if (session != null) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        session.close();
      }
    }
  }

  /**
   * Metodo para buscar entrenador por su nombre y equipo
   * @param nombrenetrenador nombre entrenador
   * @param equipo nombre del equipo
   * @return devuelve el entreandor
   */
  private Entrenadores findEntrenadorByNombreYEquipo(String nombrenetrenador, Equipo equipo) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Entrenadores> criteria = builder.createQuery(Entrenadores.class);
      Root<Entrenadores> root = criteria.from(Entrenadores.class);

      criteria.select(root)
        .where(builder.equal(root.get("nombreCompletoentrenador"), nombrenetrenador),
          builder.equal(root.get("equipo"), equipo));

      return session.createQuery(criteria).uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

    /**
     * Método para obtener un equipo por su id
     * @return  devuelve el equipo por id
     */
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

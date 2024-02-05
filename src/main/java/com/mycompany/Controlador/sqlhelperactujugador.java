/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Equipo;
import com.mycompany.Modelo.Jugadores;
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
public class sqlhelperactujugador {

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
     * Este metdo es para actualizar el equipo
     * @param nombreJugador el nombre del jugador
     * @param nuevoNombre el nuevo nombre a cambiar
     */
  public void actualizarJugador(String nombreJugador, String nuevoNombre) {
    Transaction transaction = null;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    try {
      // Comenzar la transacción
      transaction = session.beginTransaction();

      // Obtener el equipo por ID
      Equipo equipo = obtenerEquipoPorId();

      if (equipo != null) {
        // Obtener el jugador por nombre y perteneciente al equipo
        Jugadores jugador = findJugadorByNombreYEquipo(nombreJugador, equipo);

        if (jugador != null) {
          // Actualizar el nombre del jugador
          jugador.setNombrejugador(nuevoNombre);

          // Guardar los cambios en la base de datos
          session.update(jugador);
          transaction.commit();
          JOptionPane.showMessageDialog(null, "Jugador actualizado");
        } else {
          JOptionPane.showMessageDialog(null, "Jugador no encontrado en el equipo especificado.");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
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
     * Metodo para buscar jugador de un equipo
     * @param nombreJugador nombre jugador
     * @param equipo el equipo en el quye buscar
     * @return devuelve el jugador
     */
  private Jugadores findJugadorByNombreYEquipo(String nombreJugador, Equipo equipo) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Jugadores> criteria = builder.createQuery(Jugadores.class);
      Root<Jugadores> root = criteria.from(Jugadores.class);

      criteria.select(root)
        .where(builder.equal(root.get("nombrejugador"), nombreJugador),
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

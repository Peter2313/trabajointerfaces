/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Equipo;
import com.mycompany.Modelo.Usuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperinscriequipo {

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
    }finally {
      session.close();
    }
  }

  
  public static Usuarios findById(int id) {
      System.out.println(id+ "metodo find");
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

  public void inscribirEquipo(String nombreEquipo, String ciudad, String estadio) {
    // Obtener el idUsuario utilizando el método anterior
    Usuarios usuario = findById(obtenerIdUsuarioPorNombre());
      //System.out.println(idusuario +"metodo inscri");
    
      Transaction transaction = null;
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
      try {
          if (obtenerIdUsuarioPorNombre() != -1) {
        // Comenzar transacción
        transaction = session.beginTransaction();

        // Crear un objeto Equipo con los parámetros proporcionados
        Equipo equipo = new Equipo();
        equipo.setUsuarios(usuario);
        equipo.setNombreEquipo(nombreEquipo);
        equipo.setCiudad(ciudad);
        equipo.setEstadio(estadio);

        // Guardar el equipo en la base de datos
        session.save(equipo);

        // Commit de la transacción
        transaction.commit();
        } else {
        System.out.println("Usuario no encontrado");
        }
      } catch (Exception e) {
        if (transaction != null) {
          // Rollback en caso de error
          transaction.rollback();
        }
        e.printStackTrace();
      }finally {
      session.close();
    }
    
  }
}

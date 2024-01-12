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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperinscriequipo {

  String nombreArchivo = "src/main/resources/nombreusuario.txt";
  String contenidoLeido = leerDesdeArchivo(nombreArchivo);

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
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // Consulta HQL para obtener el idUsuario por nombre de usuario
      String hql = "SELECT u.id FROM Usuarios u WHERE usuario = :nombreusuario";
      Query<Integer> query = session.createQuery(hql, Integer.class);
      query.setParameter("nombreusuario", contenidoLeido);

      // Obtener el resultado de la consulta
      Integer idUsuario = query.uniqueResult();
      System.out.println(idUsuario);
      return (idUsuario != null) ? idUsuario : -1; // Devolver -1 si no se encuentra el usuario
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /*public void inscribirEquipo(String nombreEquipo, String ciudad, String estadio) {
    // Obtener el idUsuario utilizando el método anterior

    if (idUsuario != -1) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        // Comenzar transacción
        transaction = session.beginTransaction();

        // Crear un objeto Equipo con los parámetros proporcionados
        Equipo equipo = new Equipo();
        //equipo.setUsuarios(idUsuario);
        equipo.setNombreEquipo(nombreEquipo);
        equipo.setCiudad(ciudad);
        equipo.setEstadio(estadio);

        // Guardar el equipo en la base de datos
        session.save(equipo);

        // Commit de la transacción
        transaction.commit();
      } catch (Exception e) {
        if (transaction != null) {
          // Rollback en caso de error
          transaction.rollback();
        }
        e.printStackTrace();
      }
    } else {
      System.out.println("Usuario no encontrado");
    }
  }*/
}

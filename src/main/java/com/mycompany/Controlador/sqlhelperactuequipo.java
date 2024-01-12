/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Equipo;
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
public class sqlhelperactuequipo {

  private String nombreArchivo = "src/main/resources/nombreusuario.txt";
  private String contenidoLeido = leerDesdeArchivo(nombreArchivo);
  private int idequipo = buscarEquipoPorIdUsuario(contenidoLeido);

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

  public static int buscarEquipoPorIdUsuario(String idUsuario) {
    int idEquipo = 0; // Valor predeterminado en caso de que no se encuentre ningún equipo
    Session session = null;

    try {

      // Crear la consulta para obtener el id del equipo asociado al usuario
      String hql = "SELECT e.id FROM Equipo e WHERE e.propietario.id = :idUsuario";
      Query<Integer> query = session.createQuery(hql, Integer.class);
      query.setParameter("idUsuario", idUsuario);

      // Obtener el resultado de la consulta
      Integer result = query.uniqueResult();
      if (result != null) {
        idEquipo = result;
      }

    } catch (Exception e) {
      // Manejar cualquier excepción
      e.printStackTrace();
    } finally {
      // Cerrar la sesión de Hibernate
      if (session != null) {
        session.close();
      }
    }

    return idEquipo;
  }

  public static void actualizarEquipo(String nuevoNombre, String nuevoEstadio) {
    Session session = null;
    Transaction transaction = null;

    try {

      // Comenzar la transacción
      transaction = session.beginTransaction();

      // Obtener el equipo que quieres actualizar
      Equipo equipo = session.get(Equipo.class, equipoId);

      // Actualizar el nombre y el estadio
      equipo.setNombreEquipo(nuevoNombre);
      equipo.setEstadio(nuevoEstadio);

      // Guardar los cambios en la base de datos
      session.update(equipo);

      // Commit la transacción
      transaction.commit();

    } catch (Exception e) {
      // Manejar cualquier excepción
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      // Cerrar la sesión de Hibernate
      if (session != null) {
        session.close();
      }
    }
  }
}

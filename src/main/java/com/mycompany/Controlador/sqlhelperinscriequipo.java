/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Equipo;
import com.mycompany.Modelo.Usuarios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperinscriequipo {

  public int obtenerIdUsuarioPorNombre(Usuarios usuario) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // Consulta HQL para obtener el idUsuario por nombre de usuario
      String hql = "SELECT u.idUsuario FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario";
      Query<Integer> query = session.createQuery(hql, Integer.class);
      query.setParameter("usuario", usuario);

      // Obtener el resultado de la consulta
      Integer idUsuario = query.uniqueResult();

      return (idUsuario != null) ? idUsuario : -1; // Devolver -1 si no se encuentra el usuario
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public void inscribirEquipo(String nombreEquipo, String ciudad, String estadio, Usuarios usuario) {
    // Obtener el idUsuario utilizando el método anterior
    int idUsuario = obtenerIdUsuarioPorNombre(usuario);

    if (idUsuario != -1) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        // Comenzar transacción
        transaction = session.beginTransaction();

        // Crear un objeto Equipo con los parámetros proporcionados
        Equipo equipo = new Equipo();
        equipo.setUsuarios(idUsuario);
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
  }
}

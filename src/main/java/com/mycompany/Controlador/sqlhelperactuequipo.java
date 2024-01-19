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
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelperactuequipo {
    private String nombreArchivo = "src/main/resources/nombreusuario.txt";
  private String contenidoLeido = leerDesdeArchivo(nombreArchivo);
  private int idusuario;
//metodo para recoger lo del archivo txt
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
//metodo para obtener id de usuario
 
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
    }/*finally {
      session.close();
    }*/
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

 
 /* public static int buscarEquipoPorIdUsuario(String idUsuario) {
    int idEquipo = 0; // Valor predeterminado en caso de que no se encuentre ningún equipo
    Session session = null;

    try {

      // Crear la consulta para obtener el id del equipo asociado al usuario
      String hql = "SELECT e.id FROM Equipo e WHERE e.propietario.id = :idUsuario";
      Query<Integer> query = session.createQuery(hql, Equipo.class);
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
  }*/

public void actualizarEquipo(String nuevoNombre, String nuevoEstadio) {
     Transaction transaction = null;
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

    try {
        // Comenzar la transacción
        
        transaction = session.beginTransaction();

        // Obtener el usuario por nombre
        Usuarios usuario = findById(obtenerIdUsuarioPorNombre());

        if (usuario != null) {
            // Obtener la colección de equipos asociados al usuario
            Set<Equipo> equipos = usuario.getEquipos();

            if (!equipos.isEmpty()) {
                // Tomar el primer equipo de la colección (puedes adaptarlo según tu lógica específica)
                Equipo equipo = equipos.iterator().next();

                // Actualizar el nombre y el estadio
                equipo.setNombreEquipo(nuevoNombre);
                equipo.setEstadio(nuevoEstadio);

                // Guardar los cambios en la base de datos
                session.update(equipo);

                // Commit la transacción
                transaction.commit();
            } else {
                System.out.println("No se encontraron equipos asociados al usuario.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    } catch (Exception e) {
        // Manejar cualquier excepción
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        // Cerrar la sesión de Hibernate
        /*if (session != null) {
            session.close();
        }*/
    }
}
}

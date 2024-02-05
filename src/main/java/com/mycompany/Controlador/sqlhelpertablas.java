/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Modelo.Equipo;
import com.mycompany.Modelo.Jugadores;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Pedro Garcia Vicente
 */
public class sqlhelpertablas {
/**
 * Metodo para meter en una tabla los equipos y entrenadores mediante relacion
 * @return devuelve una lista de los equipos
 */
  public static List<Equipo> selectEquipos() {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    String hql = "FROM Equipo";
    Query query = session.createQuery(hql, Equipo.class);
    return query.list();
  }
  
  /**
 * Metodo para meter en una tabla los jugadores de los equipos
 * @return devuelve una lista de los Jugadores
 */
  public static List<Jugadores> selectjugadores() {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    String hql = "FROM Jugadores";
    Query query = session.createQuery(hql, Jugadores.class);
    return query.list();
  }
}

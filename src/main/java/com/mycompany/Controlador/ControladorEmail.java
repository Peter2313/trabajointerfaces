/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.Controlador.HibernateUtil;
import com.mycompany.Modelo.Usuarios;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ACER
 */
public class ControladorEmail {
   public boolean verificarEmail(String email) {
        boolean emailExiste = false;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sesion = sessionFactory.openSession();
        Transaction tx = sesion.beginTransaction();

        try {
            // Crear la consulta HQL
            String hql = "FROM Usuarios WHERE email = :email";
            Query<Usuarios> query = sesion.createQuery(hql, Usuarios.class);
            query.setParameter("email", email);

            // Obtener el resultado de la consulta
            Usuarios usuario = query.uniqueResult();

            // Verificar si se encontró un usuario con las credenciales proporcionadas
            if (usuario != null) {
                emailExiste = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            sesion.close();
        }

        return emailExiste;
    }
}

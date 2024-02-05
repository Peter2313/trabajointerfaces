package com.mycompany.Modelo;

import com.mycompany.Controlador.HibernateUtil;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @ToString @RequiredArgsConstructor
@Table(name = "usuarios")
public class Usuarios implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "usuario", length = 15, nullable = false, unique = true)
  private String usuario;

  @Column(name = "contrasena", length = 255, nullable = false, unique = true)
  private String contrasena;

  @Column(name = "email", length = 50, nullable = false)
  private String email;

  @Column(name = "nombre", length = 100, nullable = false)
  private String nombre;

  @Column(name = "apellidos", length = 100, nullable = false)
  private String apellidos;

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true) @ToString.Exclude
  private Set<Equipo> equipos = new HashSet<>();

  public Usuarios(String nombre, String apellidos) {
    this.nombre = nombre;
    this.apellidos = apellidos;
  }

  public Usuarios(String usuario, String contrasena, String email, String nombre) {
    this.usuario = usuario;
    this.contrasena = contrasena;
    this.email = email;
    this.nombre = nombre;
  }

  /**
   * Metodo para buscar el equipo
   * @param usuario
   * @return 
   */
  public static Equipo findByEquipo(Usuarios usuario) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Equipo equipo = session.createQuery("from Equipo e WHERE e.usuarios.id = :idusuario ", Equipo.class).setParameter("idusuario", usuario.getId()).getSingleResult();
    session.close();
    return equipo;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Usuarios usuarios = (Usuarios) o;
    return getId() != null && Objects.equals(getId(), usuarios.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}

package com.mycompany.Modelo;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @ToString @RequiredArgsConstructor
@Table(name = "equipo")
public class Equipo implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_equipo")
  private Integer idEquipo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false,unique = true) @ToString.Exclude
  private Usuarios usuarios;

  @Column(name = "nombre_equipo", length = 50, nullable = false, unique = true)
  private String nombreEquipo;

  @Column(name = "ciudad", length = 50, nullable = false)
  private String ciudad;

  @Column(name = "estadio", length = 50, nullable = false)
  private String estadio;

  @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true) @ToString.Exclude
  private Set<Jugadores> jugadoreses = new HashSet<>();

  @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @ToString.Exclude
  private Set<Entrenadores> entrenadoreses = new HashSet<>();

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Equipo equipo = (Equipo) o;
    return getIdEquipo() != null && Objects.equals(getIdEquipo(), equipo.getIdEquipo());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}

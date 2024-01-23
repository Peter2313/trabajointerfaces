package com.mycompany.Modelo;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @ToString @RequiredArgsConstructor
@Table(name = "jugadores")
public class Jugadores implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idjugador")
  private Integer idjugador;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idequipo", nullable = false) @ToString.Exclude
  private Equipo equipo;

  @Column(name = "nombrejugador", length = 255, nullable = false)
  private String nombrejugador;

  @Column(name = "dorsal", nullable = false)
  private int dorsal;

  // Constructors, getters, and setters

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Jugadores jugadores = (Jugadores) o;
    return getIdjugador() != null && Objects.equals(getIdjugador(), jugadores.getIdjugador());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}

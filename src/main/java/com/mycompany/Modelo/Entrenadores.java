package com.mycompany.Modelo;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @ToString
@RequiredArgsConstructor
@Table(name = "entrenadores")
public class Entrenadores implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idequipo", nullable = false, unique = true) @ToString.Exclude
  private Equipo equipo;

  @Column(name = "nombreCompletoentrenador", nullable = false)
  private String nombreCompletoentrenador;

  @Column(name = "cLocalColor", nullable = false)
  private String clocalColor;

  @Column(name = "cvisitantecolor", nullable = false)
  private String cvisitanteColor;

  public Entrenadores(Equipo equipo, String nombreCompletoentrenador, String clocalColor, String cvisitanteColor) {
    this.equipo = equipo;
    this.nombreCompletoentrenador = nombreCompletoentrenador;
    this.clocalColor = clocalColor;
    this.cvisitanteColor = cvisitanteColor;
  }

  // Getters and setters

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Entrenadores that = (Entrenadores) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}

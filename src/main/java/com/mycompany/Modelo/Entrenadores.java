package com.mycompany.Modelo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "entrenadores")
public class Entrenadores implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idequipo", nullable = false, unique = true)
  private Equipo equipo;

  @Column(name = "nombreCompletoentrenador", nullable = false)
  private String nombreCompletoentrenador;

  @Column(name = "cLocalColor", nullable = false)
  private String clocalColor;

  @Column(name = "cvisitantecolor", nullable = false)
  private String cvisitanteColor;

  public Entrenadores() {
  }

  public Entrenadores(Equipo equipo, String nombreCompletoentrenador, String clocalColor, String cvisitanteColor) {
    this.equipo = equipo;
    this.nombreCompletoentrenador = nombreCompletoentrenador;
    this.clocalColor = clocalColor;
    this.cvisitanteColor = cvisitanteColor;
  }

  // Getters and setters
}

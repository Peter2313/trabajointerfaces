package com.mycompany.Modelo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jugadores")
public class Jugadores implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idjugador")
  private Integer idjugador;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idequipo", nullable = false, unique = true)
  private Equipo equipo;

  @Column(name = "nombrejugador", length = 255, nullable = false)
  private String nombrejugador;

  @Column(name = "dorsal", nullable = false)
  private int dorsal;

  // Constructors, getters, and setters
}

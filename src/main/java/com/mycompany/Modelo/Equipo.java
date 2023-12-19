package com.mycompany.Modelo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "equipo")
public class Equipo implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_equipo")
  private Integer idEquipo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuarios usuarios;

  @Column(name = "nombre_equipo", length = 50, nullable = false, unique = true)
  private String nombreEquipo;

  @Column(name = "ciudad", length = 50, nullable = false)
  private String ciudad;

  @Column(name = "estadio", length = 50, nullable = false)
  private String estadio;

  @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Jugadores> jugadoreses = new HashSet<>();

  @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Entrenadores> entrenadoreses = new HashSet<>();

}

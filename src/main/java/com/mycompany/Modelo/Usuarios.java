package com.mycompany.Modelo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data

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

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Equipo> equipos = new HashSet<>();


  public Usuarios() {
  }

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
}

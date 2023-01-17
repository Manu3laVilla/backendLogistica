package com.example.backendlogistica.security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUsuario;

    @NotNull
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @NotNull
    @Column(name = "user_name", unique = true)
    private String userName;

    @NotNull
    @Column(name = "correo_usuario")
    private String correoUsuario;

    @NotNull
    @Column(name = "pass_usuario")
    private String passUsuario;

    @Column(name = "id_rol_usuario")
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> idRolUsuario = new HashSet<>();

    public Usuario(){}
    public Usuario(@NotNull String nombreUsuario, @NotNull String userName,
                   @NotNull String correoUsuario, @NotNull String passUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.userName = userName;
        this.correoUsuario = correoUsuario;
        this.passUsuario = passUsuario;
    }
}

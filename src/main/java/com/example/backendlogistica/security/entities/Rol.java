package com.example.backendlogistica.security.entities;

import com.example.backendlogistica.security.enums.RolNombre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="rol")
public class Rol {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idRol;

    @NotNull
    @Column(name = "tipo_rol")
    @Enumerated(EnumType.STRING)
    private RolNombre tipoRol;

    public Rol(){}

    public Rol(@NotNull RolNombre tipoRol) {
        this.tipoRol = tipoRol;
    }
}

package com.example.backendlogistica.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuarioDTO {

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String userName;

    @Email
    private String correoUsuario;

    @NotBlank
    private String passUsuario;

    private Set<String> idRolUsuario = new HashSet<>();
}

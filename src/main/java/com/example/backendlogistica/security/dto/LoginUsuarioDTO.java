package com.example.backendlogistica.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuarioDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String passUsuario;

}

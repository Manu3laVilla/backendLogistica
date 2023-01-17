package com.example.backendlogistica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ClienteRequest implements Serializable {

    @JsonProperty("identificacion")
    private int identificacion;

    @JsonProperty("nombre_cliente")
    private String nombreCliente;

    @JsonProperty("apellido_cliente")
    private String apellidoCliente;

    @JsonProperty("direccion_cliente")
    private String direccionCliente;

    @JsonProperty("correo_cliente")
    private String correoCliente;

    @JsonProperty("telefono_cliente")
    private String telefonoCliente;
}

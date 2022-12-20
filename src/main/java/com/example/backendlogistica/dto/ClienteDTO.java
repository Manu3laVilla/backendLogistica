package com.example.backendlogistica.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteDTO implements Serializable {

    private int id;
    private int identificacion;
    private String nombreCliente;
    private String apellidoCliente;
    private String direccionCliente;
    private String correoCliente;
    private String telefonoCliente;

}

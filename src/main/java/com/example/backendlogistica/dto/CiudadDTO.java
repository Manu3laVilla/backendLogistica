package com.example.backendlogistica.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CiudadDTO implements Serializable {

    private int id;
    private String nombreCiudad;

}

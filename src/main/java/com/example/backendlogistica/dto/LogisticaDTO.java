package com.example.backendlogistica.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogisticaDTO implements Serializable {

    private int idLogistica;
    private String tipoLogistica;
    private int precioEnvio;

}

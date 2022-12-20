package com.example.backendlogistica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="logistica")
public class Logistica implements Serializable {

    @Id
    @Column(name = "id_logistica")
    private int idLogistica;

    @Column(name = "tipo_logistica")
    private String tipoLogistica;

    @Column(name = "precio_envio")
    private int precioEnvio;

}
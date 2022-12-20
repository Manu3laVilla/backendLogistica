package com.example.backendlogistica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="ciudad")
public class Ciudad implements Serializable {

    @Id
    @Column(name = "id_ciudad")
    private int id;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;

}


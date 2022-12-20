package com.example.backendlogistica.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="vehiculo")
public class Vehiculo implements Serializable {

    @Id
    @Column(name = "id_vehiculo")
    private int id;

    @Column(name = "placa_vehiculo")
    private String placaVehiculo;

    @ManyToOne
    @JoinColumn(name="id_logistica_vehiculo")
    private Logistica idLogisticaVehiculo;

}

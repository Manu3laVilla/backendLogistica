package com.example.backendlogistica.dto;

import com.example.backendlogistica.entities.Logistica;
import lombok.Data;

import java.io.Serializable;

@Data
public class VehiculoDTO implements Serializable {

    private int id;
    private String placaVehiculo;
    private Logistica idLogisticaVehiculo;

}

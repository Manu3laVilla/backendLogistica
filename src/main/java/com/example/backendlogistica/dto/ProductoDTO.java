package com.example.backendlogistica.dto;

import com.example.backendlogistica.entities.Logistica;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductoDTO implements Serializable {

    private int id;
    private String nombreProducto;
    private Logistica idLogisticaProducto;

}

package com.example.backendlogistica.dto;

import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.entities.Logistica;
import lombok.Data;

import java.io.Serializable;

@Data
public class AlmacenDTO implements Serializable {

    private int idAlmacen;
    private String nombreAlmacen;
    private Logistica idLogisticaAlmacen;
    private Ciudad idCiudadAlmacen;

}

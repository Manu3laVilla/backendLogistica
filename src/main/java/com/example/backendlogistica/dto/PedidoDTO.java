package com.example.backendlogistica.dto;

import com.example.backendlogistica.entities.Cliente;
import com.example.backendlogistica.entities.Producto;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.entities.Vehiculo;
import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.entities.Almacen;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PedidoDTO implements Serializable {

    private int idPlan;
    private Cliente idCliente;
    private Producto idProducto;
    private Logistica idLogistica;
    private Vehiculo idVehiculo;
    private Ciudad idCiudad;
    private Almacen idCentro;
    private int cantidad;
    private int costoEnvio;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private String guia;

}

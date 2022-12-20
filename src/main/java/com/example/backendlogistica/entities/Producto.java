package com.example.backendlogistica.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {

    @Id
    @Column(name = "id_producto")
    private int id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @ManyToOne
    @JoinColumn(name="id_logistica_producto")
    private Logistica idLogisticaProducto;

}
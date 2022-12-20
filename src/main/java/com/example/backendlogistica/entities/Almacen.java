package com.example.backendlogistica.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="almacen")
public class Almacen implements Serializable {

    @Id
    @Column(name = "id_almacen")
    private int idAlmacen;

    @Column(name = "nombre_almacen")
    private String nombreAlmacen;

    @ManyToOne
    @JoinColumn(name="id_logistica_almacen")
    private Logistica idLogisticaAlmacen;

    @ManyToOne
    @JoinColumn(name="id_ciudad_almacen")
    private Ciudad idCiudadAlmacen;

}
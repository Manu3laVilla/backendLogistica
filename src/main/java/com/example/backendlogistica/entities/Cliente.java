package com.example.backendlogistica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente")
    private int id;

    @Column(name = "identificacion")
    private int identificacion;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "apellido_cliente")
    private String apellidoCliente;

    @Column(name = "direccion_cliente")
    private String direccionCliente;

    @Column(name = "correo_cliente")
    private String correoCliente;

    @Column(name = "telefono_cliente")
    private String telefonoCliente;

}

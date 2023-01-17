package com.example.backendlogistica.entities;

import com.example.backendlogistica.utils.generator.Generator;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

    @Id
    @Column(name = "id_plan")
    private int idPlan;

    @ManyToOne
    @JoinColumn(name = "id_cliente_entrega")
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_producto_entrega")
    private Producto idProducto;

    @ManyToOne
    @JoinColumn(name = "id_logistica_entrega")
    private Logistica idLogistica;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo_entrega")
    private Vehiculo idVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_ciudad_entrega")
    private Ciudad idCiudad;

    @ManyToOne
    @JoinColumn(name = "id_almacen_entrega")
    private Almacen idCentro;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_envio")
    private int costoEnvio;

    @Column(name = "precio_pagar")
    private int costoPagar;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "guia")
    private String guia;

    @PrePersist
    public void prePersist() {

        Generator generator = new Generator();

        this.fechaRegistro = new Date();
        this.fechaEntrega = generator.dateEntrega(this.fechaRegistro);
        this.guia = generator.generateGuia();
        int id = this.idLogistica.getIdLogistica();
        this.costoPagar = generator.getCostoPagar(id, this.cantidad);
        this.costoEnvio = generator.getCosto(id);
    }

}

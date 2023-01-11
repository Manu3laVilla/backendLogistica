package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class PedidoRepositoryTest {

    @Mock
    private PedidoRepository pedidoRepository;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pedido = new Pedido();
        pedido.setIdPlan(1);
        pedido.setIdLogistica(new Logistica());
        pedido.setIdCentro(new Almacen());
        pedido.setIdCiudad(new Ciudad());
        pedido.setIdCliente(new Cliente());
        pedido.setIdVehiculo(new Vehiculo());
        pedido.setIdProducto(new Producto());
        pedido.setCantidad(10);
        pedido.setCostoEnvio(25000);
        pedido.setCostoPagar(25000);
        pedido.setGuia("guia1234da");
        pedido.setFechaRegistro(new Date());
        pedido.setFechaEntrega(new Date());

    }

    @Test
    void findByGuia() {
        Iterable<Pedido> pedidos = pedidoRepository.findByGuia(pedido.getGuia());

        assertThat(pedidos).isNotNull();
    }

    @Test
    void existsByGuia() {
        given(pedidoRepository.existsByGuia(anyString())).willReturn(true);

        boolean response = pedidoRepository.existsByGuia(pedido.getGuia());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(true);
    }
}
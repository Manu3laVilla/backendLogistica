package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.PedidoDTO;
import com.example.backendlogistica.entities.*;
import com.example.backendlogistica.repository.PedidoRepository;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PedidoImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoImpl pedidoService;

    private Pedido pedido, pedido_1;

    private Logistica logistica;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        logistica = new Logistica();
        logistica.setIdLogistica(1);
        logistica.setTipoLogistica("Marítima");
        logistica.setPrecioEnvio(50000);

        pedido = new Pedido();
        pedido.setIdPlan(1);
        pedido.setIdLogistica(logistica);
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

        pedido_1 = new Pedido();
        pedido_1.setIdPlan(1);
        pedido_1.setIdLogistica(new Logistica());
        pedido_1.setIdCentro(new Almacen());
        pedido_1.setIdCiudad(new Ciudad());
        pedido_1.setIdCliente(new Cliente());
        pedido_1.setIdVehiculo(new Vehiculo());
        pedido_1.setIdProducto(new Producto());
        pedido_1.setCantidad(12);
        pedido_1.setCostoEnvio(50000);
        pedido_1.setCostoPagar(48000);
        pedido_1.setGuia("guia456da");
        pedido_1.setFechaRegistro(new Date());
        pedido_1.setFechaEntrega(new Date());
    }

    @Test
    void findAll() {
        given(pedidoRepository.findAll()).willReturn(List.of(pedido_1, pedido));

        List<PedidoDTO> pedidoDTOS = pedidoService.findAll();

        assertThat(pedidoDTOS).isNotNull();
        assertThat(pedidoDTOS.size()).isEqualTo(2);
    }

    @Test
    void findByGuia() {
        given(pedidoRepository.findByGuia(anyString())).willReturn(List.of(pedido));

        List<PedidoDTO> pedidoDTOS = pedidoService.findByGuia(pedido.getGuia());

        assertThat(pedidoDTOS).isNotNull();
        assertThat(pedidoDTOS.get(0).getGuia()).isEqualTo("guia1234da");
        assertThat(pedidoDTOS.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        given(pedidoRepository.findById(anyInt())).willReturn(Optional.of(pedido));

        PedidoDTO pedidoDTO = pedidoService.findById(pedido.getIdPlan());

        assertThat(pedidoDTO).isNotNull();
        assertThat(pedidoDTO.getGuia()).isEqualTo("guia1234da");
    }

    @Test
    void save() {
        given(pedidoRepository.save(pedido)).willReturn(pedido);
        given(pedidoRepository.save(pedido_1)).willReturn(pedido_1);

        PedidoDTO pedido = MHelpers.modelMapper().map(this.pedido, PedidoDTO.class);
        PedidoDTO pedido_1 = MHelpers.modelMapper().map(this.pedido_1, PedidoDTO.class);
        pedidoService.save(pedido);
        pedidoService.save(pedido_1);

        assertThat(pedidoService.findAll()).isNotNull();
    }

    @Test
    void deleteById() {
        willDoNothing().given(pedidoRepository).deleteById(1);

        pedidoService.deleteById(1);

        verify(pedidoRepository, times(1)).deleteById(1);
    }

    @Test
    void existsByGuia() {
        given(pedidoRepository.existsByGuia(anyString())).willReturn(true);

        assertThat(pedidoService.existsByGuia(pedido.getGuia())).isTrue();
    }
}
package com.example.backendlogistica.services.implementation;

import ch.qos.logback.core.model.Model;
import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.ClienteDTO;
import com.example.backendlogistica.dto.ClienteRequest;
import com.example.backendlogistica.entities.Cliente;
import com.example.backendlogistica.repository.ClienteRepository;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ClienteImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteImpl clienteService;

    private Cliente cliente;

    private ClienteRequest clienteRequest, clienteRequest_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setIdentificacion(123456789);
        cliente.setNombreCliente("Nombre Prueba");
        cliente.setApellidoCliente("Apellido Prueba");
        cliente.setTelefonoCliente("1234567");
        cliente.setCorreoCliente("correo@prueba.com");
        cliente.setDireccionCliente("Dirección #Prueba");

        clienteRequest = ClienteRequest.builder()
                .identificacion(123456789)
                .nombreCliente("Nombre Prueba")
                .apellidoCliente("Apellido Prueba")
                .telefonoCliente("1234567")
                .correoCliente("correo@prueba.com")
                .direccionCliente("Dirección # Prueba")
                .build();

        clienteRequest_1 = ClienteRequest.builder()
                .identificacion(987654321)
                .nombreCliente("Nombre Prueba")
                .apellidoCliente("Apellido Prueba")
                .telefonoCliente("9876543")
                .correoCliente("correo_1@prueba.com")
                .direccionCliente("Dirección # Prueba")
                .build();
    }

    @Test
    void findAll() {

        given(clienteRepository.findAll()).willReturn(List.of(cliente));

        List<ClienteDTO> clienteDTOS = clienteService.findAll();

        assertThat(clienteDTOS).isNotNull();
        assertThat(clienteDTOS.size()).isEqualTo(1);

    }

    @Test
    void findByIdentificacion() {
        given(clienteRepository.findByIdentificacion(cliente.getIdentificacion()))
                .willReturn(List.of(cliente));

        List<ClienteDTO> clienteDTOS = clienteService.findByIdentificacion(cliente.getIdentificacion());

        assertThat(clienteDTOS).isNotNull();
        assertThat(clienteDTOS.get(0).getIdentificacion()).isEqualTo(123456789);
    }

    @Test
    void findById() {
        given(clienteRepository.findById(cliente.getId())).willReturn(Optional.of(cliente));

        ClienteDTO clienteDTO = clienteService.findById(cliente.getId());

        assertThat(clienteDTO).isNotNull();
        assertThat(clienteDTO.getId()).isEqualTo(1);
    }

    @Test
    void save() {
        Cliente clienteRequest = MHelpers.modelMapper().map(this.clienteRequest, Cliente.class);
        Cliente clienteRequest_1 = MHelpers.modelMapper().map(this.clienteRequest_1, Cliente.class);

        given(clienteRepository.save(clienteRequest)).willReturn(clienteRequest);
        given(clienteRepository.save(clienteRequest_1)).willReturn(clienteRequest_1);

        clienteService.save(this.clienteRequest);
        clienteService.save(this.clienteRequest_1);

        assertThat(clienteService.findAll()).isNotNull();
    }

    @Test
    void deleteById() {
        willDoNothing().given(clienteRepository).deleteById(1);

        clienteService.deleteById(1);

        verify(clienteRepository, times(1)).deleteById(1);
    }

    @Test
    void existsByIdentificacion() {
        given(clienteRepository.existsByIdentificacion(anyInt())).willReturn(true);

        assertThat(clienteService.existsByIdentificacion(cliente.getIdentificacion())).isTrue();
    }

    @Test
    void existsByCorreoCliente() {
        given(clienteRepository.existsByCorreoCliente(anyString())).willReturn(true);

        assertThat(clienteService.existsByCorreoCliente(cliente.getCorreoCliente())).isTrue();
    }
}
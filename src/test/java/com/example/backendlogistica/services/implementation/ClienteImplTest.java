package com.example.backendlogistica.services.implementation;

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
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ClienteImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteImpl clienteService;

    private Cliente cliente;

    private ClienteRequest clienteRequest;

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
        clienteService.save(clienteRequest);

        ClienteDTO clienteDTO = clienteService.findById(1);

        assertThat(clienteDTO).isNotNull();
    }

    @Test
    void update() {
        given(clienteRepository.save(cliente)).willReturn(cliente);

        clienteRequest = MHelpers.modelMapper().map(cliente, ClienteRequest.class);

        clienteService.save(clienteRequest);

        ClienteDTO clienteDTO = clienteService.findById(cliente.getId());

        assertThat(clienteService.findAll()).isNotNull();
    }

    @Test
    void deleteById() {
    }

    @Test
    void existsByIdentificacion() {
    }

    @Test
    void existsByCorreoCliente() {
    }
}
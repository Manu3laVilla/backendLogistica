package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Cliente;
import com.example.backendlogistica.services.implementation.ClienteImpl;
import com.example.backendlogistica.services.interfaces.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ClienteRepositoryTest {

    @Mock
    ClienteRepository clienteRepository;

    private Cliente cliente;

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

        clienteRepository.save(cliente);
    }

    @Test
    void findByIdentificacion() {

        Iterable<Cliente> cliente1 = clienteRepository.findByIdentificacion(cliente.getIdentificacion());

        assertThat(cliente1).isNotNull();
    }

    @Test
    void existsByIdentificacion() {

        given(clienteRepository.existsByIdentificacion(cliente.getIdentificacion()))
                .willReturn(true);

        assertThat(clienteRepository.existsByIdentificacion(cliente.getIdentificacion())).isEqualTo(true);
    }

    @Test
    void existsByCorreoCliente() {

        given(clienteRepository.existsByCorreoCliente(cliente.getCorreoCliente()))
                .willReturn(true);

        assertThat(clienteRepository.existsByCorreoCliente(cliente.getCorreoCliente())).isEqualTo(true);
    }
}
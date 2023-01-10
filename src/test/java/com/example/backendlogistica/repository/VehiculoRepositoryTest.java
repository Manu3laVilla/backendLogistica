package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.entities.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class VehiculoRepositoryTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    private Vehiculo vehiculo, vehiculo_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setIdLogisticaVehiculo(new Logistica());
        vehiculo.setPlacaVehiculo("XXX123");

        vehiculo_1 = new Vehiculo();
        vehiculo_1.setId(2);
        vehiculo_1.setIdLogisticaVehiculo(new Logistica());
        vehiculo_1.setPlacaVehiculo("XXX1234X");
    }

    @Test
    void findByPlacaVehiculo() {
        Iterable<Vehiculo> vehiculos = vehiculoRepository.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());

        assertThat(vehiculos).isNotNull();
    }

    @Test
    void existsByPlacaVehiculo() {
        given(vehiculoRepository.existsByPlacaVehiculo(anyString())).willReturn(true);

        boolean response = vehiculoRepository.existsByPlacaVehiculo(vehiculo_1.getPlacaVehiculo());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(true);
    }
}
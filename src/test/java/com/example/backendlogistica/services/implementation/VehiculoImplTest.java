package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.ProductoDTO;
import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.entities.Vehiculo;
import com.example.backendlogistica.repository.VehiculoRepository;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

class VehiculoImplTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private VehiculoImpl vehiculoService;

    private Vehiculo vehiculo, vehiculo_1;

    private Logistica logistica;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        logistica = new Logistica();
        logistica.setIdLogistica(1);
        logistica.setTipoLogistica("Marítima");
        logistica.setPrecioEnvio(50000);

        vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setIdLogisticaVehiculo(logistica);
        vehiculo.setPlacaVehiculo("XXX123");

        vehiculo_1 = new Vehiculo();
        vehiculo_1.setId(2);
        vehiculo_1.setIdLogisticaVehiculo(new Logistica());
        vehiculo_1.setPlacaVehiculo("XXX1234X");
    }

    @Test
    void findAll() {
        given(vehiculoRepository.findAll()).willReturn(List.of(vehiculo_1, vehiculo));

        List<VehiculoDTO> vehiculoDTOS = vehiculoService.findAll();

        assertThat(vehiculoDTOS).isNotNull();
        assertThat(vehiculoDTOS.size()).isEqualTo(2);
    }

    @Test
    void findByPlacaVehiculo() {
        given(vehiculoRepository.findByPlacaVehiculo(anyString())).willReturn(List.of(vehiculo));

        List<VehiculoDTO> vehiculoDTOS = vehiculoService.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());

        assertThat(vehiculoDTOS).isNotNull();
        assertThat(vehiculoDTOS.get(0).getPlacaVehiculo()).isEqualTo("XXX123");
        assertThat(vehiculoDTOS.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        given(vehiculoRepository.findById(anyInt())).willReturn(Optional.of(vehiculo));

        VehiculoDTO vehiculoDTO = vehiculoService.findById(vehiculo.getId());

        assertThat(vehiculoDTO).isNotNull();
        assertThat(vehiculoDTO.getPlacaVehiculo()).isEqualTo("XXX123");
    }

    @Test
    void findAllByLogistica() {
        given(vehiculoRepository.findAll()).willReturn(List.of(vehiculo, vehiculo_1));

        List<VehiculoDTO> vehiculoDTOS = vehiculoService.findAllByLogistica(1);

        assertThat(vehiculoDTOS).isNotNull();
        assertThat(vehiculoDTOS.get(0).getPlacaVehiculo()).isEqualTo("XXX123");
    }

    @Test
    void save() {
        given(vehiculoRepository.save(vehiculo)).willReturn(vehiculo);
        given(vehiculoRepository.save(vehiculo_1)).willReturn(vehiculo_1);

        VehiculoDTO vehiculo = MHelpers.modelMapper().map(this.vehiculo, VehiculoDTO.class);
        VehiculoDTO vehiculo_1 = MHelpers.modelMapper().map(this.vehiculo_1, VehiculoDTO.class);
        vehiculoService.save(vehiculo);
        vehiculoService.save(vehiculo_1);

        assertThat(vehiculoService.findAll()).isNotNull();
    }

    @Test
    void update() {
        given(vehiculoRepository.findById(anyInt())).willReturn(Optional.of(vehiculo));
        given(vehiculoRepository.save(vehiculo)).willReturn(vehiculo);
        vehiculo.setPlacaVehiculo("AAA456");

        VehiculoDTO vehiculo = MHelpers.modelMapper().map(this.vehiculo, VehiculoDTO.class);
        vehiculoService.update(vehiculo, this.vehiculo.getId());

        assertThat(this.vehiculo.getPlacaVehiculo()).isEqualTo("AAA456");
    }

    @Test
    void deleteById() {
        willDoNothing().given(vehiculoRepository).deleteById(1);

        vehiculoService.deleteById(1);

        verify(vehiculoRepository, times(1)).deleteById(1);
    }

    @Test
    void existsByPlacaVehiculo() {
        given(vehiculoRepository.existsByPlacaVehiculo(anyString())).willReturn(true);

        assertThat(vehiculoService.existsByPlacaVehiculo(vehiculo.getPlacaVehiculo())).isTrue();
    }
}
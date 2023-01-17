package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.entities.Almacen;
import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.repository.AlmacenRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AlmacenImplTest {

    @Mock
    private AlmacenRepository almacenRepository;

    @InjectMocks
    private AlmacenImpl almacenService;

    private Almacen almacen, almacen_1;

    private Ciudad ciudad;

    private Logistica logistica;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        logistica = new Logistica();
        logistica.setIdLogistica(1);
        logistica.setTipoLogistica("Marítima");
        logistica.setPrecioEnvio(50000);

        ciudad = new Ciudad();
        ciudad.setId(1);
        ciudad.setNombreCiudad("Medellín");

        almacen = new Almacen();
        almacen.setIdAlmacen(1);
        almacen.setNombreAlmacen("Prueba Almacén");
        almacen.setIdLogisticaAlmacen(logistica);
        almacen.setIdCiudadAlmacen(ciudad);

        almacen_1 = new Almacen();
        almacen_1.setIdAlmacen(2);
        almacen_1.setNombreAlmacen("Prueba Almacén_1");
        almacen_1.setIdLogisticaAlmacen(new Logistica());
        almacen_1.setIdCiudadAlmacen(new Ciudad());
    }

    @Test
    void findAll() {
        given(almacenRepository.findAll()).willReturn(List.of(almacen_1, almacen));

        List<AlmacenDTO> almacenDTOS = almacenService.findAll();

        assertThat(almacenDTOS).isNotNull();
        assertThat(almacenDTOS.size()).isEqualTo(2);
    }

    @Test
    void findByNombreAlmacen() {
        given(almacenRepository.findByNombreAlmacen(anyString())).willReturn(List.of(almacen));

        List<AlmacenDTO> almacenDTOS = almacenService.findByNombreAlmacen(almacen.getNombreAlmacen());

        assertThat(almacenDTOS).isNotNull();
        assertThat(almacenDTOS.get(0).getNombreAlmacen()).isEqualTo("Prueba Almacén");
        assertThat(almacenDTOS.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        given(almacenRepository.findById(anyInt())).willReturn(Optional.of(almacen));

        AlmacenDTO almacenDTO = almacenService.findById(almacen.getIdAlmacen());

        assertThat(almacenDTO).isNotNull();
        assertThat(almacenDTO.getNombreAlmacen()).isEqualTo("Prueba Almacén");
    }

    @Test
    void findAllByLogisticaAndCiudad() {
        given(almacenRepository.findAll()).willReturn(List.of(almacen, almacen_1));

        List<AlmacenDTO> almacenDTOS = almacenService.findAllByLogisticaAndCiudad(1,1);

        assertThat(almacenDTOS).isNotNull();
        assertThat(almacenDTOS.get(0).getNombreAlmacen()).isEqualTo("Prueba Almacén");
    }

    @Test
    void save() {
        given(almacenRepository.save(almacen)).willReturn(almacen);
        given(almacenRepository.save(almacen_1)).willReturn(almacen_1);

        AlmacenDTO almacen = MHelpers.modelMapper().map(this.almacen, AlmacenDTO.class);
        AlmacenDTO almacen_1 = MHelpers.modelMapper().map(this.almacen_1, AlmacenDTO.class);
        almacenService.save(almacen);
        almacenService.save(almacen_1);

        assertThat(almacenService.findAll()).isNotNull();
    }

    @Test
    void update() {
        given(almacenRepository.findById(anyInt())).willReturn(Optional.of(almacen));
        given(almacenRepository.save(almacen)).willReturn(almacen);
        almacen.setNombreAlmacen("Almacén 1");

        AlmacenDTO almacen = MHelpers.modelMapper().map(this.almacen, AlmacenDTO.class);
        almacenService.update(almacen, this.almacen.getIdAlmacen());

        assertThat(this.almacen.getNombreAlmacen()).isEqualTo("Almacén 1");
    }

    @Test
    void deleteById() {
        willDoNothing().given(almacenRepository).deleteById(1);

        almacenService.deleteById(1);

        verify(almacenRepository, times(1)).deleteById(1);
    }

    @Test
    void existsByNombreAlmacen() {
        given(almacenRepository.existsByNombreAlmacen(anyString())).willReturn(true);

        assertThat(almacenService.existsByNombreAlmacen(almacen.getNombreAlmacen())).isTrue();
    }
}
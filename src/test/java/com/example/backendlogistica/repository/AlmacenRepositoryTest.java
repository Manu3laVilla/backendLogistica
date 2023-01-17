package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Almacen;
import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.entities.Logistica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class AlmacenRepositoryTest {

    @Mock
    private AlmacenRepository almacenRepository;

    private Almacen almacen, almacen_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        almacen = new Almacen();
        almacen.setIdAlmacen(1);
        almacen.setNombreAlmacen("Prueba Almacén");
        almacen.setIdLogisticaAlmacen(new Logistica());
        almacen.setIdCiudadAlmacen(new Ciudad());

        almacen_1 = new Almacen();
        almacen_1.setIdAlmacen(2);
        almacen_1.setNombreAlmacen("Prueba Almacén_1");
        almacen_1.setIdLogisticaAlmacen(new Logistica());
        almacen_1.setIdCiudadAlmacen(new Ciudad());
    }

    @Test
    void findByNombreAlmacen() {
        Iterable<Almacen> almacens = almacenRepository.findByNombreAlmacen(almacen_1.getNombreAlmacen());

        assertThat(almacens).isNotNull();
    }

    @Test
    void existsByNombreAlmacen() {
        given(almacenRepository.existsByNombreAlmacen(anyString())).willReturn(true);

        boolean response = almacenRepository.existsByNombreAlmacen(almacen_1.getNombreAlmacen());

        assertThat(response).isTrue();
    }
}
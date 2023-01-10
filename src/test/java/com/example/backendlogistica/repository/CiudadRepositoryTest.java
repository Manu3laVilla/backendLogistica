package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Ciudad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CiudadRepositoryTest {

    @Mock
    private CiudadRepository ciudadRepository;

    private Ciudad ciudad, ciudad_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ciudad = new Ciudad();
        ciudad.setId(1);
        ciudad.setNombreCiudad("Medellín");

        ciudad_1 = new Ciudad();
        ciudad_1.setId(2);
        ciudad_1.setNombreCiudad("Cali");
    }

    @Test
    void findById() {
        ciudadRepository.save(ciudad);
        ciudadRepository.save(ciudad_1);

        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudad_1.getId());

        assertThat(ciudadOptional).isNotNull();
    }
}
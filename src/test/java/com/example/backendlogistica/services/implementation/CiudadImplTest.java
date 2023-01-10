package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.CiudadDTO;
import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.repository.CiudadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class CiudadImplTest {

    @Mock
    private CiudadRepository ciudadRepository;

    @InjectMocks
    private CiudadImpl ciudadService;

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
    void findAll() {
        given(ciudadRepository.findAll()).willReturn(Arrays.asList(ciudad, ciudad_1));

        List<CiudadDTO> ciudadDTO = ciudadService.findAll();

        assertThat(ciudadDTO).isNotNull();
        assertThat(ciudadDTO.size()).isEqualTo(2);
    }
}
package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.CiudadDTO;
import com.example.backendlogistica.dto.LogisticaDTO;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.repository.LogisticaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class LogisticaImplTest {

    @Mock
    private LogisticaRepository logisticaRepository;

    @InjectMocks
    private LogisticaImpl logisticaService;

    private Logistica logistica, logistica_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        logistica = new Logistica();
        logistica.setIdLogistica(1);
        logistica.setTipoLogistica("Terrestre");
        logistica.setPrecioEnvio(25000);

        logistica_1 = new Logistica();
        logistica_1.setIdLogistica(2);
        logistica_1.setTipoLogistica("Marítimo");
        logistica_1.setPrecioEnvio(50000);
    }

    @Test
    void findAll() {
        given(logisticaRepository.findAll()).willReturn(Arrays.asList(logistica, logistica_1));

        List<LogisticaDTO> logisticaDTO = logisticaService.findAll();

        assertThat(logisticaDTO).isNotNull();
        assertThat(logisticaDTO.size()).isEqualTo(2);
    }

    @Test
    void findByIdLogistica() {
        given(logisticaRepository.findByIdLogistica(1)).willReturn(Optional.of(logistica));

        LogisticaDTO logisticaDTO = logisticaService.findByIdLogistica(logistica.getIdLogistica());

        assertThat(logisticaDTO).isNotNull();
    }
}
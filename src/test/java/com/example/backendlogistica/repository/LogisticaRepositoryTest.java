package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Logistica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LogisticaRepositoryTest {

    @Mock
    private LogisticaRepository logisticaRepository;

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
    void findByIdLogistica() {
        logisticaRepository.save(logistica);
        logisticaRepository.save(logistica_1);

        Optional<Logistica> logisticaOptional = logisticaRepository.findByIdLogistica(logistica_1.getIdLogistica());

        assertThat(logisticaOptional).isNotNull();
    }
}
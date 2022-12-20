package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.LogisticaDTO;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.repository.LogisticaRepository;
import com.example.backendlogistica.services.interfaces.ILogisticaService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogisticaImpl implements ILogisticaService {

    @Autowired
    private LogisticaRepository logisticaRepository;

    @Override
    public List<LogisticaDTO> findAll() {

        List<LogisticaDTO> dto = new ArrayList<>();

        Iterable<Logistica> logisticas = this.logisticaRepository.findAll();

        for (Logistica logistica : logisticas){

            LogisticaDTO logisticaDTO = MHelpers.modelMapper().map(logistica, LogisticaDTO.class);
            dto.add(logisticaDTO);

        }

        return dto;

    }
}

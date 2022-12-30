package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.LogisticaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILogisticaService {

    List<LogisticaDTO> findAll();

    LogisticaDTO findByIdLogistica(int id);

}

package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.CiudadDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICiudadService {

    List<CiudadDTO> findAll();

}

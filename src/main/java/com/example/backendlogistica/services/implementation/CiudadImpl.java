package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.CiudadDTO;
import com.example.backendlogistica.entities.Ciudad;
import com.example.backendlogistica.repository.CiudadRepository;
import com.example.backendlogistica.services.interfaces.ICiudadService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CiudadImpl implements ICiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<CiudadDTO> findAll() {

        List<CiudadDTO> dto = new ArrayList<>();

        Iterable<Ciudad> ciudades = this.ciudadRepository.findAll();

        for (Ciudad ciudad : ciudades){

            CiudadDTO ciudadDTO = MHelpers.modelMapper().map(ciudad, CiudadDTO.class);
            dto.add(ciudadDTO);

        }

        return dto;

    }
}


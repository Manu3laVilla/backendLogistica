package com.example.backendlogistica.security.service;

import com.example.backendlogistica.security.entities.Rol;
import com.example.backendlogistica.security.enums.RolNombre;
import com.example.backendlogistica.security.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByTipoRol(RolNombre rolNombre){
        return rolRepository.findByTipoRol(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}

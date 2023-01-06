package com.example.backendlogistica.security.repository;

import com.example.backendlogistica.security.entities.Rol;
import com.example.backendlogistica.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByTipoRol(RolNombre rolNombre);

}

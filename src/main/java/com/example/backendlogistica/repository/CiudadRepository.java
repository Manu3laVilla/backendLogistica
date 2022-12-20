package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    @Transactional(readOnly = true)
    Optional<Ciudad> findById(int id);

}

package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Logistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LogisticaRepository extends JpaRepository<Logistica, Integer> {

    @Transactional(readOnly = true)
    Optional<Logistica> findByIdLogistica(int id);

}

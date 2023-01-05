package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

    @Transactional(readOnly = true)
    Iterable<Almacen> findByNombreAlmacen(String nombreAlmacen);

}

package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Transactional(readOnly = true)
    Iterable<Producto> findByNombreProducto(String nombreProducto);

}

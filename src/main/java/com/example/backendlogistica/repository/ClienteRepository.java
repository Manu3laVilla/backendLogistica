package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    Iterable<Cliente> findByIdentificacion(int identificacion);

    boolean existsByIdentificacion(int identificacion);

    boolean existsByCorreoCliente(String correoCliente);

}

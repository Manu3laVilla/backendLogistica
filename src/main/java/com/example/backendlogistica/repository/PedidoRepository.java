package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Iterable<Pedido> findByGuia(String guia);

    boolean existsByGuia(String guia);

}

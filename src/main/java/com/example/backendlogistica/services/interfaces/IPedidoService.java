package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.PedidoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPedidoService {

    List<PedidoDTO> findAll();

    List<PedidoDTO> findByGuia(String guia);

    PedidoDTO findById(int id);

    List<PedidoDTO> findAllByCliente(int idCliente);

    void save(PedidoDTO pedido);

    void update(PedidoDTO pedido, int id);

    void saveAll(List<PedidoDTO> pedidos);

    void deleteById(int id);

    boolean existsByGuia(String guia);

}

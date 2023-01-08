package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.ClienteDTO;
import com.example.backendlogistica.dto.ClienteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {

    List<ClienteDTO> findAll();

    List<ClienteDTO> findByIdentificacion(int identificacion);

    ClienteDTO findById(int id);

    void save(ClienteRequest cliente);

    void update(ClienteRequest cliente, int id);

    void saveAll(List<ClienteRequest> clientes);

    void deleteById(int id);

    boolean existsByIdentificacion(int identificacion);

    boolean existsByCorreoCliente(String correoCliente);

}

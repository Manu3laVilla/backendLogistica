package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.ClienteDTO;
import com.example.backendlogistica.dto.ClienteRequest;
import com.example.backendlogistica.entities.Cliente;
import com.example.backendlogistica.repository.ClienteRepository;
import com.example.backendlogistica.services.interfaces.IClienteService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<ClienteDTO> findAll() {

        List<ClienteDTO> dto = new ArrayList<>();

        Iterable<Cliente> clients = this.clienteRepository.findAll();

        for (Cliente cliente : clients){

            ClienteDTO clienteDTO = MHelpers.modelMapper().map(cliente, ClienteDTO.class);
            dto.add(clienteDTO);

        }

        return dto;

    }

    @Override
    public List<ClienteDTO> findByIdentificacion(int identificacion) {

        List<ClienteDTO> dto = new ArrayList<>();

        Iterable<Cliente> clients = this.clienteRepository.findByIdentificacion(identificacion);

        for (Cliente cliente : clients){

            ClienteDTO clienteDTO = MHelpers.modelMapper().map(cliente, ClienteDTO.class);
            dto.add(clienteDTO);

        }

        return dto;

    }

    @Override
    public ClienteDTO findById(int id) {

        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if(!cliente.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(cliente.get(), ClienteDTO.class);

    }

    @Override
    public void save(ClienteRequest cliente) {

        Cliente clientes = MHelpers.modelMapper().map(cliente, Cliente.class);

        this.clienteRepository.save(clientes);

    }

    @Override
    public void update(ClienteRequest cliente, int id) {

        Optional<Cliente> clientes = this.clienteRepository.findById(id);

        Cliente cliente1 = clientes.get();
        cliente1.setIdentificacion(cliente.getIdentificacion());
        cliente1.setNombreCliente(cliente.getNombreCliente());
        cliente1.setApellidoCliente(cliente.getApellidoCliente());
        cliente1.setDireccionCliente(cliente.getDireccionCliente());
        cliente1.setCorreoCliente(cliente.getCorreoCliente());
        cliente1.setTelefonoCliente(cliente.getTelefonoCliente());

        this.clienteRepository.save(cliente1);

    }

    @Override
    public void saveAll(List<ClienteRequest> clientes) {

        List<Cliente> c = new ArrayList<>();

        for (ClienteRequest user : clientes){
            Cliente cliente = MHelpers.modelMapper().map(user, Cliente.class);
            c.add(cliente);
        }

        this.clienteRepository.saveAll(c);

    }

    @Override
    public void deleteById(int id) {

        this.clienteRepository.deleteById(id);

    }

    private ClienteDTO convertToClienteDTO(final Cliente cliente){
        return MHelpers.modelMapper().map(cliente, ClienteDTO.class);
    }
}

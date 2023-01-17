package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.PedidoDTO;
import com.example.backendlogistica.entities.Pedido;
import com.example.backendlogistica.repository.PedidoRepository;
import com.example.backendlogistica.services.interfaces.IPedidoService;
import com.example.backendlogistica.utils.generator.Generator;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoImpl implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<PedidoDTO> findAll() {

        List<PedidoDTO> dto = new ArrayList<>();

        Iterable<Pedido> pedidos = this.pedidoRepository.findAll();

        for (Pedido pedido : pedidos){

            PedidoDTO pedidoDTO = MHelpers.modelMapper().map(pedido, PedidoDTO.class);
            dto.add(pedidoDTO);

        }

        return dto;

    }

    @Override
    public List<PedidoDTO> findByGuia(String guia) {

        List<PedidoDTO> dto = new ArrayList<>();

        Iterable<Pedido> pedidos = this.pedidoRepository.findByGuia(guia);

        for (Pedido pedido : pedidos){

            PedidoDTO pedidoDTO = MHelpers.modelMapper().map(pedido, PedidoDTO.class);
            dto.add(pedidoDTO);

        }

        return dto;

    }

    @Override
    public PedidoDTO findById(int id) {

        Optional<Pedido> pedido = this.pedidoRepository.findById(id);

        if(!pedido.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(pedido.get(), PedidoDTO.class);

    }

    @Override
    public List<PedidoDTO> findAllByCliente(int idCliente) {

        List<PedidoDTO> dto = new ArrayList<>();

        List<Pedido> pedidos = this.pedidoRepository.findAll();

        for (int i = 0; i < pedidos.size(); i++){

            if(pedidos.get(i).getIdCliente().getId() == idCliente){

                PedidoDTO pedidoDTO = MHelpers.modelMapper().map(pedidos.get(i), PedidoDTO.class);
                dto.add(pedidoDTO);

            }

        }

        return dto;

    }

    @Override
    public void save(PedidoDTO pedido) {

        Pedido pedidos = MHelpers.modelMapper().map(pedido, Pedido.class);

        this.pedidoRepository.save(pedidos);

    }

    @Override
    public void update(PedidoDTO pedido, int id) {

        Optional<Pedido> pedidos = this.pedidoRepository.findById(id);
        Generator generator = new Generator();

        if(!pedidos.isEmpty()){

            Pedido pedido1 = pedidos.get();
            pedido1.setIdCliente(pedido.getIdCliente());
            pedido1.setIdProducto(pedido.getIdProducto());
            pedido1.setIdLogistica(pedido.getIdLogistica());
            pedido1.setIdVehiculo(pedido.getIdVehiculo());
            pedido1.setIdCiudad(pedido.getIdCiudad());
            pedido1.setIdCentro(pedido.getIdCentro());
            pedido1.setCantidad(pedido.getCantidad());
            pedido1.setCostoEnvio(generator.getCosto(pedido.getIdLogistica().getIdLogistica()));
            pedido1.setCostoPagar(generator.getCostoPagar(pedido.getIdLogistica().getIdLogistica(), pedido.getCantidad()));

            this.pedidoRepository.save(pedido1);

        }

    }

    @Override
    public void saveAll(List<PedidoDTO> pedidos) {

        List<Pedido> p = new ArrayList<>();

        for (PedidoDTO pedido : pedidos){
            Pedido entrega = MHelpers.modelMapper().map(pedido, Pedido.class);
            p.add(entrega);
        }

        this.pedidoRepository.saveAll(p);

    }

    @Override
    public void deleteById(int id) {

        this.pedidoRepository.deleteById(id);

    }

    @Override
    public boolean existsByGuia(String guia) {
        return this.pedidoRepository.existsByGuia(guia);
    }
}

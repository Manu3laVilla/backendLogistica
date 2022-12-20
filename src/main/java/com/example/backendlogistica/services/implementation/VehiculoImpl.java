package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.entities.Vehiculo;
import com.example.backendlogistica.repository.VehiculoRepository;
import com.example.backendlogistica.services.interfaces.IVehiculoService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VehiculoImpl implements IVehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<VehiculoDTO> findAll() {

        List<VehiculoDTO> dto = new ArrayList<>();

        Iterable<Vehiculo> vehiculos = this.vehiculoRepository.findAll();

        for (Vehiculo vehiculo : vehiculos){

            VehiculoDTO vehiculoDTO = MHelpers.modelMapper().map(vehiculo, VehiculoDTO.class);
            dto.add(vehiculoDTO);

        }

        return dto;

    }

    @Override
    public VehiculoDTO findByPlacaVehiculo(String PlacaVehiculo) {

        Optional<Vehiculo> vehiculo = this.vehiculoRepository.findByPlacaVehiculo(PlacaVehiculo);

        if(!vehiculo.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(vehiculo.get(), VehiculoDTO.class);

    }

    @Override
    public VehiculoDTO findById(int id) {

        Optional<Vehiculo> vehiculo = this.vehiculoRepository.findById(id);

        if(!vehiculo.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(vehiculo.get(), VehiculoDTO.class);

    }

    @Override
    public List<VehiculoDTO> findAllByLogistica(int idLogistica) {

        List<VehiculoDTO> dto = new ArrayList<>();

        List<Vehiculo> vehiculos = this.vehiculoRepository.findAll();

        for (int i = 0; i < vehiculos.size(); i++){

            if(vehiculos.get(i).getIdLogisticaVehiculo().getIdLogistica() == idLogistica){

                VehiculoDTO vehiculoDTO = MHelpers.modelMapper().map(vehiculos.get(i), VehiculoDTO.class);
                dto.add(vehiculoDTO);

            }

        }

        return dto;

    }

    @Override
    public void save(VehiculoDTO vehiculo) {

        Vehiculo vehiculos = MHelpers.modelMapper().map(vehiculo, Vehiculo.class);

        this.vehiculoRepository.save(vehiculos);

    }

    @Override
    public void update(VehiculoDTO vehiculo, int id) {

        Optional<Vehiculo> vehiculos = this.vehiculoRepository.findById(id);

        Vehiculo vehiculo1 = vehiculos.get();
        vehiculo1.setPlacaVehiculo(vehiculo.getPlacaVehiculo());
        vehiculo1.setIdLogisticaVehiculo(vehiculo.getIdLogisticaVehiculo());

        this.vehiculoRepository.save(vehiculo1);

    }

    @Override
    public void saveAll(List<VehiculoDTO> veheiculos) {

        List<Vehiculo> v = new ArrayList<>();

        for (VehiculoDTO vehiculo : veheiculos){
            Vehiculo vehiculo1 = MHelpers.modelMapper().map(vehiculo, Vehiculo.class);
            v.add(vehiculo1);
        }

        this.vehiculoRepository.saveAll(v);

    }

    @Override
    public void deleteById(int id) {

        this.vehiculoRepository.deleteById(id);

    }
}

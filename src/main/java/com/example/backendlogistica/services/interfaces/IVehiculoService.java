package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.VehiculoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVehiculoService {

    List<VehiculoDTO> findAll();

    List<VehiculoDTO> findByPlacaVehiculo(String PlacaVehiculo);

    VehiculoDTO findById(int id);

    List<VehiculoDTO> findAllByLogistica(int idLogistica);

    void save(VehiculoDTO vehiculo);

    void update(VehiculoDTO vehiculo, int id);

    void saveAll(List<VehiculoDTO> veheiculos);

    void deleteById(int id);

}

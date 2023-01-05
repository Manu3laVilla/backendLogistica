package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.VehiculoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAlmacenService {

    List<AlmacenDTO> findAll();

    List<AlmacenDTO> findByNombreAlmacen(String nombreAlmacen);

    AlmacenDTO findById(int id);

    List<AlmacenDTO> findAllByLogisticaAndCiudad(int idLogistica, int idCiudad);

    void save(AlmacenDTO almacen);

    void update(AlmacenDTO almacen, int id);

    void saveAll(List<AlmacenDTO> almacenes);

    void deleteById(int id);

}

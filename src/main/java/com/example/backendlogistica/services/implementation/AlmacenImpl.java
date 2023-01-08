package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.entities.Almacen;
import com.example.backendlogistica.entities.Vehiculo;
import com.example.backendlogistica.repository.AlmacenRepository;
import com.example.backendlogistica.services.interfaces.IAlmacenService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlmacenImpl implements IAlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    public List<AlmacenDTO> findAll() {

        List<AlmacenDTO> dto = new ArrayList<>();

        Iterable<Almacen> almacens = this.almacenRepository.findAll();

        for (Almacen almacen : almacens){

            AlmacenDTO almacenDTO = MHelpers.modelMapper().map(almacen, AlmacenDTO.class);
            dto.add(almacenDTO);

        }

        return dto;
    }

    @Override
    public List<AlmacenDTO> findByNombreAlmacen(String nombreAlmacen) {

        List<AlmacenDTO> dto = new ArrayList<>();

        Iterable<Almacen> almacens = this.almacenRepository.findByNombreAlmacen(nombreAlmacen);

        for (Almacen almacen : almacens){

            AlmacenDTO almacenDTO = MHelpers.modelMapper().map(almacen, AlmacenDTO.class);
            dto.add(almacenDTO);

        }

        return dto;

    }

    @Override
    public AlmacenDTO findById(int id) {

        Optional<Almacen> almacen = this.almacenRepository.findById(id);

        if(!almacen.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(almacen.get(), AlmacenDTO.class);

    }

    @Override
    public List<AlmacenDTO> findAllByLogisticaAndCiudad(int idLogistica, int idCiudad) {

        List<AlmacenDTO> dto = new ArrayList<>();

        List<Almacen> almacens = this.almacenRepository.findAll();

        for (int i = 0; i < almacens.size(); i++){

            if(almacens.get(i).getIdLogisticaAlmacen().getIdLogistica() == idLogistica &&
                almacens.get(i).getIdCiudadAlmacen().getId() == idCiudad){

                AlmacenDTO almacenDTO = MHelpers.modelMapper().map(almacens.get(i), AlmacenDTO.class);
                dto.add(almacenDTO);

            }

        }

        return dto;

    }

    @Override
    public void save(AlmacenDTO almacen) {

        Almacen almacenes = MHelpers.modelMapper().map(almacen, Almacen.class);

        this.almacenRepository.save(almacenes);

    }

    @Override
    public void update(AlmacenDTO almacen, int id) {

        Optional<Almacen> almacenes = this.almacenRepository.findById(id);

        Almacen almacen1 = almacenes.get();
        almacen1.setNombreAlmacen(almacen.getNombreAlmacen());
        almacen1.setIdLogisticaAlmacen(almacen.getIdLogisticaAlmacen());
        almacen1.setIdCiudadAlmacen(almacen.getIdCiudadAlmacen());

        this.almacenRepository.save(almacen1);

    }

    @Override
    public void saveAll(List<AlmacenDTO> almacenes) {

        List<Almacen> a = new ArrayList<>();

        for (AlmacenDTO almacen : almacenes){
            Almacen almacen1 = MHelpers.modelMapper().map(almacen, Almacen.class);
            a.add(almacen1);
        }

        this.almacenRepository.saveAll(a);

    }

    @Override
    public void deleteById(int id) {
        this.almacenRepository.deleteById(id);
    }

    @Override
    public boolean existsByNombreAlmacen(String nombreAlmacen) {
        return this.almacenRepository.existsByNombreAlmacen(nombreAlmacen);
    }
}

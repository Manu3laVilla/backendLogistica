package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    @Transactional(readOnly = true)
    Iterable<Vehiculo> findByPlacaVehiculo(String placaVehiculo);

    boolean existsByPlacaVehiculo(String placa);


}

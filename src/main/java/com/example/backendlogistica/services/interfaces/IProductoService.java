package com.example.backendlogistica.services.interfaces;

import com.example.backendlogistica.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductoService {

    List<ProductoDTO> findAll();

    List<ProductoDTO> findByNombreProducto(String nombreProducto);

    ProductoDTO findById(int id);

    List<ProductoDTO> findAllByLogistica(int idLogistica);

    void save(ProductoDTO producto);

    void update(ProductoDTO producto, int id);

    void saveAll(List<ProductoDTO> productos);

    void deleteById(int id);

}

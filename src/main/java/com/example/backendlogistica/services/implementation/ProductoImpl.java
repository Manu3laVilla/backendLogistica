package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.ProductoDTO;
import com.example.backendlogistica.entities.Producto;
import com.example.backendlogistica.repository.ProductoRepository;
import com.example.backendlogistica.services.interfaces.IProductoService;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductoImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> findAll() {

        List<ProductoDTO> dto = new ArrayList<>();

        Iterable<Producto> productos = this.productoRepository.findAll();

        for (Producto producto : productos){

            ProductoDTO productoDTO = MHelpers.modelMapper().map(producto, ProductoDTO.class);
            dto.add(productoDTO);

        }

        return dto;

    }

    @Override
    public List<ProductoDTO> findByNombreProducto(String nombreProducto) {

        List<ProductoDTO> dto = new ArrayList<>();

        Iterable<Producto> productos = this.productoRepository.findByNombreProducto(nombreProducto);

        for (Producto producto : productos){

            ProductoDTO productoDTO = MHelpers.modelMapper().map(producto, ProductoDTO.class);
            dto.add(productoDTO);

        }

        return dto;
    }

    @Override
    public ProductoDTO findById(int id) {

        Optional<Producto> producto = this.productoRepository.findById(id);

        if(!producto.isPresent()){
            return null;
        }

        return MHelpers.modelMapper().map(producto.get(), ProductoDTO.class);

    }

    @Override
    public List<ProductoDTO> findAllByLogistica(int idLogistica) {

        List<ProductoDTO> dto = new ArrayList<>();

        List<Producto> productos = this.productoRepository.findAll();

        for (int i = 0; i < productos.size(); i++){

            if(productos.get(i).getIdLogisticaProducto().getIdLogistica() == idLogistica){

                ProductoDTO productoDTO = MHelpers.modelMapper().map(productos.get(i), ProductoDTO.class);
                dto.add(productoDTO);

            }

        }

        return dto;

    }

    @Override
    public void save(ProductoDTO producto) {

        Producto productos = MHelpers.modelMapper().map(producto, Producto.class);

        this.productoRepository.save(productos);

    }

    @Override
    public void update(ProductoDTO producto, int id) {

        Optional<Producto> productos = this.productoRepository.findById(id);

        if(!productos.isEmpty()){

            Producto producto1 = productos.get();
            producto1.setNombreProducto(producto.getNombreProducto());
            producto1.setIdLogisticaProducto(producto.getIdLogisticaProducto());

            this.productoRepository.save(producto1);

        }

    }

    @Override
    public void saveAll(List<ProductoDTO> productos) {

        List<Producto> p = new ArrayList<>();

        for (ProductoDTO producto : productos){
            Producto product = MHelpers.modelMapper().map(producto, Producto.class);
            p.add(product);
        }

        this.productoRepository.saveAll(p);

    }

    @Override
    public void deleteById(int id) {

        this.productoRepository.deleteById(id);

    }

    @Override
    public boolean existsByNombreProducto(String nombreProducto) {
        return this.productoRepository.existsByNombreProducto(nombreProducto);
    }
}

package com.example.backendlogistica.services.implementation;

import com.example.backendlogistica.dto.ProductoDTO;
import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.entities.Producto;
import com.example.backendlogistica.repository.ProductoRepository;
import com.example.backendlogistica.utils.helpers.MHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProductoImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoImpl productoService;

    private Producto producto, producto_1;

    private Logistica logistica;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        logistica = new Logistica();
        logistica.setIdLogistica(1);
        logistica.setTipoLogistica("Marítima");
        logistica.setPrecioEnvio(50000);

        producto = new Producto();
        producto.setId(1);
        producto.setNombreProducto("Nombre Producto #1");
        producto.setIdLogisticaProducto(logistica);

        producto_1 = new Producto();
        producto_1.setId(2);
        producto_1.setNombreProducto("Nombre Producto #2");
        producto_1.setIdLogisticaProducto(new Logistica());
    }

    @Test
    void findAll() {
        given(productoRepository.findAll()).willReturn(List.of(producto, producto_1));

        List<ProductoDTO> productoDTOS = productoService.findAll();

        assertThat(productoDTOS).isNotNull();
        assertThat(productoDTOS.size()).isEqualTo(2);
    }

    @Test
    void findByNombreProducto() {
        given(productoRepository.findByNombreProducto(anyString())).willReturn(List.of(producto));

        List<ProductoDTO> productoDTOS = productoService.findByNombreProducto(producto.getNombreProducto());

        assertThat(productoDTOS).isNotNull();
        assertThat(productoDTOS.get(0).getNombreProducto()).isEqualTo("Nombre Producto #1");
        assertThat(productoDTOS.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        given(productoRepository.findById(anyInt())).willReturn(Optional.of(producto));

        ProductoDTO productoDTO = productoService.findById(producto.getId());

        assertThat(productoDTO).isNotNull();
        assertThat(productoDTO.getNombreProducto()).isEqualTo("Nombre Producto #1");
    }

    @Test
    void findAllByLogistica() {
        given(productoRepository.findAll()).willReturn(List.of(producto, producto_1));

        List<ProductoDTO> productoDTOS = productoService.findAllByLogistica(1);

        assertThat(productoDTOS).isNotNull();
        assertThat(productoDTOS.get(0).getNombreProducto()).isEqualTo("Nombre Producto #1");
    }

    @Test
    void save() {
        given(productoRepository.save(producto)).willReturn(producto);
        given(productoRepository.save(producto_1)).willReturn(producto_1);

        ProductoDTO producto = MHelpers.modelMapper().map(this.producto, ProductoDTO.class);
        ProductoDTO producto_1 = MHelpers.modelMapper().map(this.producto_1, ProductoDTO.class);
        productoService.save(producto);
        productoService.save(producto_1);

        assertThat(productoService.findAll()).isNotNull();
    }

    @Test
    void update() {
        given(productoRepository.findById(anyInt())).willReturn(Optional.of(producto));
        given(productoRepository.save(producto)).willReturn(producto);
        producto.setNombreProducto("Producto 1");

        ProductoDTO producto = MHelpers.modelMapper().map(this.producto, ProductoDTO.class);
        productoService.update(producto, this.producto.getId());

        assertThat(this.producto.getNombreProducto()).isEqualTo("Producto 1");
    }

    @Test
    void deleteById() {
        willDoNothing().given(productoRepository).deleteById(1);

        productoService.deleteById(1);

        verify(productoRepository, times(1)).deleteById(1);
    }

    @Test
    void existsByNombreProducto() {
        given(productoRepository.existsByNombreProducto(anyString())).willReturn(true);

        assertThat(productoService.existsByNombreProducto(producto.getNombreProducto())).isTrue();
    }
}
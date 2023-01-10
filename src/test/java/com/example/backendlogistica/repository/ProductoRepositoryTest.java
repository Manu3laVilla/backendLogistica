package com.example.backendlogistica.repository;

import com.example.backendlogistica.entities.Logistica;
import com.example.backendlogistica.entities.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ProductoRepositoryTest {

    @Mock
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        producto = new Producto();
        producto.setId(1);
        producto.setIdLogisticaProducto(new Logistica());
        producto.setNombreProducto("Nombre Producto");
    }

    @Test
    void findByNombreProducto() {
        Iterable<Producto> productos = productoRepository.findByNombreProducto(producto.getNombreProducto());

        assertThat(productos).isNotNull();
    }

    @Test
    void existsByNombreProducto() {
        given(productoRepository.existsByNombreProducto(producto.getNombreProducto())).willReturn(true);

        boolean response = productoRepository.existsByNombreProducto(producto.getNombreProducto());

        assertThat(response).isEqualTo(true);
    }
}
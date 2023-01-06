package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.ProductoDTO;
import com.example.backendlogistica.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.productoService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/by/{nombreProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNombreProducto(@PathVariable("nombreProducto") String nombreProducto) {
        return ResponseEntity.ok(this.productoService.findByNombreProducto(nombreProducto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/byLogistica/{idLogistica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllLogistica(@PathVariable("idLogistica") int idLogistica) {
        return ResponseEntity.ok(this.productoService.findAllByLogistica(idLogistica));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveProducto(@RequestBody ProductoDTO request) {
        this.productoService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{productioId}/delete")
    public ResponseEntity<Object> deleteProducto(@PathVariable int productioId){
        this.productoService.deleteById(productioId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{productioId}/update")
    public ResponseEntity<Object> updateProducto(@RequestBody ProductoDTO request, @PathVariable int productioId) {

        this.productoService.update(request, productioId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}

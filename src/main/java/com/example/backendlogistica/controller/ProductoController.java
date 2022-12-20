package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.ProductoDTO;
import com.example.backendlogistica.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.productoService.findAll());
    }

    @GetMapping(value = "/by/{nombreProducto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNombreProducto(@PathVariable("nombreProducto") String nombreProducto) {
        return ResponseEntity.ok(this.productoService.findByNombreProducto(nombreProducto));
    }

    @GetMapping(value = "/byLogistica/{idLogistica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllLogistica(@PathVariable("idLogistica") int idLogistica) {
        return ResponseEntity.ok(this.productoService.findAllByLogistica(idLogistica));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveProducto(@RequestBody ProductoDTO request) {
        this.productoService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{productioId}/delete")
    public ResponseEntity<Object> deleteProducto(@PathVariable int productioId){
        this.productoService.deleteById(productioId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/{productioId}/update")
    public ResponseEntity<Object> updateProducto(@RequestBody ProductoDTO request, @PathVariable int productioId) {

        this.productoService.update(request, productioId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}

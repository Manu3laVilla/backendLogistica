package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.PedidoDTO;
import com.example.backendlogistica.services.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.pedidoService.findAll());
    }

    @GetMapping(value = "/byPedido/{guia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByGuiaPedido(@PathVariable("guia") String guia) {
        if(!pedidoService.existsByGuia(guia))
            return new ResponseEntity("No Hay Datos Para La Guía: " + guia, HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(this.pedidoService.findByGuia(guia));
    }

    @GetMapping(value = "/by/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllCliente(@PathVariable("idCliente") int idCliente) {
        return ResponseEntity.ok(this.pedidoService.findAllByCliente(idCliente));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePedido(@RequestBody PedidoDTO request) {
        if(request.getCantidad() == 0)
            return new ResponseEntity("Debe Ingresar Una Cantidad Superior A 0" ,HttpStatus.UNPROCESSABLE_ENTITY);
        this.pedidoService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{pedidoId}/delete")
    public ResponseEntity<Object> deletePedido(@PathVariable int pedidoId){
        this.pedidoService.deleteById(pedidoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{pedidoId}/update")
    public ResponseEntity<Object> updatePedido(@RequestBody PedidoDTO request, @PathVariable int pedidoId) {
        if(request.getCantidad() == 0)
            return new ResponseEntity("Debe Ingresar Una Cantidad Superior A 0" ,HttpStatus.UNPROCESSABLE_ENTITY);
        this.pedidoService.update(request, pedidoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}

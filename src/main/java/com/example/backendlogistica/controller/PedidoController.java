package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.PedidoDTO;
import com.example.backendlogistica.services.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(this.pedidoService.findByGuia(guia));
    }

    @GetMapping(value = "/by/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllCliente(@PathVariable("idCliente") int idCliente) {
        return ResponseEntity.ok(this.pedidoService.findAllByCliente(idCliente));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePedido(@RequestBody PedidoDTO request) {
        this.pedidoService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{pedidoId}/delete")
    public ResponseEntity<Object> deletePedido(@PathVariable int pedidoId){
        this.pedidoService.deleteById(pedidoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/{pedidoId}/update")
    public ResponseEntity<Object> updatePedido(@RequestBody PedidoDTO request, @PathVariable int pedidoId) {

        this.pedidoService.update(request, pedidoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}

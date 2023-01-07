package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.ClienteRequest;
import com.example.backendlogistica.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.clienteService.findAll());
    }

    @GetMapping(value = "/by/{identificacion}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByIdentificacion(@PathVariable("identificacion") int identificacion) {
        return ResponseEntity.ok(this.clienteService.findByIdentificacion(identificacion));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCliente(@RequestBody ClienteRequest request) {
        this.clienteService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{clienteId}/delete")
    public ResponseEntity<Object> deleteCliente(@PathVariable int clienteId){
        this.clienteService.deleteById(clienteId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{clienteId}/update")
    public ResponseEntity<Object> updateCliente(@RequestBody ClienteRequest request, @PathVariable int clienteId) {

        this.clienteService.update(request, clienteId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
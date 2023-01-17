package com.example.backendlogistica.controller;
import com.example.backendlogistica.services.interfaces.ILogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/tipos")
public class LogisticaController {

    @Autowired
    private ILogisticaService logisticaService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.logisticaService.findAll());
    }

    @GetMapping(value = "/by/{idLogistica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByIdLogistica(@PathVariable("idLogistica") int idLogistica) {
        return ResponseEntity.ok(this.logisticaService.findByIdLogistica(idLogistica).getPrecioEnvio());
    }

}

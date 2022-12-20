package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.services.interfaces.IAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/almacenes")
public class AlmacenController {

    @Autowired
    private IAlmacenService almacenService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.almacenService.findAll());
    }

    @GetMapping(value = "/by/{nombreAlmacen}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNombreAlmacen(@PathVariable("nombreAlmacen") String nombreAlmacen) {
        return ResponseEntity.ok(this.almacenService.findByNombreAlmacen(nombreAlmacen));
    }

    @GetMapping(value = "/byAlmacen/{idLogistica}_{idCiudad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllLogisticaCiudad(@PathVariable("idLogistica") int idLogistica,
                                                           @PathVariable("idCiudad") int idCiudad) {
        return ResponseEntity.ok(this.almacenService.findAllByLogisticaAndCiudad(idLogistica, idCiudad));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveAlmacen(@RequestBody AlmacenDTO request) {
        this.almacenService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{almacenId}/delete")
    public ResponseEntity<Object> deleteAlmacen(@PathVariable int almacenId){
        this.almacenService.deleteById(almacenId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/{almacenId}/update")
    public ResponseEntity<Object> updateAlmacen(@RequestBody AlmacenDTO request, @PathVariable int almacenId) {

        this.almacenService.update(request, almacenId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}

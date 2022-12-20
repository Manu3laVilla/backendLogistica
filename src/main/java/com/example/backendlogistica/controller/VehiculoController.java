package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.services.interfaces.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.vehiculoService.findAll());
    }

    @GetMapping(value = "/by/{placaVehiculo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNombreProducto(@PathVariable("placaVehiculo") String placaVehiculo) {
        return ResponseEntity.ok(this.vehiculoService.findByPlacaVehiculo(placaVehiculo));
    }

    @GetMapping(value = "/byLogistica/{idLogistica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllLogistica(@PathVariable("idLogistica") int idLogistica) {
        return ResponseEntity.ok(this.vehiculoService.findAllByLogistica(idLogistica));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVehiculo(@RequestBody VehiculoDTO request) {
        this.vehiculoService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{vehiculoId}/delete")
    public ResponseEntity<Object> deleteVehiculo(@PathVariable int vehiculoId){
        this.vehiculoService.deleteById(vehiculoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/{vehiculoId}/update")
    public ResponseEntity<Object> updateVehiculo(@RequestBody VehiculoDTO request, @PathVariable int vehiculoId) {

        this.vehiculoService.update(request, vehiculoId);
        return ResponseEntity.ok(Boolean.TRUE);
    }


}

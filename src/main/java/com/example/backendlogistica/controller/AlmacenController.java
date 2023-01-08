package com.example.backendlogistica.controller;

import com.example.backendlogistica.dto.AlmacenDTO;
import com.example.backendlogistica.dto.VehiculoDTO;
import com.example.backendlogistica.services.interfaces.IAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistica/almacenes")
public class AlmacenController {

    @Autowired
    private IAlmacenService almacenService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(this.almacenService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/by/{nombreAlmacen}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNombreAlmacen(@PathVariable("nombreAlmacen") String nombreAlmacen) {
        if(!almacenService.existsByNombreAlmacen(nombreAlmacen))
            return new ResponseEntity("No Hay Datos Para El Almacén Con Nombre: " + nombreAlmacen,
                    HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(this.almacenService.findByNombreAlmacen(nombreAlmacen));
    }

    @GetMapping(value = "/byAlmacen/{idLogistica}_{idCiudad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByAllLogisticaCiudad(@PathVariable("idLogistica") int idLogistica,
                                                           @PathVariable("idCiudad") int idCiudad) {
        return ResponseEntity.ok(this.almacenService.findAllByLogisticaAndCiudad(idLogistica, idCiudad));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveAlmacen(@RequestBody AlmacenDTO request) {
        if(this.almacenService.existsByNombreAlmacen(request.getNombreAlmacen()))
            return new ResponseEntity("El Nombre De Almacén Ingresado Ya Existe", HttpStatus.UNPROCESSABLE_ENTITY);
        this.almacenService.save(request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{almacenId}/delete")
    public ResponseEntity<Object> deleteAlmacen(@PathVariable int almacenId){
        this.almacenService.deleteById(almacenId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{almacenId}/update")
    public ResponseEntity<Object> updateAlmacen(@RequestBody AlmacenDTO request, @PathVariable int almacenId) {

        this.almacenService.update(request, almacenId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}

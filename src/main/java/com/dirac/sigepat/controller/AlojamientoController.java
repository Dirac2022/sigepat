/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.service.AlojamientoService;
import com.dirac.sigepat.model.Alojamiento;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/alojamiento")
public class AlojamientoController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired // Aplica inyeccion de dependencias
    private AlojamientoService alojamientoService;
    
    
    //@RequestMapping(value="listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping()  // se entiende que al hacer una llamada GET se debe llamar a getAlojamientos
    public ResponseEntity<?> getAlojamientos() {
        List<Alojamiento> listaAlojamientos = null;
        try {
            listaAlojamientos = alojamientoService.getAlojamientos();
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if (listaAlojamientos.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamientos not found").build());
        
        return ResponseEntity.ok(listaAlojamientos);
    }
    
    @GetMapping("/find")  // se entiende que al hacer una llamada GET se debe llamar a getAlojamientos
    public ResponseEntity<?> getAlojamiento(@RequestBody Optional<Alojamiento> alojamiento) {
        
        logger.info(">findAlojamientoById", alojamiento.toString());
        try {
            alojamiento = alojamientoService.findAlojamientoById(alojamiento.get().getIdAlojamiento());
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if (alojamiento == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamiento not found").build());
        
        return ResponseEntity.ok(alojamiento.get());
    }
    

    
}

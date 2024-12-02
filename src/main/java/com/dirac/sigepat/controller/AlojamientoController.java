/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.AlojamientoRequest;
import com.dirac.sigepat.dto.AlojamientoResponse;
import com.dirac.sigepat.service.AlojamientoService;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path="api/v1/alojamiento")
public class AlojamientoController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    AlojamientoService alojamientoService;
    
    
    
    @GetMapping()
    public ResponseEntity<?> getAlojamientos() {
        List<AlojamientoResponse> listaAlojamientoResponse = null;
        
        try {
            listaAlojamientoResponse = alojamientoService.listAlojamientos();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
        
        if (listaAlojamientoResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamientos not found").build());
        }
        
        return ResponseEntity.ok(listaAlojamientoResponse);
    }
    
    @PostMapping()
    public ResponseEntity<?> insertAlojamiento(@RequestBody AlojamientoRequest alojamientoRequest) {
        
        logger.info(">insert " + alojamientoRequest.toString());
        
        AlojamientoResponse alojamientoResponse;
        
        try {
            alojamientoResponse = alojamientoService.insertAlojamiento(alojamientoRequest);
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if (alojamientoResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamiento not inserted").build());
        }
        
        return ResponseEntity.ok(alojamientoResponse);
    }
       
}

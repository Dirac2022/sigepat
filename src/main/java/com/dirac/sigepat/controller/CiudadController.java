/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.CiudadRequest;
import com.dirac.sigepat.dto.CiudadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dirac.sigepat.service.CiudadService;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping(path = "api/v1/ciudad")
public class CiudadController {
    
    
    // Configura un logger para registrar mensajes de depuración y errores.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CiudadService ciudadService;

    // GetMapping() maneja las solicitudes HTTP GET a la ruta
    @GetMapping()
    public ResponseEntity<?> getCiudades() {
        
        List<CiudadResponse> listaCiudadesResponse = null;

        try {
            listaCiudadesResponse = ciudadService.listCiudades();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaCiudadesResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ciudades not found").build());
        }

        return ResponseEntity.ok(listaCiudadesResponse);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findCiudadById(@RequestBody Optional<CiudadRequest> ciudadRequest) {
        logger.info(">find" + ciudadRequest.toString());
        CiudadResponse ciudadResponse;
        try {
   
            ciudadResponse = ciudadService.findCiudad(ciudadRequest.get().getIdCiudad());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            // Devuelve un status 500
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (ciudadResponse == null) {
            // Devuelve status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ciudad not found").build());
        }
        
        // Devuelve status 200
        return ResponseEntity.ok(ciudadResponse);

    }
    
}

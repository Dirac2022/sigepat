/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.PasajeroRequest;
import com.dirac.sigepat.dto.PasajeroResponse;
import com.dirac.sigepat.service.PasajeroService;
import com.dirac.sigepat.utils.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api/v1/pasajero")
public class PasajeroController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PasajeroService pasajeroService;
    
    @PostMapping
    public ResponseEntity<?> insertPasajero(@RequestBody PasajeroRequest pasajeroRequest) {
        logger.info(">insert " + pasajeroRequest.toString());
        PasajeroResponse pasajeroResponse;
        try {
            pasajeroResponse = pasajeroService.insertPasajero(pasajeroRequest);
        } catch (Exception e) {
            logger.error("Error inesperado en controller ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (pasajeroResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Pasajero not inserted").build());
        }
        return ResponseEntity.ok(pasajeroResponse);
    }
}

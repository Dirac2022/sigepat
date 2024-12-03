/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.AlojamientoResponse;
import com.dirac.sigepat.dto.PaqueteTuristicoResponse;
import com.dirac.sigepat.dto.PaqueteTuristicoRequest;
import com.dirac.sigepat.service.PaqueteTuristicoService;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/paquete-turistico")
public class PaqueteTuristicoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PaqueteTuristicoService paqueteTuristicoService;

    @GetMapping()
    public ResponseEntity<?> getPaquetesTuristicos() {
        List<PaqueteTuristicoResponse> paqueteTuristicoResponse = null;

        try {
            paqueteTuristicoResponse = paqueteTuristicoService.listPaqueteTuristico();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (paqueteTuristicoResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Paquetes not found").build());
        }

        return ResponseEntity.ok(paqueteTuristicoResponse);
    }

    @PostMapping()
    public ResponseEntity<?> insertPaqueteTuristico(@RequestBody PaqueteTuristicoRequest paqueteTuristicoRequest) {

        logger.info(">insert " + paqueteTuristicoRequest.toString());

        PaqueteTuristicoResponse paqueteTuristicoResponse;

        try {
            paqueteTuristicoResponse = paqueteTuristicoService.insertPaqueteTuristico(paqueteTuristicoRequest);
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (paqueteTuristicoResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("PaqueteTuristico not inserted").build());
        }

        return ResponseEntity.ok(paqueteTuristicoResponse);
    }

}

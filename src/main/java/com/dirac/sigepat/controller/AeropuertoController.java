package com.dirac.sigepat.controller;

import com.dirac.sigepat.service.AeropuertoService;
import com.dirac.sigepat.model.Aeropuerto;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/aeropuerto")
public class AeropuertoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AeropuertoService aeropuertoService;

    @GetMapping()
    public ResponseEntity<?> getAeropuertos() {
        List<Aeropuerto> listaAeropuertos = null;
        try {
            listaAeropuertos = aeropuertoService.getAeropuertos();
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaAeropuertos.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Aeropuertos not found").build());

        return ResponseEntity.ok(listaAeropuertos);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAeropuerto(@RequestBody Optional<Aeropuerto> aeropuerto) {
        logger.info(">findAeropuertoById", aeropuerto.get().toString());
        try {
            aeropuerto = aeropuertoService.findAeropuertoById(aeropuerto.get().getIdAeropuerto());
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (aeropuerto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Aeropuerto not found").build());

        return ResponseEntity.ok(aeropuerto.get());
    }

    @PostMapping()
    public ResponseEntity<?> insertAeropuerto(@RequestBody Aeropuerto aeropuerto) {
        logger.info(">insertAeropuerto", aeropuerto.toString());
        Aeropuerto newAeropuerto;
        try {
            newAeropuerto = aeropuertoService.insertAeropuerto(aeropuerto);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error de integridad de datos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Violación de clave única: el aeropuerto ya existe.");
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage());
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newAeropuerto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Aeropuerto not insert").build());
        return ResponseEntity.ok(newAeropuerto);
    }

    @PutMapping()
    public ResponseEntity<?> updateAeropuerto(@RequestBody Aeropuerto aeropuerto) {
        logger.info(">updateAeropuerto", aeropuerto.toString());
        Aeropuerto newAeropuerto;
        try {
            newAeropuerto = aeropuertoService.updateAeropuerto(aeropuerto);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newAeropuerto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Aeropuerto not update").build());
        return ResponseEntity.ok(newAeropuerto);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAeropuerto(@RequestBody Optional<Aeropuerto> aeropuerto) {
        logger.info(">deleteAeropuerto", aeropuerto.get().toString());
        try {
            aeropuerto = aeropuertoService.findAeropuertoById(aeropuerto.get().getIdAeropuerto());
            if (aeropuerto.isPresent())
                aeropuertoService.deleteAeropuerto(aeropuerto.get().getIdAeropuerto());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(aeropuerto.get());
    }

}

package com.dirac.sigepat.controller;

import com.dirac.sigepat.service.AutoService;
import com.dirac.sigepat.model.Auto;
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
@RequestMapping(path = "api/v1/auto")
public class AutoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired // Aplica inyección de dependencias
    private AutoService autoService;

    @GetMapping()
    public ResponseEntity<?> getAutos() {
        List<Auto> listaAutos = null;
        try {
            listaAutos = autoService.getAutos();
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaAutos.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Autos not found").build());

        return ResponseEntity.ok(listaAutos);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAuto(@RequestBody Optional<Auto> auto) {
        logger.info(">findAutoById", auto.get().toString());
        try {
            auto = autoService.findAutoById(auto.get().getIdAuto());
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (auto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Auto not found").build());

        return ResponseEntity.ok(auto.get());
    }

    @PostMapping()
    public ResponseEntity<?> insertAuto(@RequestBody Auto auto) {
        logger.info(">insertAuto", auto.toString());
        Auto newAuto;
        try {
            newAuto = autoService.insertAuto(auto);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error de integridad de datos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Violación de clave única: el auto ya existe.");
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage());
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newAuto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Auto not insert").build());
        return ResponseEntity.ok(newAuto);
    }

    @PutMapping()
    public ResponseEntity<?> updateAuto(@RequestBody Auto auto) {
        logger.info(">updateAuto", auto.toString());
        Auto newAuto;
        try {
            newAuto = autoService.updateAuto(auto);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newAuto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Auto not update").build());
        return ResponseEntity.ok(newAuto);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAuto(@RequestBody Optional<Auto> auto) {
        logger.info(">deleteAuto", auto.get().toString());
        try {
            auto = autoService.findAutoById(auto.get().getIdAuto());
            if (auto.isPresent())
                autoService.deleteAuto(auto.get().getIdAuto());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(auto.get());
    }
}

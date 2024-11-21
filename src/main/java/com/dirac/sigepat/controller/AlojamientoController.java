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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        // En vez de precisar el tipo, usamos ? ya que no queremos definir 
        // explicitamente el tipo de retorno
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
        
        logger.info(">findAlojamientoById", alojamiento.get().toString());
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
    

    @PostMapping()
    public ResponseEntity<?> insertAlojamiento(@RequestBody Alojamiento alojamiento){        
        logger.info(">insertAlojamiento",alojamiento.toString());
        Alojamiento newAlojamiento;
        try{
            newAlojamiento = alojamientoService.insertAlojamiento(alojamiento);
        }catch (DataIntegrityViolationException e) {
            logger.error("Error de integridad de datos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Violación de clave única: el alojamiento ya existe.");
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage());
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newAlojamiento==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamiento not insert").build());
        return ResponseEntity.ok(newAlojamiento);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateAlojamiento(@RequestBody Alojamiento alojamiento){        
        logger.info(">updateAlojamiento",alojamiento.toString());
        Alojamiento newAlojamiento;
        try{
            newAlojamiento=alojamientoService.updateAlojamiento(alojamiento);
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(newAlojamiento==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Alojamiento not update").build());
        return ResponseEntity.ok(newAlojamiento);
    }
    
    @DeleteMapping()
    public ResponseEntity<?> deleteAlojamiento(@RequestBody Optional<Alojamiento> alojamiento){        
        logger.info(">deleteAlojamiento",alojamiento.get().toString());        
        try{
            alojamiento = alojamientoService.findAlojamientoById(alojamiento.get().getIdAlojamiento());
            if(alojamiento.isPresent())            
                alojamientoService.deleteAlojamiento(alojamiento.get().getIdAlojamiento());
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(alojamiento.get());
    }
   
}

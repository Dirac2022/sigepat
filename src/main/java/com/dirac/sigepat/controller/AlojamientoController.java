/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.service.AlojamientoService;
import com.dirac.sigepat.model.Alojamiento;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/alojamiento")
public class AlojamientoController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired // Aplica inyeccion de dependencias
    private AlojamientoService alojamientoService;
    
    
    @RequestMapping(value="listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Alojamiento>> getAlojamientos() {
        List<Alojamiento> listaAlojamientos = null;
        try {
            listaAlojamientos = alojamientoService.getAlojamientos();
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(listaAlojamientos, HttpStatus.OK);
    }
    
}

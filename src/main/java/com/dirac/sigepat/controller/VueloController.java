/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.HotelRequest;
import com.dirac.sigepat.dto.HotelResponse;
import com.dirac.sigepat.dto.VueloRequest;
import com.dirac.sigepat.dto.VueloResponse;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.service.VueloService;
import com.dirac.sigepat.model.Vuelo;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * El controlador es el encargado de gestionar las peticiones del front para la entidad Vuelo.
 */
@RestController
@RequestMapping(path = "api/v1/vuelo")
public class VueloController {

    // Configura un logger para registrar mensajes de depuración y errores.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VueloService vueloService;

    // GetMapping() maneja las solicitudes HTTP GET a la ruta
    @GetMapping()
    public ResponseEntity<?> getVuelos() {
        
        List<VueloResponse> listaVuelosResponse = null;

        try {
            // Llama al servicio HotelService para 
            // obtener la lista de hoteles.
            listaVuelosResponse = vueloService.listVuelos();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaVuelosResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Vuelos not found").build());
        }

        // Si la lista no esta vacia devuelve el status 200 OK
        // y los datos de los hoteles que el cuerpo de la respuesta
        return ResponseEntity.ok(listaVuelosResponse);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findVueloById(@RequestBody Optional<VueloRequest> vueloRequest) {
        
        logger.info(">find" + vueloRequest.toString());
        
        VueloResponse vueloResponse;
        
        try {

            vueloResponse = vueloService.findVuelo(vueloRequest.get().getIdVuelo());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            // Devuelve un status 500
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (vueloResponse == null) {
            // Devuelve status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Vuelo not found").build());
        }
        
        // Devuelve status 200
        return ResponseEntity.ok(vueloResponse);

    }
    
    
    // Buscar vuelos por ciudad de origen y destino
    @GetMapping("/findByCiudades")
    public ResponseEntity<?> findVuelosByCiudades(@RequestBody VueloRequest vueloRequest) {
        logger.info(">findVuelosByCiudades " + vueloRequest.toString());
        List<VueloResponse> vueloResponse;

        try {
            vueloResponse = vueloService.findVuelosByCiudades(vueloRequest.getCuidadOrigen(), vueloRequest.getCiudadDestino());
            
            if (vueloResponse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("Vuelos not found for the cities entered").build());
            }

            return ResponseEntity.ok(vueloResponse);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    

    @PostMapping()
    public ResponseEntity<?> insertVuelo(@RequestBody VueloRequest vueloRequest) {
        
        logger.info(">insert" + vueloRequest.toString());
        
        VueloResponse vueloResponse;
        
        try {
            vueloResponse = vueloService.insertVuelo(vueloRequest);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (vueloResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Vuelo not insert").build());
        }
        return ResponseEntity.ok(vueloResponse);
    }
}

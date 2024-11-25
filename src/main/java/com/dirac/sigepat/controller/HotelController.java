/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dirac.sigepat.dto.HotelRequest;
import com.dirac.sigepat.dto.HotelResponse;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.model.Hotel;
import com.dirac.sigepat.service.HotelService;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HotelService hotelService;

    @GetMapping()
    public ResponseEntity<?> getHoteles() {
        List<HotelResponse> listaHotelesResponse = null;

        try {
            listaHotelesResponse = hotelService.listHoteles();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaHotelesResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hoteles not found").build());
        }

        return ResponseEntity.ok(listaHotelesResponse);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findHotelById(@RequestBody Optional<HotelRequest> hotelRequest) {
        logger.info(">find" + hotelRequest.toString());
        HotelResponse hotelResponse;
        try {
            hotelResponse = hotelService.findHotel(hotelRequest.get().getIdHotel());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (hotelResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hotel not found").build());
        }
        return ResponseEntity.ok(hotelResponse);

    }

    @GetMapping("/findByCity")
    public ResponseEntity<?> findHotelsByCity(@RequestBody Optional<HotelRequest> hotelRequest) {
        logger.info(">findHotelsByCity " + hotelRequest.toString());
        List<HotelResponse> hotelResponses;

        try {
            // Obtén el ID de la ciudad desde el request
            Long idCiudad = hotelRequest.map(HotelRequest::getCiudad).orElse(null);

            if (idCiudad == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().message("Ciudad ID is required").build());
            }

            // Encuentra la entidad Ciudad en el repositorio
            Ciudad ciudad = hotelService.findCiudadById(idCiudad);

            // Llama al método que busca hoteles por ciudad
            hotelResponses = hotelService.findHotelesByCiudad(ciudad);

            if (hotelResponses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("No hotels found in the given city").build());
            }

            return ResponseEntity.ok(hotelResponses);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> insertHotel(@RequestBody HotelRequest hotelRequest) {
        logger.info(">insert" + hotelRequest.toString());
        HotelResponse hotelResponse;
        try {
            hotelResponse = hotelService.insertHotel(hotelRequest);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (hotelResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hotel not insert").build());
        }
        return ResponseEntity.ok(hotelResponse);
    }

}

/*

    @PostMapping()
    public ResponseEntity<?> insertPersona(@RequestBody PersonaRequest personaResquest){
        logger.info(">insert" +  personaResquest.toString());
        PersonaResponse personaResponse;
        try{            
            personaResponse=personaService.insertPersona(personaResquest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());           
        return ResponseEntity.ok(personaResponse);        
    } 

*/

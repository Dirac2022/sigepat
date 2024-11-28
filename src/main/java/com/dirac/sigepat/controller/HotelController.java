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

/**
 * HotelController es un controlador REST que maneja peticiones
 * HTTP relacioandas con hoteles. Utiliza Spring MVC para manejar
 * solicitudes y obtener respuestas. Los métodos en el controlador
 * se encargan de mapear las solicitudes entrantes (GET, POST) y 
 * delegar loa lógica de negocio a la capa de servicio (HotelService)
 * @author mitch
 */


// @RestController: Controller especializado que devuelve resultados 
// en JSON o XML sin necesidad de una vista
// @RequestMapping: Define el prefijo de la ruta para todas 
// las solicitudes manejadas por este controlador
@RestController 
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    // Configura un logger para registrar mensajes de depuración y errores.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HotelService hotelService;

    // GetMapping() maneja las solicitudes HTTP GET a la ruta
    @GetMapping()
    public ResponseEntity<?> getHoteles() {
        
        List<HotelResponse> listaHotelesResponse = null;

        try {
            // Llama al servicio HotelService para 
            // obtener la lista de hoteles.
            listaHotelesResponse = hotelService.listHoteles();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaHotelesResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hoteles not found").build());
        }

        // Si la lista no esta vacia devuelve el status 200 OK
        // y los datos de los hoteles que el cuerpo de la respuesta
        return ResponseEntity.ok(listaHotelesResponse);
    }

    // @GetMapping("/find") maneja las solicitudes HTTP GET a la ruta /api/v1/hotel/find
    // Optional<HotelRequest> hotelRequest: Spring deserializa automáticamente la 
    // solicitud JSON a un objeto HotelRequest. 
    //Si el objeto es nulo o no se proporciona, se manejará correctamente.
    @GetMapping("/find")
    public ResponseEntity<?> findHotelById(@RequestBody Optional<HotelRequest> hotelRequest) {
        logger.info(">find" + hotelRequest.toString());
        HotelResponse hotelResponse;
        try {
            // Llama al servicio para obtener los detalles del hotel por su ID. 
            //El ID del hotel se obtiene desde el objeto HotelRequest.
            hotelResponse = hotelService.findHotel(hotelRequest.get().getIdHotel());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            // Devuelve un status 500
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (hotelResponse == null) {
            // Devuelve status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hotel not found").build());
        }
        
        // Devuelve status 200
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


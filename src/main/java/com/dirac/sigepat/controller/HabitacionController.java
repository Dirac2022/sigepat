/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.HabitacionRequest;
import com.dirac.sigepat.dto.HabitacionResponse;
import com.dirac.sigepat.dto.HotelRequest;
import com.dirac.sigepat.model.Hotel;
import com.dirac.sigepat.repository.HotelRepository;
import com.dirac.sigepat.service.HabitacionService;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/habitacion")
public class HabitacionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HabitacionService habitacionService;

    // GetMapping() maneja las solicitudes HTTP GET a la ruta
    @GetMapping()
    public ResponseEntity<?> getHabitaciones() {

        List<HabitacionResponse> listaHabitacionesResponse = null;

        try {

            listaHabitacionesResponse = habitacionService.listHabitaciones();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaHabitacionesResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Habitaciones not found").build());
        }

        // Si la lista no esta vacia devuelve el status 200 OK
        // y los datos de los hoteles que el cuerpo de la respuesta
        return ResponseEntity.ok(listaHabitacionesResponse);
    }

    // @GetMapping("/find") maneja las solicitudes HTTP GET a la ruta /api/v1/hotel/find
    // Optional<HabitacionRequest> habitacionRequest: Spring deserializa automáticamente la 
    // solicitud JSON a un objeto HabitacionRequest. 
    //Si el objeto es nulo o no se proporciona, se manejará correctamente.
    @GetMapping("/find")
    public ResponseEntity<?> findHabitacionById(@RequestBody Optional<HabitacionRequest> habitacionRequest) {
        // Recomendacion:
        // public ResponseEntity<?> findHotelById(@RequestBody HabitacionRequest habitacionRequest)
        logger.info(">find" + habitacionRequest.toString());
        HabitacionResponse habitacionResponse;
        try {
            // Llama al servicio para obtener los detalles del hotel por su ID. 
            //El ID del hotel se obtiene desde el objeto HotelRequest.
            habitacionResponse = habitacionService.findHabitacion(habitacionRequest.get().getIdHabitacion());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            // Devuelve un status 500
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (habitacionResponse == null) {
            // Devuelve status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Hotel not found").build());
        }

        // Devuelve status 200
        return ResponseEntity.ok(habitacionResponse);

    }

    @GetMapping("/findByHotel")
    public ResponseEntity<?> findHabitacionesByHotel(@RequestParam Long idHotel) {
        logger.info(">findHabitacionesByHotel " + idHotel);
        List<HabitacionResponse> habitacionResponse;

        if (idHotel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder().message("Hotel ID is required").build());
        }
        
        try {

            // Encuentra la entidad Hotel en el repositorio
            Hotel hotel = habitacionService.findHotelById(idHotel);

            // Llama al método que busca hoteles por ciudad
            habitacionResponse = habitacionService.findHabitacionesByHotel(hotel);

            if (habitacionResponse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("No habitacion found in the given hotel").build());
            }

            return ResponseEntity.ok(habitacionResponse);

        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/findByHotel")
    public ResponseEntity<?> findHabitacionesByHotel(@RequestBody Optional<HabitacionRequest> habitacionRequest) {
        logger.info(">findHabitacionesByHotel " + habitacionRequest.toString());
        List<HabitacionResponse> habitacionResponse;

        try {
            // Obtén el ID del hotel desde el request
            Long idHotel = habitacionRequest.map(HabitacionRequest::getHotel).orElse(null);

            if (idHotel == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.builder().message("Hotel ID is required").build());
            }

            // Encuentra la entidad Hotel en el repositorio
            Hotel hotel = habitacionService.findHotelById(idHotel);

            // Llama al método que busca hoteles por ciudad
            habitacionResponse = habitacionService.findHabitacionesByHotel(hotel);

            if (habitacionResponse.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("No habitacion found in the given hotel").build());
            }

            return ResponseEntity.ok(habitacionResponse);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
     */
}

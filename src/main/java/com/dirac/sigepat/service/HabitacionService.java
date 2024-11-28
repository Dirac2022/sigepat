/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.HabitacionRequest;
import com.dirac.sigepat.dto.HabitacionResponse;
import com.dirac.sigepat.model.Habitacion;
import com.dirac.sigepat.model.Hotel;
import com.dirac.sigepat.repository.HabitacionRepository;
import com.dirac.sigepat.repository.HotelRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {
    
        // Registra informacion sobre la ejecucion del codigo
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // Inyeccion de dependencias proporcionada por Spring
    // Se utiliza para asignar instancias de HabitacionRepository
    // y CiudadRespository a estas variables
    @Autowired
    HabitacionRepository habitacionRepository;
    
    @Autowired
    HotelRepository hotelRepository;
    
    
    /**
     * @return Devuelde una lista de respuesta de habitaciones
     */
    public List<HabitacionResponse> listHabitaciones() {
        
        return HabitacionResponse.fromEntities(habitacionRepository.findAll());
        
    }
    
    
    
    /**
     * Busca todos los tipo de habitaciones relacionados con un Hotel
     * @param hotel
     * @return Devuelve una lista de objetos HotelResponse
     */
    public List<HabitacionResponse> findHabitacionesByHotel(Hotel hotel) {

        return habitacionRepository.findByHotel(hotel).stream()
                .map(HabitacionResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    
    /**
     * Busca una hotel por su id
     * @param idHotel
     * @return
     */
    public Hotel findHotelById(Long idHotel) {
        
        // findById() metodo de CiudadRepository que busca una
        // ciudad por su id, devuelve un Optional<Ciudad>
        // orElseThrow Si la ciudad no se encuentra, lanza una excepcion
        return hotelRepository.findById(idHotel)
            .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }

    
   
    public HabitacionResponse findHabitacion(Long id) {
        // findById() metodo de HotelRepository para encontrar el hotel
        // por Id, devuelve un Optional<Hotel>
        // get() Extrae el valor del Optional, puede lanzar una excepcion
        // NoSuchElementException si el hotel con ese ID no existe
        return HabitacionResponse.fromEntity(habitacionRepository.findById(id).get());
    } 
    
    
    /**
     * Se encarga de insertar un nuevo hotel en la base de datos
     * usando los datos del HotelRequest
     * @param habitacionRequest
     * @return 
     */
    public HabitacionResponse insertHabitacion(HabitacionRequest habitacionRequest) {

        Long idHotel = habitacionRequest.getHotel();
        
        Hotel hotel = hotelRepository.findById(idHotel).get();
        
        // Registra un mensaje en los logs con los detalles de la
        // iudad seleccionada para el hotel
        logger.info("insertHabitacion-hotel" + hotel.toString());
        
        // Si la ciudad es null devuelve un HoteResponse vacio
        // este bloque no se ejecutara ya que se ejecutara una excepcion
        if (hotel == null)
            return new HabitacionResponse();
        
        // Creamos la nueva instancia de Hotel a partir
        // del objeto hotelRequest
        
        Habitacion habitacion = new Habitacion (
                habitacionRequest.getIdHabitacion(),
                habitacionRequest.getTipoHabitacion(),
                habitacionRequest.getPrecioDia(),
                hotel
        );
        
        habitacion = habitacionRepository.save(habitacion);
        
        HabitacionResponse habitacionResponse = HabitacionResponse.fromEntity(habitacion);
        
        return habitacionResponse;
    }
    
}

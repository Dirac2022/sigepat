/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.HotelRequest;
import com.dirac.sigepat.dto.HotelResponse;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.model.Hotel;
import com.dirac.sigepat.repository.CiudadRepository;
import com.dirac.sigepat.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service // Clase que contiene logica de negocio (bean)
public class HotelService {
    
    // Registra informacion sobre la ejecucion del codigo
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // Inyeccion de dependencias proporcionada por Spring
    // Se utiliza para asignar instancias de HotelRepository
    // y CiudadRespository a estas variables
    @Autowired
    HotelRepository hotelRepository;
    
    @Autowired
    CiudadRepository ciudadRepository;
    
    
    /**
     * @return Devuelde una lista de respuesta de hoteles
     */
    public List<HotelResponse> listHoteles() {
        // hotelRepository.findAll() llama al repositorio HotelRepository
        // para obtener todos los registros de hoteles de la base de datos
        // HotelResponse.fromEntities() es un metodo estatico que 
        // recibe una lista de entidades Hotel y las transforma en objetos
        // HotelResponse. Este tipo de conversion es util para separar la 
        // lógica de acceso a datos y la presentacion
        return HotelResponse.fromEntities(hotelRepository.findAll());
    }
    
    
    
    /**
     * Busca todos los hoteles relacionados con una ciudad
     * @param ciudad
     * @return Devuelve una lista de objetos HotelResponse
     */
    public List<HotelResponse> findHotelesByCiudad(Ciudad ciudad) {
        // stream() Convierte la lista de hoteles en un flujo (stream)
        // lo que permite aplicar funcionales como map
        // map() transforma cada entidad Hotel en un objeto HotelResponse
        // utilizando el metodo estatico fromEntity de HotelResponse
        // collect() Recoge los elementos del flujo de vuelta a una
        // lista de objetos HoteResponse
        return hotelRepository.findByCiudad(ciudad).stream()
                .map(HotelResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    
    /**
     * Busca una ciudad por su id
     * @param idCiudad
     * @return
     */
    public Ciudad findCiudadById(Long idCiudad) {
        
        // findById() metodo de CiudadRepository que busca una
        // ciudad por su id, devuelve un Optional<Ciudad>
        // orElseThrow Si la ciudad no se encuentra, lanza una excepcion
        return ciudadRepository.findById(idCiudad)
            .orElseThrow(() -> new IllegalArgumentException("City not found"));
    }

    
   
    public HotelResponse findHotel(Long id) {
        // findById() metodo de HotelRepository para encontrar el hotel
        // por Id, devuelve un Optional<Hotel>
        // get() Extrae el valor del Optional, puede lanzar una excepcion
        // NoSuchElementException si el hotel con ese ID no existe
        return HotelResponse.fromEntity(hotelRepository.findById(id).get());
    } 
    
    
    /**
     * Se encarga de insertar un nuevo hotel en la base de datos
     * usando los datos del HotelRequest
     * @param hotelRequest
     * @return 
     */
    public HotelResponse insertHotel(HotelRequest hotelRequest) {
        // Obtiene el ID de la ciudad desde el objeto
        // hotelRequest
        Long idCiudad = hotelRequest.getCiudad();
        
        // Busca la ciudad con el ID obtenido del hotelRequest
        // usa get() para obtener el valor del Optional, puede
        // lanzar una excepcion
        Ciudad ciudad = ciudadRepository.findById(idCiudad).get();
        
        // Registra un mensaje en los logs con los detalles de la
        // iudad seleccionada para el hotel
        logger.info("insertHotel-ciudad" + ciudad.toString());
        
        // Si la ciudad es null devuelve un HoteResponse vacio
        // este bloque no se ejecutara ya que se ejecutara una excepcion
        if (ciudad == null)
            return new HotelResponse();
        
        // Creamos la nueva instancia de Hotel a partir
        // del objeto hotelRequest
        Hotel hotel = new Hotel (
                hotelRequest.getIdHotel(),
                hotelRequest.getNombre(),
                hotelRequest.getDireccion(),
                hotelRequest.getClasificacion(),
                hotelRequest.getHabitIndDisponibles(),
                hotelRequest.getHabitIndDisponibles(),
                ciudad,
                hotelRequest.isCancelable(),
                hotelRequest.isModificable()
        );
        
        // save() guarda el hotel en la bd a traves del hotelRepository
        // En caso de existir el id, el metodo actualizara el registro
        // existente en la bd
        hotel = hotelRepository.save(hotel);
        
        // HotelResponse.fromEntity() toma hotel y lo conveirte a un
        // objeto HotelResponse
        HotelResponse hotelResponse = HotelResponse.fromEntity(hotel);
        
        return hotelResponse;
    }
}


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


@Service
public class HotelService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    HotelRepository hotelRepository;
    
    @Autowired
    CiudadRepository ciudadRepository;
    
    public List<HotelResponse> listHoteles() {
        return HotelResponse.fromEntities(hotelRepository.findAll());
    }
    
    public List<HotelResponse> findHotelesByCiudad(Ciudad ciudad) {
        return hotelRepository.findByCiudad(ciudad).stream()
                .map(HotelResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    public Ciudad findCiudadById(Long idCiudad) {
    return ciudadRepository.findById(idCiudad)
            .orElseThrow(() -> new IllegalArgumentException("City not found"));
}

    
   
    public HotelResponse findHotel(Long id) {
        return HotelResponse.fromEntity(hotelRepository.findById(id).get());
    } 
    
    
    public HotelResponse insertHotel(HotelRequest hotelRequest) {
        
        Long idCiudad = hotelRequest.getCiudad();
        Ciudad ciudad = ciudadRepository.findById(idCiudad).get();
        logger.info("insertHotel-ciudad" + ciudad.toString());
        
        if (ciudad == null)
            return new HotelResponse();
        
        Hotel hotel = new Hotel (
                hotelRequest.getIdHotel(),
                hotelRequest.getNombre(),
                hotelRequest.getDireccion(),
                hotelRequest.getClasificacion(),
                hotelRequest.getHabitIndDisponibles(),
                hotelRequest.getHabitIndDisponibles(),
                ciudad
        );
        
        hotel = hotelRepository.save(hotel);
        HotelResponse hotelResponse = HotelResponse.fromEntity(hotel);
        return hotelResponse;
    }
}


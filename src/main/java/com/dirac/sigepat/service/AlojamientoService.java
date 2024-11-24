/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.repository.AlojamientoRepository;
import com.dirac.sigepat.model.Alojamiento;
import com.dirac.sigepat.model.Habitacion;
import com.dirac.sigepat.repository.HabitacionRepository;
import com.dirac.sigepat.repository.HotelRepository;
import com.dirac.sigepat.dto.AlojamientoRequest;
import com.dirac.sigepat.dto.AlojamientoResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlojamientoService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired // Se esta aplicando el patron inyección de dependencia
    AlojamientoRepository alojamientoRepository;
    
    @Autowired
    HotelRepository hotelRepository;
    
    @Autowired
    HabitacionRepository habitacionRepository;
    
    
    public List<AlojamientoResponse> listAlojamientos() {
        return AlojamientoResponse.fromEntities(alojamientoRepository.findAll());
    }
    
    public AlojamientoResponse findAlojamiento(Long id) {
        return AlojamientoResponse.fromEntity(alojamientoRepository.findById(id).get());
    }
    
}

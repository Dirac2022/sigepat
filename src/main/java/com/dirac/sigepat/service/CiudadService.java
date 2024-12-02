/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.CiudadResponse;
import com.dirac.sigepat.repository.CiudadRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CiudadService {
    
     // Registra informacion sobre la ejecucion del codigo
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    CiudadRepository ciudadRepository;
    
    
    public List<CiudadResponse> listCiudades() {
        return CiudadResponse.fromEntities(ciudadRepository.findAll());
    }
    
    public CiudadResponse findCiudad(Long id) {
        return CiudadResponse.fromEntity(ciudadRepository.findById(id).get());
    } 
    
}

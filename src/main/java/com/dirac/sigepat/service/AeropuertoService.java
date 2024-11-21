/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

/**
 *
 * @author Andre
 *//*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.dirac.sigepat.repository.AeropuertoRepository;
import com.dirac.sigepat.model.Aeropuerto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AeropuertoService {
    
    @Autowired // Se esta aplicando el patron inyección de dependencia
    AeropuertoRepository aeropuertoRepository;
    
    public List<Aeropuerto> getAeropuertos() {
        return aeropuertoRepository.findAll();
    }
    
    /**
     * El tipo Optional es seguro 
     * @param id
     * @return 
     */
    
    // GET
    public Optional<Aeropuerto> findAeropuertoById(Long id) {
        return aeropuertoRepository.findById(id);
    }
    
    // POST
    public Aeropuerto insertAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }
    
    // PUT
    public Aeropuerto updateAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }
    
    // DELETE
    public void deleteAeropuerto(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.repository.AlojamientoRepository;
import com.dirac.sigepat.model.Alojamiento;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlojamientoService {
    
    @Autowired // Se esta aplicando el patron inyección de dependencia
    AlojamientoRepository alojamientoRepository;
    
    public List<Alojamiento> getAlojamientos() {
        return alojamientoRepository.findAll();
    }
    
    /**
     * El tipo Optional es seguro 
     * @param id
     * @return 
     */
    
    // GET
    public Optional<Alojamiento> getAlojamiento(Long id) {
        return alojamientoRepository.findById(id);
    }
    
    // POST
    public Alojamiento insertAlojamiento(Alojamiento alojamiento) {
        return alojamientoRepository.save(alojamiento);
    }
    
    // PUT
    public Alojamiento updateAlojamiento(Alojamiento alojamiento) {
        return alojamientoRepository.save(alojamiento);
    }
    
    // DELETE
    public void deleteAlojamiento(Long id) {
        alojamientoRepository.deleteById(id);
    }
}

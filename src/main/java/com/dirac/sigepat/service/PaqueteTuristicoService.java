/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.AlojamientoResponse;
import com.dirac.sigepat.dto.PaqueteTuristicoRequest;
import com.dirac.sigepat.dto.PaqueteTuristicoResponse;
import com.dirac.sigepat.model.Alojamiento;
import com.dirac.sigepat.model.PaqueteTuristico;
import com.dirac.sigepat.model.Vuelo;
import com.dirac.sigepat.repository.AlojamientoRepository;
import com.dirac.sigepat.repository.PaqueteTuristicoRepository;
import com.dirac.sigepat.repository.VueloRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteTuristicoService {
    
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PaqueteTuristicoRepository paqueteTuristicoRepository;
    
    @Autowired // Se esta aplicando el patron inyección de dependencia
    AlojamientoRepository alojamientoRepository;
    
    @Autowired
    VueloRepository vueloRepository;
    
        
    public List<PaqueteTuristicoResponse> listPaqueteTuristico() {
        return PaqueteTuristicoResponse.fromEntities(paqueteTuristicoRepository.findAll());
    }
    
   
    public PaqueteTuristicoResponse insertPaqueteTuristico(PaqueteTuristicoRequest paqueteTuristicoRequest) {
        
        Long idAlojamiento = paqueteTuristicoRequest.getAlojamiento();
        Long idVuelo = paqueteTuristicoRequest.getVuelo();
        
        
        Alojamiento alojamiento = alojamientoRepository.findById(idAlojamiento).get();
        Vuelo vuelo = vueloRepository.findById(idVuelo).get();
        
        logger.info(">insertPaqueteTuristico" + "Alojamiento: " +
                alojamiento.toString() + "/Vuelo: " + vuelo.toString());
        
        double precio = alojamiento.getPrecio() + vuelo.getPrecio();
        
        PaqueteTuristico paqueteTuristico = new PaqueteTuristico (
                null,
                precio,
                alojamiento,
                vuelo
        );
       
       paqueteTuristico = paqueteTuristicoRepository.save(paqueteTuristico);
       
       PaqueteTuristicoResponse paqueteTuristicoResponse = PaqueteTuristicoResponse.fromEntity(paqueteTuristico);
       
       return paqueteTuristicoResponse;        
   }
    
}

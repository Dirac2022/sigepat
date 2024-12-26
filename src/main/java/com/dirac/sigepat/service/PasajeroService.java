/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.PasajeroRequest;
import com.dirac.sigepat.dto.PasajeroResponse;
import com.dirac.sigepat.model.Pais;
import com.dirac.sigepat.model.Pasajero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dirac.sigepat.repository.PasajeroRepository;
import com.dirac.sigepat.repository.TipoDocumentoRepository;
import com.dirac.sigepat.model.TipoDocumento;
import com.dirac.sigepat.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PasajeroService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PasajeroRepository pasajeroRepository;
    
    @Autowired
    TipoDocumentoRepository tipoDocRepository;
    
    @Autowired
    PaisRepository paisRepository;
    public PasajeroResponse insertPasajero(PasajeroRequest pasajeroRequest) {
    
        Long idTipoDocumento = pasajeroRequest.getTipoDoc();
        TipoDocumento tipoDoc = tipoDocRepository.findById(idTipoDocumento).get(); 
        
        Long idPais = pasajeroRequest.getPaisResidencia();
        Pais pais = paisRepository.findById(idPais).get();
        logger.info(">insert Pasajero" + "tipoDoc" + tipoDoc.toString());      
        Pasajero pasajero = new Pasajero(
                null,
                pasajeroRequest.getNombres(),
                pasajeroRequest.getApellidos(),
                pais,
                tipoDoc,
                pasajeroRequest.getNroDoc(),
                pasajeroRequest.getFechaNac(),
                pasajeroRequest.getSexo()
        );
        pasajero = pasajeroRepository.save(pasajero);
        PasajeroResponse pasajeroResponse = PasajeroResponse.fromEntity(pasajero);
        return pasajeroResponse;  
    }
    
}

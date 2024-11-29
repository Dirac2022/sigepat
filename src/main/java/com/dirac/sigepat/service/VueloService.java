package com.dirac.sigepat.service;


import com.dirac.sigepat.dto.VueloRequest;
import com.dirac.sigepat.dto.VueloResponse;
import com.dirac.sigepat.model.Aerolinea;
import com.dirac.sigepat.model.Aeropuerto;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.repository.VueloRepository;
import com.dirac.sigepat.model.Vuelo;
import com.dirac.sigepat.repository.AerolineaRepository;
import com.dirac.sigepat.repository.AeropuertoRepository;
import com.dirac.sigepat.repository.CiudadRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VueloService {

    // Registra informacion sobre la ejecucion del codigo
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // Inyeccion de dependencias proporcionada por Spring
    // Se utiliza para asignar instancias de HotelRepository
    // y CiudadRespository a estas variables
    @Autowired
    VueloRepository vueloRepository;
    
    @Autowired
    CiudadRepository ciudadRepository;
    
    @Autowired
    AeropuertoRepository aeropuertoRepository;
    
    @Autowired
    AerolineaRepository aerolineaRepository;
    
    
    /**
     * @return Devuelde una lista de respuesta de hoteles
     */
    public List<VueloResponse> listVuelos() {
        return VueloResponse.fromEntities(vueloRepository.findAll());
    }
     
    
   
    public VueloResponse findVuelo(Long id) {
        return VueloResponse.fromEntity(vueloRepository.findById(id).get());
    } 
    
    
    public List<VueloResponse> findVuelosByCiudades(Long idCiudadOrigen, Long idCiudadDestino) {
        Ciudad ciudadOrigen = ciudadRepository.findById(idCiudadOrigen).get();
        Ciudad ciudadDestino = ciudadRepository.findById(idCiudadDestino).get();
        
        List<Vuelo> vuelos = vueloRepository.findAll().stream()
                .filter(vuelo -> vuelo.getCuidadOrigen().equals(ciudadOrigen) 
                        && vuelo.getCiudadDestino().equals(ciudadDestino))
                .collect(Collectors.toList());
        
        return VueloResponse.fromEntities(vuelos);
    }
    
    
    
    
    
    
    
    
    public VueloResponse insertVuelo(VueloRequest vueloRequest) {

        Long idCiudadOrigen = vueloRequest.getCuidadOrigen();
        Long idCiudadDestino = vueloRequest.getCiudadDestino();
        
        Long idAeropuertoIda = vueloRequest.getAeropuertoIda();
        Long idAeropuertoRegreso = vueloRequest.getAeropuertoRegreso();
        
        Long idAerolinea = vueloRequest.getAerolinea();
        

        Ciudad ciudadOrigen = ciudadRepository.findById(idCiudadOrigen).get();
        Ciudad ciudadDestino = ciudadRepository.findById(idCiudadDestino).get();
        
        Aeropuerto aeropuertoIda = aeropuertoRepository.findById(idAeropuertoIda).get();
        Aeropuerto aeropuertoRegreso = aeropuertoRepository.findById(idAeropuertoRegreso).get();
        
        Aerolinea aerolinea = aerolineaRepository.findById(idAerolinea).get();
        
        logger.info("insertVuelo-ciudadOrigen-ciudadDestino" + 
                ciudadOrigen.toString() + "- " + ciudadDestino.toString());
        

        if (ciudadOrigen == null || ciudadDestino == null)
            return new VueloResponse();
        
        Vuelo vuelo = new Vuelo(
                vueloRequest.getIdVuelo(), 
                vueloRequest.getFechaHoraIda(), 
                vueloRequest.getFechaHoraRegreso(), 
                ciudadOrigen, 
                aeropuertoIda, 
                ciudadDestino, 
                aeropuertoRegreso, 
                aerolinea
        );
        

        vuelo = vueloRepository.save(vuelo);

        VueloResponse vueloResponse = VueloResponse.fromEntity(vuelo);
        
        return vueloResponse;
    }
}

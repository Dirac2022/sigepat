package com.dirac.sigepat.service;

import com.dirac.sigepat.repository.AutoRepository;
import com.dirac.sigepat.model.Auto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {

    @Autowired // Se está aplicando el patrón inyección de dependencia
    AutoRepository autoRepository;

    public List<Auto> getAutos() {
        return autoRepository.findAll();
    }

    /**
     * El tipo Optional es seguro 
     * @param id
     * @return 
     */
    
    // GET
    public Optional<Auto> findAutoById(Long id) {
        return autoRepository.findById(id);
    }

    // POST
    public Auto insertAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    // PUT
    public Auto updateAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    // DELETE
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }
}

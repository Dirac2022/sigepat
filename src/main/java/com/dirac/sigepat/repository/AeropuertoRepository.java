/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dirac.sigepat.repository;

import com.dirac.sigepat.model.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andre
 */
@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long>{
    
}

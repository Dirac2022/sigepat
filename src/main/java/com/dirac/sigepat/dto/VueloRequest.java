/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Aerolinea;
import com.dirac.sigepat.model.Aeropuerto;
import com.dirac.sigepat.model.Ciudad;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VueloRequest {
    
    private Long idVuelo;
    private LocalDateTime fechaHoraIda;
    private LocalDateTime fechaHoraRegreso;
    private Long cuidadOrigen;
    private Long aeropuertoIda;
    private Long ciudadDestino;
    private Long aeropuertoRegreso;
    private Long aerolinea;
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Aerolinea;
import com.dirac.sigepat.model.Aeropuerto;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.model.Vuelo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VueloResponse {
    
    private Long idVuelo;
    private double precio;
    private LocalDateTime fechaHoraIda;
    private LocalDateTime fechaHoraRegreso;
    private Ciudad cuidadOrigen;
    private Aeropuerto aeropuertoIda;
    private Ciudad ciudadDestino;
    private Aeropuerto aeropuertoRegreso;
    private Aerolinea aerolinea;
    
    public static VueloResponse fromEntity(Vuelo vuelo) {
        return VueloResponse.builder()
                .idVuelo(vuelo.getIdVuelo())
                .precio(vuelo.getPrecio())
                .fechaHoraIda(vuelo.getFechaHoraIda())
                .fechaHoraRegreso(vuelo.getFechaHoraRegreso())
                .cuidadOrigen(vuelo.getCuidadOrigen())
                .aeropuertoIda(vuelo.getAeropuertoIda())
                .ciudadDestino(vuelo.getCiudadDestino())
                .aeropuertoRegreso(vuelo.getAeropuertoRegreso())
                .aerolinea(vuelo.getAerolinea())
                .build();
    }
    
    public static List<VueloResponse> fromEntities(List<Vuelo> vuelo) {
        return vuelo.stream()
                .map(VueloResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
}
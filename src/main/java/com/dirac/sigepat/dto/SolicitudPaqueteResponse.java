/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.model.SolicitudPaquete;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudPaqueteResponse {
    
    private Long idSolicitudPaquete;
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private LocalDate fechaIda;
    private LocalDate fechaRegreso;
    private int nroHabitaciones;
    private int nroPasajeros;
    
    public static SolicitudPaqueteResponse fromEntity(SolicitudPaquete solicitudPaquete) {
        return SolicitudPaqueteResponse.builder()
                .idSolicitudPaquete(solicitudPaquete.getIdSolicitudPaquete())
                .ciudadOrigen(solicitudPaquete.getCiudadOrigen())
                .ciudadDestino(solicitudPaquete.getCiudadDestino())
                .fechaIda(solicitudPaquete.getFechaIda())
                .fechaRegreso(solicitudPaquete.getFechaRegreso())
                .nroHabitaciones(solicitudPaquete.getNroHabitaciones())
                .nroPasajeros(solicitudPaquete.getNroPasajeros())
                .build();
    }
    
    public static List<SolicitudPaqueteResponse> fromEntities(List<SolicitudPaquete> solicitudPaquete) {
        return solicitudPaquete.stream()
                .map(SolicitudPaqueteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

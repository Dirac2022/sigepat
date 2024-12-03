/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Aerolinea;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AerolineaResponse {

    private Long idAerolinea;
    private String nombre;

    public static AerolineaResponse fromEntity(Aerolinea aerolinea) {
        return AerolineaResponse.builder()
                .idAerolinea(aerolinea.getIdAerolinea())
                .nombre(aerolinea.getNombre())
                .build();
    }

    public static List<AerolineaResponse> fromEntities(List<Aerolinea> aerolinea) {
        return aerolinea.stream()
                .map(AerolineaResponse::fromEntity)
                .collect(Collectors.toList());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Aeropuerto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Ciudad;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AeropuertoResponse {

    private Long idAeropuerto;
    private String nombre;
    private Ciudad ciudad;

    public static AeropuertoResponse fromEntity(Aeropuerto aeropuerto) {
        return AeropuertoResponse.builder()
                .idAeropuerto(aeropuerto.getIdAeropuerto())
                .nombre(aeropuerto.getNombre())
                .ciudad(aeropuerto.getCiudad())
                .build();
    }

    public static List<AeropuertoResponse> fromEntities(List<Aeropuerto> aeropuerto) {
        return aeropuerto.stream()
                .map(AeropuertoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

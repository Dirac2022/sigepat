/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Pais;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadResponse {

    private Long idCiudad;
    private String nombre;
    private Pais pais;

    public static CiudadResponse fromEntity(Ciudad ciudad) {
        return CiudadResponse.builder()
                .idCiudad(ciudad.getIdCiudad())
                .nombre(ciudad.getNombre())
                .pais(ciudad.getPais())
                .build();
    }

    public static List<CiudadResponse> fromEntities(List<Ciudad> ciudad) {
        return ciudad.stream()
                .map(CiudadResponse::fromEntity)
                .collect(Collectors.toList());

    }

}

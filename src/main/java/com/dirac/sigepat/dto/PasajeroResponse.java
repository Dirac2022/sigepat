/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Pais;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dirac.sigepat.model.TipoDocumento;
import com.dirac.sigepat.model.Pasajero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroResponse {
    
    private Long idPasajero;
    private String nombres;
    private String apellidos;
    private Pais paisResidencia;
    private TipoDocumento tipoDoc;
    private String nroDoc;
    private LocalDate fechaNac;
    private String sexo;
    
    public static PasajeroResponse fromEntity(Pasajero pasajero) {
        return PasajeroResponse.builder()
                .idPasajero(pasajero.getIdPasajero())
                .nombres(pasajero.getNombres())
                .apellidos(pasajero.getApellidos())
                .paisResidencia(pasajero.getPaisResidencia())
                .tipoDoc(pasajero.getTipoDoc())
                .nroDoc(pasajero.getNroDoc())
                .fechaNac(pasajero.getFechaNac())
                .sexo(pasajero.getSexo())
                .build();
    }
    
    public static List<PasajeroResponse> fromEntities(List<Pasajero> pasajeros) {
        return pasajeros.stream()
                .map(PasajeroResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

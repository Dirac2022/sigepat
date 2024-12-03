/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Aerolinea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.PaqueteTuristico;
import com.dirac.sigepat.model.Alojamiento;    
import com.dirac.sigepat.model.Vuelo;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteTuristicoResponse {

    private Long idPaqueteTuristico;
    private double precioTotal;
    private Alojamiento alojamiento;
    private Vuelo vuelo;
    
        public static PaqueteTuristicoResponse fromEntity(PaqueteTuristico paqueteTuristico) {
        return PaqueteTuristicoResponse.builder()
                .idPaqueteTuristico(paqueteTuristico.getIdPaqueteTuristico())
                .precioTotal(paqueteTuristico.getPrecioTotal())
                .alojamiento(paqueteTuristico.getAlojamiento())
                .vuelo(paqueteTuristico.getVuelo())
                .build();
    }

    public static List<PaqueteTuristicoResponse> fromEntities(List<PaqueteTuristico> paqueteTuristico) {
        return paqueteTuristico.stream()
                .map(PaqueteTuristicoResponse::fromEntity)
                .collect(Collectors.toList());
    }

}

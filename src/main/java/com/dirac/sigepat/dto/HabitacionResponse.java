/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Habitacion;
import com.dirac.sigepat.model.Hotel;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionResponse {
    
    private Long idHabitacion;
    private String tipoHabitacion;
    private double precioDia;
    private Hotel hotel;
    
    public static HabitacionResponse fromEntity(Habitacion habitacion) {
        return HabitacionResponse.builder()
                .idHabitacion(habitacion.getIdHabitacion())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .precioDia(habitacion.getPrecioDia())
                .hotel(habitacion.getHotel())
                .build();
    }
    
    public static List<HabitacionResponse> fromEntities(List<Habitacion> habitacion) {
        return habitacion.stream()
                .map(HabitacionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}


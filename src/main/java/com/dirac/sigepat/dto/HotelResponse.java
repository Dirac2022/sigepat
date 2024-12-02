/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import com.dirac.sigepat.model.Hotel;
import com.dirac.sigepat.model.Ciudad;
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
public class HotelResponse {
    
    private Long idHotel;
    private String nombre;
    private String direccion;
    private int clasificacion;
    private int habitIndDisponibles;
    private int habitDobDisponibles;
    private Ciudad ciudad;
    private boolean cancelable;
    private boolean modificable;
    
    public static HotelResponse fromEntity(Hotel hotel) {
        return HotelResponse.builder()
                .idHotel(hotel.getIdHotel())
                .nombre(hotel.getNombre())
                .direccion(hotel.getDireccion())
                .clasificacion(hotel.getClasificacion())
                .habitIndDisponibles(hotel.getHabitIndDisponibles())
                .habitDobDisponibles(hotel.getHabitDobDisponibles())
                .ciudad(hotel.getCiudad())
                .cancelable(hotel.isCancelable())
                .modificable(hotel.isModificable())
                .build();
    }
    
    public static List<HotelResponse> fromEntities(List<Hotel> hotel) {
        return hotel.stream()
                .map(HotelResponse::fromEntity)
                .collect(Collectors.toList());
    }
        
}


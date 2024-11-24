/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Alojamiento;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlojamientoResponse {
    /**
     * Se usa para devolver la informacion requerida de alguna petición
     */
    private Long idAlojamiento;    
    private double precio;
    private boolean cancelable;
    private boolean modificable;
    private String nombre;
    private String ciudad;
    private String direccion;
    private int clasificacion;
    private int habIndDisponibles;    
    private int habDobDisponibles;
    
    public static AlojamientoResponse fromEntity(Alojamiento alojamiento) {
        return AlojamientoResponse.builder()
                .idAlojamiento(alojamiento.getIdAlojamiento())
                .precio(alojamiento.getPrecio())
                .cancelable(alojamiento.isCancelable())
                .modificable(alojamiento.isModificable())
                .nombre(alojamiento.getNombre())
                .ciudad(alojamiento.getCiudad())
                .direccion(alojamiento.getDireccion())
                .clasificacion(alojamiento.getClasificacion())
                .habIndDisponibles(alojamiento.getHabIndDisponibles())
                .habDobDisponibles(alojamiento.getHabDobDisponibles())
                .build();
    }
    
    public static List<AlojamientoResponse> fromEntities(List<Alojamiento> alojamiento) {
        return alojamiento.stream()
                .map(AlojamientoResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
}




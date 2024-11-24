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
    
}

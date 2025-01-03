/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlojamientoRequest {
    
    private Long idAlojamiento; 
    private double precio;
    private boolean cancelable;
    private boolean modificable;
    private int noches;
    private Long hotel;
    private Long habitacion;
    
}


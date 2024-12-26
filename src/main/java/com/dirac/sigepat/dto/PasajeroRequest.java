/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroRequest {
    
    private Long idPasajero;
    private String nombres;
    private String apellidos;
    private Long paisResidencia;
    private Long tipoDoc;
    private String nroDoc;
    private LocalDate fechaNac;
    private String sexo;
}

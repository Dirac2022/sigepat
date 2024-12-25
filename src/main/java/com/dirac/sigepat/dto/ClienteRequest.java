/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    
    private Long idCliente; 
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private Date fechaNac;
    private String sexo;
    private String email;
    private String numCelular;
    
}

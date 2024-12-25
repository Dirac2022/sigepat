/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dirac.sigepat.model.Cliente;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    
    private Long idCliente; 
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private Date fechaNac;
    private String sexo;
    private String email;
    private String numCelular;
    private String passwordliente;
    
    public static ClienteResponse fromEntity(Cliente cliente) {
        return ClienteResponse.builder()
                .idCliente(cliente.getIdCliente())
                .nombres(cliente.getNombres())
                .apePaterno(cliente.getApePaterno())
                .apeMaterno(cliente.getApePaterno())
                .fechaNac(cliente.getFechaNac())
                .sexo(cliente.getSexo())
                .email(cliente.getEmail())
                .numCelular(cliente.getNumCelular())
                .build();
    }
    
    public static List<ClienteResponse> fromEntities(List<Cliente> cliente) {
        return cliente.stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tarjeta")
public class Tarjeta {
    
    @Id
    @Column(name = "nro_tarjeta", nullable = false)
    private String nroTarjeta;
    
    @Column(name = "titular", nullable = false)
    private String titular;
    
    @Column(name = "nro_doc_titular", nullable = false)
    private String nroDocTitular;
    
    @Column(name = "fecha_venc", nullable = false)
    private String fechaVenc;
            
    @Column(name = "cod_seguridad", nullable = false)
    private String codSeguridad;
            
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pasajero")
public class Pasajero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nombres", nullable = false)
    private String nombres;
    
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    
    @Column(name = "pais_residencia", nullable = false)
    private String paisResidencia;
    
    @Column(name = "tipo_doc", nullable = false)
    private String tipoDoc;
    
    @Column(name = "nro_doc", nullable = false)
    private String nroDoc;
    
    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "sexo", nullable = false)
    private String sexo;
    
    
}

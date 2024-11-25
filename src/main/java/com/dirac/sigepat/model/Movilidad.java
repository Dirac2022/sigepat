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
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movilidad")
public class Movilidad extends Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;
    
    @Column(name="fechahora_ida", nullable=false)
    private LocalDateTime fechahoraIda;
    
    @Column(name="fechahora_vuelta", nullable=false)
    private LocalDateTime fechahoraVuelta;
    
}

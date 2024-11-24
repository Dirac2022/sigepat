/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vuelo")
public class Vuelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_vuelo")
    private Long nroVuelo;
    @Column(name = "precio")
    private Long Precio;
    @Column(name = "cancelable")
    private boolean Cancelable;
    @Column(name = "modificable")
    private boolean Modificable;
    @Column(name = "lugar_origen")
    private String lugarOrigen;
    @Column(name = "lugar_destino")
    private String lugarDestino;
    @Column(name = "fecha_hora_ida")
    private String fechaHoraIda;
    @Column(name = "fecha_origen_regreso")
    private String fechaOrigenRegreso;
    @Column(name = "clase")
    private Long Clase;
    @Column(name = "aerolinea")
    private boolean Aerolinea;
}
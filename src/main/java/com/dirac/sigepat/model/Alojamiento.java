/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alojamiento")
public class Alojamiento extends Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alojamiento")
    private Long idAlojamiento;
    
    @Column(name = "precio")
    private double precio;
    
    @Column(name = "cancelable", nullable = false)
    private boolean cancelable;
    
    @Column(name = "modificable", nullable = false)
    private boolean modificable;

    @Column(name = "noches")
    private int noches;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel", referencedColumnName = "id_hotel")
    private Hotel hotel;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "habitacion", referencedColumnName = "id_habitacion")
    private Habitacion habitacion;
}


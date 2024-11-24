/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hotel")
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_hotel", nullable = false)
    private Long idHotel;
    
    @Column(name="nombre", nullable = false)
    private String nombre;
    
    @Column(name="direccion", nullable = false)
    private String direccion;
    
    @Column(name="clasificacion", nullable = false)
    private int clasificacion;
    
    @Column(name="habit_ind_disponibles", nullable = false)
    private int habitIndDisponibles;
    
    @Column(name="habit_dob_disponibles", nullable = false)
    private int habitDobDisponibles;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;
}

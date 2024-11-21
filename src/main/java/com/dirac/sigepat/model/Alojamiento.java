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
@Table(name="alojamiento")
public class Alojamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "precio")
    private double precio;
    @Column(name = "cancelable")
    private boolean cancelable;
    @Column(name = "modificable")
    private boolean modificable;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "clasificacion")
    private int clasificacion;
    @Column(name = "habitacion_ind_disponible")
    private int habIndDisponibles;
    @Column(name = "habitacion_dob_disponible")
    private int habDobDisponibles;
}

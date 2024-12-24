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
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="paquete_turistico")
public class PaqueteTuristico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete_turistico", nullable = false)
    private Long idPaqueteTuristico;
    
    @Column(name = "precio_total", nullable = false)
    private double precioTotal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alojamiento", referencedColumnName = "id_alojamiento")
    private Alojamiento alojamiento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vuelo", referencedColumnName = "id_vuelo")
    private Vuelo vuelo;
    
    @Column(name="email", nullable = false)
    private String email;
    
}

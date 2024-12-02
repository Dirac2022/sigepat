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
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitud_paquete")
public class SolicitudPaquete {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_paquete", nullable = false)
    private Long idSolicitudPaquete;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad_origen", referencedColumnName = "id_ciudad")
    private Ciudad ciudadOrigen;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad_destino", referencedColumnName = "id_ciudad")
    private Ciudad ciudadDestino;
    
    @Column(name = "fecha_ida", nullable = false)
    private LocalDate fechaIda;
    
    @Column(name = "fecha_regreso", nullable = false)
    private LocalDate fechaRegreso;
    
    @Column(name = "nro_habitaciones", nullable = false)
    private int nroHabitaciones;
    
    @Column(name = "nro_pasajeros", nullable = false)
    private int nroPasajeros;
}

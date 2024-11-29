/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vuelo")
public class Vuelo extends Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Long idVuelo;

    @Column(name = "fechahora_ida")
    private LocalDateTime fechaHoraIda;

    @Column(name = "fechahora_regreso")
    private LocalDateTime fechaHoraRegreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad_origen", referencedColumnName = "id_ciudad")
    private Ciudad cuidadOrigen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aeropuerto_ida", referencedColumnName = "id_aeropuerto")
    private Aeropuerto aeropuertoIda;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudad_destino", referencedColumnName = "id_ciudad")
    private Ciudad ciudadDestino;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aeropuerto_regreso", referencedColumnName = "id_aeropuerto")
    private Aeropuerto aeropuertoRegreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aerolinea", referencedColumnName = "id_aerolinea")
    private Aerolinea aerolinea;
}


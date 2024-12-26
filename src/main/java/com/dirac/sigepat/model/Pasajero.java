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
import lombok.Data;
import lombok.NoArgsConstructor;

import com.dirac.sigepat.model.TipoDocumento;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pasajero")
public class Pasajero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero", nullable = false)
    private Long idPasajero;
    
    @Column(name = "nombres", nullable = false)
    private String nombres;
    
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pais_residencia")
    private Pais paisResidencia;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_doc", referencedColumnName = "id_tipo_documento")
    private TipoDocumento tipoDoc;
    
    @Column(name = "nro_doc", nullable = false)
    private String nroDoc;
    
    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "sexo", nullable = false)
    private String sexo;
    
}

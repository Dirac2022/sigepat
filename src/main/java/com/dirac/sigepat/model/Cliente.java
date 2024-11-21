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
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 *
 * @author mitch
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long idCliente;
    @Column(name="nombres")
    private String nombres;
    @Column(name="apellido_paterno")
    private String apePaterno;
    @Column(name="apellido_materno")
    private String apeMaterno;
    @Column(name="fecha_nac")
    private Date fechaNac;
    @Column(name="sexo")
    private String sexo;
    @Column(name="email")
    private String email;
    @Column(name="numcelular")
    private String numCelular;
    @Column(name="password_cliente")
    private String passwordliente;
    
}

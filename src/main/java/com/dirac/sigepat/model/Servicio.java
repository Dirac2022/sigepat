/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass // Los campos se heredan, pero cada clase define su propia tabla o estrategia de mapeo.
public abstract class Servicio {

    @Column(name = "precio")
    private double precio;

    @Column(name = "cancelable")
    private boolean cancelable;

    @Column(name = "modificable")
    private boolean modificable;
}


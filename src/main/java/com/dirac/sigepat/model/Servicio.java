/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass // Permite compartir atributos pero no genera tabla.
public abstract class Servicio {

    private double precio;
    private boolean cancelable;
    private boolean modificable;
}


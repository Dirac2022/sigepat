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


// @Data: Lombok, genera automaticamente los metodos getters, setters, 
// toString(), equals() y hashCode()
// @Entity:  Marca la clase como una entidad de JPA, 
// esta mapeada a una tabla de base de datos
// @Builder: Lombok, permite la creacion de objetos Hotel mediante un patron 

@Data 
@Entity
@Builder 
@NoArgsConstructor // Genera constructores sin parametros
@AllArgsConstructor // Genera constructores con todos los parametros
@Table(name="hotel") // De JPA, especifica el nombre de la tabla en la base de datos
public class Hotel {
    
    // @Id: Indica que el campo es una primary key
    // @GeneratedValue: // Indica que la bd es la encargada de generar el valor 
    // del identificador automaticamente
    // @Column: Asocia el campo de clase con la columna especificada en 
    // name de la tabla. nullable indica que esta columna no puede
    // ser NULL
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_hotel", nullable = false) // 
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
    
    // @ManyToOneDefine una relacion de muchos a uno entre Hotel y Ciudad.
    // fetch = FetchType.EAGER indica que la ciudad asociada se cargara
    // de manera inmediata (eager fetching). Cuando se carga un hotel, 
    // Spring tambien cargara su ciudad automaticamente
    // @JoinColumn especifica la columna en la tabla hotel, que se
    // usara para la relacion con la tabla ciudad. La columna ciudad en hotel
    // hace referencia a la columna id_ciudad en la tabla ciudad
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;
    
    @Column(name = "cancelable", nullable = false)
    private boolean cancelable;
    
    @Column(name = "modificable", nullable = false)
    private boolean modificable;
}

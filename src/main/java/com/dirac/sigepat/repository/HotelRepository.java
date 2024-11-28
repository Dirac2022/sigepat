/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dirac.sigepat.repository;

import com.dirac.sigepat.model.Ciudad;
import com.dirac.sigepat.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Esta anotacion ayuda a Spring a identificar que debe manejar la
// capa de acceso a datos de esta clase. (Aunque no es necesario
// es una buena practica)


// JpaRepository<Hotel, Long>, es una interfaz proporcionada por 
// Spring Data JPA. HotelRepository hereda metodos para realizar
// operaciones CRUD (Create, Read, Update, Delete) sobre la 
// entidad Hotel sin necesidad de implementarlos manualmente.
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    // Es un metodo de consulta personalido que busca
    // todos los hoteles asociados a una ciudad especifica. Este metodo sigue
    // la convencion de Spring Data JPA, que puede crear consultas
    // SQL basadas en nonmbres de metodos.
    // findBy indica que es una consulta para buscar
    // Ciudad es el nombre del atributo de la clase Hotel que se
    // utilizara en la consulta
    List<Hotel> findByCiudad(Ciudad ciudad);
}
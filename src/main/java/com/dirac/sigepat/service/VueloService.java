package com.dirac.sigepat.service;

import com.dirac.sigepat.repository.VueloRepository;
import com.dirac.sigepat.model.Vuelo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Gestiona las llamadas al repositorio
 * para la entidad Vuelo.
 */
@Service
public class VueloService {

    @Autowired // Se está aplicando el patrón inyección de dependencia
    VueloRepository vueloRepository;

    /**
     * Obtiene todos los vuelos almacenados en la base de datos.
     * @return Lista de vuelos
     */
    public List<Vuelo> getVuelos() {
        return vueloRepository.findAll();
    }

    /**
     * Busca un vuelo por su ID de forma segura utilizando Optional.
     * @param id Identificador del vuelo
     * @return Vuelo encontrado o vacío si no existe
     */
    public Optional<Vuelo> getVuelo(Long id) {
        return vueloRepository.findById(id);
    }

    /**
     * Inserta un nuevo vuelo en la base de datos.
     * @param vuelo Objeto vuelo a insertar
     * @return Vuelo insertado
     */
    public Vuelo insertVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    /**
     * Actualiza un vuelo existente en la base de datos.
     * @param vuelo Objeto vuelo con los datos actualizados
     * @return Vuelo actualizado
     */
    public Vuelo updateVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    /**
     * Elimina un vuelo de la base de datos por su ID.
     * @param id Identificador del vuelo a eliminar
     */
    public void deleteVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}

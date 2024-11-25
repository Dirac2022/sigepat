/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.model.*;
import com.dirac.sigepat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private MovilidadRepository movilidadRepository;

    /**
     * Guarda una reserva con un servicio asociado.
     *
     * @param reserva  La reserva a guardar.
     * @param servicio El servicio asociado (Alojamiento, Vuelo o Movilidad).
     */
    public void guardarReservaConServicio(Reserva reserva, Servicio servicio) {
        // Guardar la reserva
        //reservaRepository.save(reserva);
        reservaRepository.save(reserva);

        // Asociar el servicio con la reserva
        if (servicio instanceof Alojamiento) {
            Alojamiento alojamiento = (Alojamiento) servicio;
            alojamiento.setIdAlojamiento(reserva.getId_reserva()); // Asociar el ID de la reserva
            alojamientoRepository.save(alojamiento);
        } else if (servicio instanceof Vuelo) {
            Vuelo vuelo = (Vuelo) servicio;
            vuelo.setIdVuelo(reserva.getId_reserva()); // Asociar el ID de la reserva
            vueloRepository.save(vuelo);
        } else if (servicio instanceof Movilidad) {
            Movilidad movilidad = (Movilidad) servicio;
            movilidad.setId(reserva.getId_reserva()); // Asociar el ID de la reserva
            movilidadRepository.save(movilidad);
        }
    }

    /**
     * Recupera una reserva junto con el servicio asociado.
     *
     * @param idReserva El ID de la reserva a recuperar.
     * @return La reserva con el servicio asociado.
     */
    public Reserva recuperarReservaConServicio(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva).orElseThrow();

        // Recuperar el servicio asociado
        Servicio servicio = null;
        if (alojamientoRepository.existsById(idReserva)) {
            servicio = alojamientoRepository.findById(idReserva).orElse(null);
        } else if (vueloRepository.existsById(idReserva)) {
            servicio = vueloRepository.findById(idReserva).orElse(null);
        } else if (movilidadRepository.existsById(idReserva)) {
            servicio = movilidadRepository.findById(idReserva).orElse(null);
        }

        reserva.setServicio(servicio);
        return reserva;
    }
}

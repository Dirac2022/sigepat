/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.repository.ClienteRepository;
import com.dirac.sigepat.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mitch
 */

/**
 * Gestiona las llamadas al repositorio
 * @author mitch
 */


@Service
public class ClienteService {
    @Autowired // Se esta aplicando el patron inyección de dependencia
    ClienteRepository clienteRepository;
    
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
    
    /**
     * El tipo Optional es seguro 
     * @param id
     * @return 
     */
    
    // GET
    public Optional<Cliente> getCliente(Long id) {
        return clienteRepository.findById(id);
    }
    
    // POST
    public Cliente insertCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    // PUT
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    // DELETE
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

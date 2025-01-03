/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.service;

import com.dirac.sigepat.dto.ClienteRequest;
import com.dirac.sigepat.dto.ClienteResponse;
import com.dirac.sigepat.repository.ClienteRepository;
import com.dirac.sigepat.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
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
    public ClienteResponse insertCliente(ClienteRequest clienteRequest) {
        
        logger.info("insertCliente>");
        
        Cliente cliente = new Cliente (
                clienteRequest.getIdCliente(),
                clienteRequest.getNombres(),
                clienteRequest.getApePaterno(),
                clienteRequest.getApeMaterno(),
                clienteRequest.getFechaNac(),
                clienteRequest.getSexo(),
                clienteRequest.getEmail(),
                clienteRequest.getNumCelular()
        );
        
        cliente = clienteRepository.save(cliente);
        ClienteResponse clienteResponse = ClienteResponse.fromEntity(cliente);
        return clienteResponse;
    }
    
    // PUT
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    // DELETE
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    
    public Cliente registrarClienteSiNoExiste(String email, String nombres, String apePaterno, String apeMaterno) {
        logger.info("registrarClienteSiNoExiste> email: {}", email);
        
        return clienteRepository.findByEmail(email)
                .orElseGet(() -> {
                    Cliente newCliente = new Cliente();
                    newCliente.setEmail(email);
                    newCliente.setNombres(nombres);
                    newCliente.setApePaterno(apePaterno);
                    newCliente.setApeMaterno(apeMaterno);
                    newCliente.setFechaNac(null);
                    newCliente.setSexo(null);
                    newCliente.setNumCelular(null);
                    logger.info("Registrando nuevo cliente {}", newCliente);
                    return clienteRepository.save(newCliente);
                });
    }
    
}

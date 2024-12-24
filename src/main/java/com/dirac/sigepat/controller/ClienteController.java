/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.dto.ClienteRequest;
import com.dirac.sigepat.dto.ClienteResponse;
import com.dirac.sigepat.service.ClienteService;
import com.dirac.sigepat.model.Cliente;
import com.dirac.sigepat.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mitch
 */

/**
 * El controlador es el encargado de gestionar las peticiones del front
 * @author mitch
 */

@RestController
@RequestMapping(path="api/v1/cliente")
public class ClienteController {
    
    /**
     * Un registro que nos ayuda a tener la traza para visualizar todo el proceso
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired // Aplica inyeccion de dependencias
    private ClienteService clienteService;
    
    
    @RequestMapping(value="listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getClientes() {
        List<Cliente> listaClientes = null;
        try {
            listaClientes = clienteService.getClientes();
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }
    
        @PostMapping()
    public ResponseEntity<?> insertCliente(@RequestBody ClienteRequest clienteRequest) {
        logger.info(">insert " + clienteRequest.toString());
        ClienteResponse clienteResponse;
        try {
            clienteResponse = clienteService.insertCliente(clienteRequest);
        } catch (Exception e) {
            logger.error("Error inesperado ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (clienteResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente nor insert").build());
        }
        return ResponseEntity.ok(clienteResponse);
    }
    
}

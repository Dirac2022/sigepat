/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dirac.sigepat.controller;

import com.dirac.sigepat.model.Cliente;
import com.dirac.sigepat.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/success")
    public String loginSuccess(Authentication authentication) {
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String apePaterno = user.getAttribute("family_name");
        
        Cliente cliente = clienteService.registrarClienteSiNoExiste(email, name, apePaterno, null);
        
        return "Inicio de sesión exitoso: " + cliente.getNombres() + " (" + cliente.getEmail() + ")";
    }
    
    @GetMapping("/failure")
    public String loginFailure() {
        return "Inicio de sesión fallido.";
    }
}

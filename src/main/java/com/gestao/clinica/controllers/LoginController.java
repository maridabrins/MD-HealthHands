package com.gestao.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.clinica.dto.UsuarioEntradaDTO;
import com.gestao.clinica.services.LoginService;


@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	LoginService loginService; 
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public String login(@RequestBody UsuarioEntradaDTO dto) {
		System.err.println(dto.login() + dto.senha());
		return loginService.autenticar(dto.toUsuario(), 
									authenticationManager);  
		
	}

}

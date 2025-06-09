
package com.gestao.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.clinica.dto.UsuarioEntradaDTO;
import com.gestao.clinica.services.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	LoginService loginService; 
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	  @Operation(summary = "Logar um usuário e gerar token")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Sucesso ao logar"),
	        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
	    })
	@PostMapping("/login")
	public String login(@RequestBody UsuarioEntradaDTO dto) {
		System.err.println(dto.login() + dto.senha());
		return loginService.autenticar(dto.toUsuario(), 
									authenticationManager);  
		
	}

}

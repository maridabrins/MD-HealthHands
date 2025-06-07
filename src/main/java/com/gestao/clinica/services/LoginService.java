package com.gestao.clinica.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gestao.clinica.entities.Usuario;
import com.gestao.clinica.repositories.UsuarioRepository;


@Service
public class LoginService implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;
	
	private String chaveSecreta = "Xaxa@123";
	
	public String autenticar(Usuario usuario, 
			AuthenticationManager authenticationManager) {
		System.err.println("D");
		UsernamePasswordAuthenticationToken upat =
				new UsernamePasswordAuthenticationToken
					 (usuario.getLogin(), usuario.getSenha());
		
		Authentication usuarioLogado =
				authenticationManager.authenticate(upat);
		return gerarToken((Usuario) usuarioLogado.getPrincipal());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println("E");
		return repo.findByLogin(username)
				.orElseThrow(
						() -> new UsernameNotFoundException
						("Usuário não Encontrado!") );

	}

	private String gerarToken(Usuario usuario) {
		System.err.println("F");

		try {
			Algorithm algorithm = 
					Algorithm.HMAC256(chaveSecreta);
			
			String token = JWT.create()
					.withIssuer("API Colegio")
					.withSubject(usuario.getLogin())
					.withExpiresAt(Instant.now().plusSeconds(3600))
					.sign(algorithm);
			return token;			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token JWT" 
					+ e.getMessage());
		}
		

	}
	
	public String validarToken(String token) {
		System.err.println("G");
		try {
			Algorithm algorithm = 
					Algorithm.HMAC256(chaveSecreta);
			return JWT.require(algorithm)
					.withIssuer("API Colegio").build()
					.verify(token).getSubject();			
			
		} catch (JWTCreationException | 
				IllegalArgumentException | 
				JWTVerificationException e) {
			throw new RuntimeException("Token Inválido" 
				+ e.getMessage());
		}
		
	}
	
	
	
}

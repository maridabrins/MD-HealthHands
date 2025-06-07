package com.gestao.clinica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.clinica.entities.Usuario;

@Repository
public interface UsuarioRepository 
				extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByLogin(String login);
	
	boolean existsByLogin(String login);
	
	

}

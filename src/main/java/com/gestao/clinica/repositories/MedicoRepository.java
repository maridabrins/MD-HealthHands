package com.gestao.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.clinica.entities.Medico;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long> {
	
	

}

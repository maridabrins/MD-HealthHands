package com.gestao.clinica.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.clinica.entities.Consulta;
import com.gestao.clinica.entities.Especialidades;
import com.gestao.clinica.entities.Medico;
import com.gestao.clinica.entities.Paciente;

@Repository
public interface ConsultaRepository extends JpaRepository <Consulta, Long>{
	
	
	 List<Consulta> findByMedicoAndData(Medico medico, LocalDate data);
	 List<Consulta> findByPacienteAndData(Paciente paciente, LocalDate data);
	    
	    
	    List<Consulta> findByMedico_NomeContainingIgnoreCase(String nome); //buscar medico pelo nome sem diferenciae letras maiusc e min
	    // Buscar por data exata
	    List<Consulta> findByData(LocalDate data);
	    // Buscar por especialidade
	    List<Consulta> findByEspecialidade(Especialidades especialidade);
	    
	    boolean existsByMedicoAndData(Medico medico, LocalDate data);
	    boolean existsByPacienteAndData(Paciente paciente, LocalDate data);
	    
	    
}

package com.gestao.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.PacienteDTO;
import com.gestao.clinica.entities.Paciente;
import com.gestao.clinica.entities.Roles;
import com.gestao.clinica.repositories.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	//exibindo lista de 
	@Transactional
	public List<PacienteDTO> findAll(){
		List<Paciente> lista = pacienteRepository.findAll();
		return lista.stream().map(x-> new PacienteDTO(x)).toList();
	}
	@Transactional
	public PacienteDTO findById(Long id){
		Paciente entity = pacienteRepository.findById(id).get();
		return new PacienteDTO(entity);
	}
	
	@Transactional
	public PacienteDTO create (PacienteDTO dto) {
		Paciente entity = new Paciente();
		
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setDataNasc(dto.getDataNasc());
		entity.setTelefone(dto.getTelefone());
		
		entity.setRole(Roles.PACIENTE);
		
		entity = pacienteRepository.save(entity);
		return new PacienteDTO(entity);
	}
	
	@Transactional
	public PacienteDTO update (PacienteDTO dto, Long id) {
		Paciente entity = pacienteRepository.getReferenceById(id);
		
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setDataNasc(dto.getDataNasc());
		entity.setTelefone(dto.getTelefone());
		
		entity = pacienteRepository.save(entity);
		return new PacienteDTO(entity);
	}
	
	@Transactional
	public void delete (Long id) {
		pacienteRepository.deleteById(id);
	}
	
	

}

package com.gestao.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.MedicoDTO;
import com.gestao.clinica.entities.Medico;
import com.gestao.clinica.repositories.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Transactional
	public List<MedicoDTO> findAll(){
		List<Medico> lista = medicoRepository.findAll();
		return lista.stream().map(x-> new MedicoDTO(x)).toList();
	}
	
	@Transactional
	public MedicoDTO findById (Long id) {
		Medico entity = medicoRepository.findById(id).get();
		return new MedicoDTO(entity);
	}
	
	@Transactional
	public MedicoDTO create (MedicoDTO dto) {
		Medico entity = new Medico();
		
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCrm(dto.getCrm());
		entity.setEspecialidade(dto.getEspecialidade());
		entity.setTelefone(dto.getTelefone());
		
		entity = medicoRepository.save(entity);
		return new MedicoDTO(entity);
	}
	
	@Transactional
	public MedicoDTO update (MedicoDTO dto, Long id) {
		Medico entity = medicoRepository.getReferenceById(id);
		
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCrm(dto.getCrm());
		entity.setEspecialidade(dto.getEspecialidade());
		entity.setTelefone(dto.getTelefone());
		
		entity = medicoRepository.save(entity);
		return new MedicoDTO(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		medicoRepository.deleteById(id);
	}


}

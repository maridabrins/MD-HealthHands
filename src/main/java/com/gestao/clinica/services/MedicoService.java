package com.gestao.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.MedicoDTO;
import com.gestao.clinica.entities.Medico;
import com.gestao.clinica.entities.Role;
import com.gestao.clinica.entities.Usuario;
import com.gestao.clinica.repositories.MedicoRepository;
import com.gestao.clinica.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
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
	
	public MedicoDTO create(MedicoDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha()); 
        usuario.setPerfil(Role.MEDICO);
        
       Usuario usuarioSalvo = usuarioService.gravar(usuario);
     

        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEmail(dto.getEmail());
        medico.setTelefone(dto.getTelefone());
        medico.setEspecialidade(dto.getEspecialidade());
        medico.setUsuario(usuarioSalvo);
        medicoRepository.save(medico);
        return MedicoDTO(medico); 
    }
	
	 private MedicoDTO MedicoDTO(Medico medico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	    public Medico save(Medico medico) {
	        return medicoRepository.save(medico);
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

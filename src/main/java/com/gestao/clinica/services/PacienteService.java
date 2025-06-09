
package com.gestao.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.PacienteDTO;
import com.gestao.clinica.entities.Paciente;
import com.gestao.clinica.entities.Role;
import com.gestao.clinica.entities.Usuario;
import com.gestao.clinica.repositories.PacienteRepository;
import com.gestao.clinica.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {
	
	
	@Autowired
	PacienteRepository pacienteRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	
	//exibindo lista de pacientes
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
	
	public PacienteDTO create(PacienteDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha()); 
        usuario.setPerfil(Role.PACIENTE);
        
       Usuario usuarioSalvo = usuarioService.gravar(usuario);
     

        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setDataNasc(dto.getDataNasc());
        paciente.setUsuario(usuarioSalvo);
        pacienteRepository.save(paciente);
        return PacienteDTO(paciente); 
    }


	
	private PacienteDTO PacienteDTO(Paciente paciente) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
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

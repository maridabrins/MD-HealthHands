package com.gestao.clinica.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.ConsultaDTO;
import com.gestao.clinica.dto.ConsultaResponseDTO;
import com.gestao.clinica.entities.Consulta;
import com.gestao.clinica.entities.Especialidades;
import com.gestao.clinica.entities.Medico;
import com.gestao.clinica.entities.Paciente;
import com.gestao.clinica.repositories.ConsultaRepository;
import com.gestao.clinica.repositories.MedicoRepository;
import com.gestao.clinica.repositories.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	ConsultaRepository consultaRepository;
	@Autowired
	PacienteRepository pacienteRepository;
	@Autowired
	MedicoRepository medicoRepository;
	
	public Consulta agendarConsulta (ConsultaDTO dto) {
		
		// Verificar se o usuário autenticado é um médico
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream()
		        .anyMatch(a -> a.getAuthority().equals("ROLE_MEDICO"))) {
		    throw new RuntimeException("Médicos não podem agendar consultas.");
		}
		
		 // 1. Buscar paciente pelo ID ou nome (conforme vem no DTO)
	    Optional<Paciente> pacienteOpt = pacienteRepository.findById(dto.getPacienteId());
	    if (pacienteOpt.isEmpty()) {
	        throw new RuntimeException("Paciente não encontrado.");
	    }
	    Paciente paciente = pacienteOpt.get();

	    // 2. Buscar médico
	    Optional<Medico> medicoOpt = medicoRepository.findById(dto.getMedicoId());
	    if (medicoOpt.isEmpty()) {
	        throw new RuntimeException("Médico não encontrado.");
	    }
	    Medico medico = medicoOpt.get();
	    if (!medico.getEspecialidade().equals(dto.getEspecialidade())) {
	        throw new RuntimeException("O médico selecionado não possui a especialidade informada.");
	    }
	    

	 // Verificar se o paciente é menor de idade
	 LocalDate hoje = LocalDate.now();
	 LocalDate dataNascimento = paciente.getDataNasc();

	 int idade = Period.between(dataNascimento, hoje).getYears();
	 if (idade < 18) {
	     throw new RuntimeException("Pacientes menores de idade não podem agendar consultas.");
	 }
	 
	// Verificar se o médico já tem consulta na data
	 if (consultaRepository.existsByMedicoAndData(medico, dto.getData())) {
	     throw new RuntimeException("O médico já possui uma consulta agendada nessa data.");
	 }

	 // Verificar se o paciente já tem consulta na data
	 if (consultaRepository.existsByPacienteAndData(paciente, dto.getData())) {
	     throw new RuntimeException("O paciente já possui uma consulta agendada nessa data.");
	 }
	    
	    Consulta consulta = new Consulta();
	    consulta.setPaciente(paciente);
	    consulta.setMedico(medico);
	    consulta.setData(dto.getData());
	    consulta.setEspecialidade(medico.getEspecialidade());

	    return consultaRepository.save(consulta);
		
	}
	
	public List<ConsultaResponseDTO> listarConsultas() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isMedico = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_MEDICO"));
	    boolean isPaciente = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_PACIENTE"));

	    List<Consulta> consultas = consultaRepository.findAll();

	    return consultas.stream().map(consulta -> {
	        ConsultaResponseDTO dto = new ConsultaResponseDTO();
	        dto.setId(consulta.getId());
	        dto.setData(consulta.getData());
	        dto.setEspecialidade(consulta.getEspecialidade());

	        if (isMedico) {
	            dto.setNomePaciente(consulta.getPaciente().getNome());
	            dto.setNomeMedico(null); // Oculta o nome do médico para médico
	        } else if (isPaciente) {
	            dto.setNomeMedico(consulta.getMedico().getNome());
	            dto.setNomePaciente(null); // Oculta o nome do paciente para paciente
	        } else {
	            // Se for admin ou outro papel, mostra tudo
	            dto.setNomeMedico(consulta.getMedico().getNome());
	            dto.setNomePaciente(consulta.getPaciente().getNome());
	        }

	        return dto;
	    }).collect(Collectors.toList());
	}
	
	public Consulta editarConsulta(Long consultaId, LocalDate data) {
	    // 1. Buscar a consulta
	    Optional<Consulta> consultaOpt = consultaRepository.findById(consultaId);
	    if (consultaOpt.isEmpty()) {
	        throw new RuntimeException("Consulta não encontrada.");
	    }

	    Consulta consulta = consultaOpt.get();

	    // 2. Validar se o novo horário está disponível para o médico
	    if (consultaRepository.existsByMedicoAndData(consulta.getMedico(), data)) {
	        throw new RuntimeException("O médico já possui uma consulta nessa data.");
	    }

	    // 3. Validar se o novo horário está disponível para o paciente
	    if (consultaRepository.existsByPacienteAndData(consulta.getPaciente(), data)) {
	        throw new RuntimeException("O paciente já possui uma consulta nessa data.");
	    }

	    // 4. Atualizar a data
	    consulta.setData(data);
	    return consultaRepository.save(consulta);
	}
	
	public void cancelarConsulta(Long consultaId) {
	    // 1. Buscar a consulta
	    Optional<Consulta> consultaOpt = consultaRepository.findById(consultaId);
	    if (consultaOpt.isEmpty()) {
	        throw new RuntimeException("Consulta não encontrada.");
	    }

	    Consulta consulta = consultaOpt.get();

	    // 2. Verificar quem está autenticado
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String usuarioLogado = auth.getName(); // pode ser o e-mail ou login

	    boolean isPaciente = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_PACIENTE"));
	    boolean isMedico = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_MEDICO"));

	   

	    // 4. Cancelar a consulta 
	    consultaRepository.deleteById(consultaId);
	}
	
	private List<ConsultaResponseDTO> mapearParaResponse(List<Consulta> consultas) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isMedico = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_MEDICO"));
	    boolean isPaciente = auth.getAuthorities().stream()
	        .anyMatch(role -> role.getAuthority().equals("ROLE_PACIENTE"));

	    return consultas.stream().map(consulta -> {
	        ConsultaResponseDTO dto = new ConsultaResponseDTO();
	        dto.setId(consulta.getId());
	        dto.setData(consulta.getData());
	        dto.setEspecialidade(consulta.getEspecialidade());

	        if (isMedico) {
	            dto.setNomePaciente(consulta.getPaciente().getNome());
	        } else if (isPaciente) {
	            dto.setNomeMedico(consulta.getMedico().getNome());
	        } else {
	            dto.setNomeMedico(consulta.getMedico().getNome());
	            dto.setNomePaciente(consulta.getPaciente().getNome());
	        }

	        return dto;
	    }).collect(Collectors.toList());
	}

	
	public List<ConsultaResponseDTO> buscarPorData(LocalDate data) {
	    List<Consulta> consultas = consultaRepository.findByData(data);
	    return mapearParaResponse(consultas);
	}
	public List<ConsultaResponseDTO> buscarPorEspecialidade(Especialidades especialidade) {
	    List<Consulta> consultas = consultaRepository.findByEspecialidade(especialidade);
	    return mapearParaResponse(consultas);
	}
	public List<ConsultaResponseDTO> buscarPorNomeMedico(String nomeMedico) {
	    List<Consulta> consultas = consultaRepository.findByMedico_NomeContainingIgnoreCase(nomeMedico);
	    return mapearParaResponse(consultas);
	}
	
	public List<ConsultaResponseDTO> buscarPorTermo(String termo) {
	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate data = LocalDate.parse(termo, formatter);
	        return buscarPorData(data);
	    } catch (Exception e1) {
	        try {
	            LocalDate data = LocalDate.parse(termo); // formato ISO: yyyy-MM-dd
	            return buscarPorData(data);
	        } catch (Exception e2) {
	            // Não é data
	        }
	    }

	    // Tentar converter para especialidade usando for
	    for (Especialidades esp : Especialidades.values()) {
	        if (esp.name().equalsIgnoreCase(termo)) {
	            return buscarPorEspecialidade(esp);
	        }
	    }

	    // Caso contrário, buscar por nome do médico
	    return buscarPorNomeMedico(termo);
	}



}

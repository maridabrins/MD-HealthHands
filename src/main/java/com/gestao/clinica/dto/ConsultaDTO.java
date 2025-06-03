package com.gestao.clinica.dto;

import java.time.LocalDate;

import com.gestao.clinica.entities.Consulta;
import com.gestao.clinica.entities.Especialidades;

public class ConsultaDTO {
	
		private Long medicoId;
	    private Long pacienteId;
	    private LocalDate data;
	    private Especialidades especialidade;
	    
	    
		public ConsultaDTO() {
		}


		public ConsultaDTO(Long medicoId, Long pacienteId, LocalDate data, Especialidades especialidade) {
			this.medicoId = medicoId;
			this.pacienteId = pacienteId;
			this.data = data;
			this.especialidade = especialidade;
		}
		
		public ConsultaDTO (Consulta entity) {
			medicoId = entity.getMedico().getId();
			pacienteId = entity.getPaciente().getId();
			data = entity.getData();
			especialidade = entity.getEspecialidade();
		}


		public Long getMedicoId() {
			return medicoId;
		}


		public void setMedicoId(Long medicoId) {
			this.medicoId = medicoId;
		}


		public Long getPacienteId() {
			return pacienteId;
		}


		public void setPacienteId(Long pacienteId) {
			this.pacienteId = pacienteId;
		}


		public LocalDate getData() {
			return data;
		}


		public void setData(LocalDate data) {
			this.data = data;
		}


		public Especialidades getEspecialidade() {
			return especialidade;
		}


		public void setEspecialidade(Especialidades especialidade) {
			this.especialidade = especialidade;
		}
		
		
	    
	    

}

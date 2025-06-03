package com.gestao.clinica.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_consulta")
public class Consulta {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private LocalDate data;

	    @ManyToOne
	    @JoinColumn(name = "medico_id", nullable = false)
	    private Medico medico;

	    @ManyToOne
	    @JoinColumn(name = "paciente_id", nullable = false)
	    private Paciente paciente;

	    @Enumerated(EnumType.STRING)
	    private Especialidades especialidade;

		public Consulta() {
		}

		public Consulta(Long id, LocalDate data, Medico medico, Paciente paciente, Especialidades especialidade) {
			this.id = id;
			this.data = data;
			this.medico = medico;
			this.paciente = paciente;
			this.especialidade = especialidade;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getData() {
			return data;
		}

		public void setData(LocalDate data) {
			this.data = data;
		}

		public Medico getMedico() {
			return medico;
		}

		public void setMedico(Medico medico) {
			this.medico = medico;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Especialidades getEspecialidade() {
			return especialidade;
		}

		public void setEspecialidade(Especialidades especialidade) {
			this.especialidade = especialidade;
		}
	    
	    

}



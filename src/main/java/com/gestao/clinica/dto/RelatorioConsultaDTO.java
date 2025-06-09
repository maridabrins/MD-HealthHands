package com.gestao.clinica.dto;

import java.time.format.DateTimeFormatter;

import com.gestao.clinica.entities.Consulta;


public class RelatorioConsultaDTO {
	
	private Long consulta;
	private String paciente;
	private String medico;
	private String especialidade;
	private String data;

	

	public RelatorioConsultaDTO(Consulta entity) {
		this.consulta = entity.getId();
		this.paciente = entity.getPaciente().getNome();
		this.medico = entity.getMedico().getNome();
		this.especialidade = entity.getEspecialidade().toString();
		this.data = entity.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}



	public Long getConsulta() {
		return consulta;
	}



	public void setConsulta(Long consulta) {
		this.consulta = consulta;
	}



	public String getPaciente() {
		return paciente;
	}



	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}



	public String getMedico() {
		return medico;
	}



	public void setMedico(String medico) {
		this.medico = medico;
	}



	public String getEspecialidade() {
		return especialidade;
	}



	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}

	

}

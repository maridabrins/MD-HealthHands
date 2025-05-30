package com.gestao.clinica.dto;

import java.time.LocalDate;

import com.gestao.clinica.entities.Paciente;

public class PacienteDTO {
	
	private Long id;
	private String nome;
	private LocalDate dataNasc;
	private String email;
	private String telefone;
	
	public PacienteDTO() {
	}

	public PacienteDTO(Long id, String nome, LocalDate dataNasc, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.email = email;
		this.telefone = telefone;
	}
	
	public PacienteDTO(Paciente entity) {
		id = entity.getId();
		nome = entity.getNome();
		dataNasc = entity.getDataNasc();
		email = entity.getEmail();
		telefone = entity.getTelefone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

}

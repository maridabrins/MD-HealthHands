package com.gestao.clinica.dto;

import com.gestao.clinica.entities.Especialidades;
import com.gestao.clinica.entities.Medico;

public class MedicoDTO {
	
	private Long id;
	private int crm;
	private String nome;
	private String telefone;
	private String email;
	private Especialidades especialidade;
	
	public MedicoDTO() {
	}

	public MedicoDTO(Long id, int crm, String nome, String telefone, String email, Especialidades especialidade) {
		this.id = id;
		this.crm = crm;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.especialidade = especialidade;
	}
	
	public MedicoDTO(Medico entity) {
		id = entity.getId();
		crm = entity.getCrm();
		nome = entity.getNome();
		telefone = entity.getTelefone();
		email = entity.getEmail();
		especialidade = entity.getEspecialidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Especialidades getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidades especialidade) {
		this.especialidade = especialidade;
	}
	
	
	
	
}

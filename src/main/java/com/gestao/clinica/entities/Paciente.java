package com.gestao.clinica.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_paciente")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate dataNasc;
	private String email;
	private String telefone;
	
	
	public Paciente() {
	}


	public Paciente(Long id, String nome, LocalDate dataNasc, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.email = email;
		this.telefone = telefone;
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

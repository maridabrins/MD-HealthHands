package com.gestao.clinica.dto;

import java.time.LocalDate;

import com.gestao.clinica.entities.Paciente;
import com.gestao.clinica.entities.Role;

public class PacienteDTO {
	
	    private String login;
	    private String senha;
	    
	    private String nome;
	    private LocalDate dataNasc;
	    private String email;
	    private String telefone;
	    
	    

		public PacienteDTO() {
		}

		public PacienteDTO(String login, String senha, String nome, LocalDate dataNasc, String email, String telefone) {
			this.login = login;
			this.senha = senha;
			this.nome = nome;
			this.dataNasc = dataNasc;
			this.email = email;
			this.telefone = telefone;
		}
		
		
		public PacienteDTO(Paciente entity) {
			
			nome = entity.getNome();
			dataNasc = entity.getDataNasc();
			email = entity.getEmail();
			telefone = entity.getTelefone();
			
		}

	    public String getLogin() {
	        return login;
	    }

	    public void setLogin(String login) {
	        this.login = login;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
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



	package com.gestao.clinica.entities;
	
	import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
	
	@Entity
	@Table(name = "tb_paciente")
	@Inheritance(strategy = InheritanceType.JOINED)
	public class Paciente  {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private LocalDate dataNasc;
		private String email;
		private String telefone;
		
		@JsonIgnore
		@OneToMany(mappedBy = "paciente")
		private List<Consulta> consultas;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "usuario_id", referencedColumnName = "id")
		private Usuario usuario;
		 

		public Paciente() {
		}
	
		public Paciente(Long id, String nome, LocalDate dataNasc, String email, String telefone, List<Consulta> consultas) {
			this.id = id;
			this.nome = nome;
			this.dataNasc = dataNasc;
			this.email = email;
			this.telefone = telefone;
			this.consultas = consultas;
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
	
	
	
	
		public List<Consulta> getConsultas() {
			return consultas;
		}
	
	
	
	
		public void setConsultas(List<Consulta> consultas) {
			this.consultas = consultas;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
	
		
		
		
		
		
			
		
		
		
	
	}

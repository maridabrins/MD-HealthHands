	package com.gestao.clinica.entities;
	
	import java.time.LocalDate;
	import java.util.List;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
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
		
		@OneToMany(mappedBy = "paciente")
		private List<Consulta> consultas;
		 
		private Roles role;
		
		
		public Paciente() {
		}
	
		public Paciente(Long id, String nome, LocalDate dataNasc, String email, String telefone, List<Consulta> consultas,
				Roles role) {
			this.id = id;
			this.nome = nome;
			this.dataNasc = dataNasc;
			this.email = email;
			this.telefone = telefone;
			this.consultas = consultas;
			this.role = role;
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
	
		public Roles getRole() {
			return role;
		}
	
		public void setRole(Roles role) {
			this.role = role;
		}
		
		
		
			
		
		
		
	
	}

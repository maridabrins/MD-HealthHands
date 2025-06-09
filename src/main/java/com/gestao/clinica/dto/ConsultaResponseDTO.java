package com.gestao.clinica.dto;

import java.time.LocalDate;

import com.gestao.clinica.entities.Especialidades;

public class ConsultaResponseDTO {
    private Long id;
    private String nomePaciente;
    private String nomeMedico;
    private Especialidades especialidade;
    private LocalDate data;
    
    public ConsultaResponseDTO() {
    	
    }

    public ConsultaResponseDTO(Long id, String nomePaciente, String nomeMedico, Especialidades especialidade, LocalDate data) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.data = data;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public Especialidades  getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidades  especialidade) {
		this.especialidade = especialidade;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

   
}

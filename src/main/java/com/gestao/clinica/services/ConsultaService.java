package com.gestao.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.repositories.ConsultaRepository;
import com.gestao.clinica.repositories.MedicoRepository;
import com.gestao.clinica.repositories.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	PacienteRepository pacienteRepository;
	MedicoRepository medicoRepository;
	ConsultaRepository consultaRepository;

}

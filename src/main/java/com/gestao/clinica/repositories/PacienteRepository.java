package com.gestao.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.clinica.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente, Long>{

}

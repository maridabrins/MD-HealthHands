package com.gestao.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.clinica.dto.PacienteDTO;
import com.gestao.clinica.services.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteController {
	
	@Autowired
	PacienteService pacienteService;
	
	@GetMapping("/all")
	public List<PacienteDTO> findAll (){
		return pacienteService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Long id){
		PacienteDTO dto = pacienteService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/create")
	public ResponseEntity<PacienteDTO> create (@RequestBody PacienteDTO dto){
		 dto = pacienteService.create(dto);
		 return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PacienteDTO> update(@RequestBody PacienteDTO dto, @PathVariable Long id){
		dto = pacienteService.update(dto, id);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    pacienteService.delete(id);
	    return ResponseEntity.ok("Usuário excluído com sucesso!");
	}
}

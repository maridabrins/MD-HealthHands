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

import com.gestao.clinica.dto.MedicoDTO;
import com.gestao.clinica.services.MedicoService;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController {
	
	@Autowired
	MedicoService medicoService;
	
	@GetMapping("/all")
	public List<MedicoDTO> findAll (){
		return medicoService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoDTO> findById(@PathVariable Long id){
		MedicoDTO dto = medicoService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/create")
	public ResponseEntity<MedicoDTO> create (@RequestBody MedicoDTO dto){
		 dto = medicoService.create(dto);
		 return ResponseEntity.ok(dto);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<MedicoDTO> update(@RequestBody MedicoDTO dto, @PathVariable Long id){
		dto = medicoService.update(dto, id);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    medicoService.delete(id);
	    return ResponseEntity.ok("Usuário excluído com sucesso!");
	}


}

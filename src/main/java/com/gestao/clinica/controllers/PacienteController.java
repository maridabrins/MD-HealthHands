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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteController {
	
	@Autowired
	PacienteService pacienteService;
	
	
	  @Operation(summary = "Listar todos os pacientes")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Pacientes listados com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado")
	    })
	@GetMapping("/all")
	public List<PacienteDTO> findAll (){
		return pacienteService.findAll();
	}
	  @Operation(summary = "Buscar paciente por ID")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Paciente não encontrado para o ID fornecido")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Long id){
		PacienteDTO dto = pacienteService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	   @Operation(summary = "Cadastrar novo Paciente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso"),
	        @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o paciente")
	    })
	@PostMapping("/create")
	public ResponseEntity<PacienteDTO> create (@RequestBody PacienteDTO dto){
		 dto = pacienteService.create(dto);
		 return ResponseEntity.ok(dto);
	}


	    @Operation(summary = "Atualizar dados de um paciente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Paciente não encontrado para atualização")
	    })
	@PutMapping("/update/{id}")
	public ResponseEntity<PacienteDTO> update(@RequestBody PacienteDTO dto, @PathVariable Long id){
		dto = pacienteService.update(dto, id);
		return ResponseEntity.ok(dto);
	}
	
	    @Operation(summary = "Excluir um paciente")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Paciente excluído com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Paciente não encontrado para exclusão")
	    })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    pacienteService.delete(id);
	    return ResponseEntity.ok("Usuário excluído com sucesso!");
	}
}

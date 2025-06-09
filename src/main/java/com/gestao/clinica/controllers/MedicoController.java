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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController {
	
	@Autowired
	MedicoService medicoService;
	

    @Operation(summary = "Listar todos os médicos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médicos listados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum médico encontrado")
    })
    @GetMapping("/all")
    public List<MedicoDTO> findAll() {
        return medicoService.findAll();
    }

    @Operation(summary = "Buscar médico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado para o ID fornecido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        MedicoDTO dto = medicoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Cadastrar novo médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o médico")
    })
    @PostMapping("/create")
    public ResponseEntity<MedicoDTO> create(@RequestBody MedicoDTO dto) {
        dto = medicoService.create(dto);
        return ResponseEntity.status(201).body(dto);
    }

    @Operation(summary = "Atualizar dados de um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado para atualização")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicoDTO> update(@RequestBody MedicoDTO dto, @PathVariable Long id) {
        dto = medicoService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Excluir um médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado para exclusão")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.ok("Médico excluído com sucesso!");
    }


}

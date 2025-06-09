package com.gestao.clinica.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.clinica.dto.ConsultaDTO;
import com.gestao.clinica.dto.ConsultaResponseDTO;
import com.gestao.clinica.services.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Operation(summary = "Agendar uma consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao agendar consulta")
    })
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarConsulta(@RequestBody ConsultaDTO dto) {
        try {
            var consulta = consultaService.agendarConsulta(dto);
            return ResponseEntity.status(201).body(consulta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todas as consultas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consultas listadas com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhuma consulta encontrada")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO') or hasRole('PACIENTE')")
    @GetMapping("/all")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    // DTO para edição da data da consulta
    public static class AtualizarDataDTO {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate data;

        public LocalDate getData() {
            return data;
        }
        public void setData(LocalDate data) {
            this.data = data;
        }
    }

    @Operation(summary = "Remarcar data da consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao atualizar"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada para atualização")
    })
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> editarConsulta(
            @PathVariable Long id,
            @RequestBody AtualizarDataDTO dto) {
        try {
            var consultaAtualizada = consultaService.editarConsulta(id, dto.getData());
            return ResponseEntity.ok(consultaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Cancelar uma consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao cancelar a consulta"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada para exclusão")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO') or hasRole('PACIENTE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id) {
        try {
            consultaService.cancelarConsulta(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar por Termo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso ao buscar"),
        @ApiResponse(responseCode = "400", description = "Erro ao buscar")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO') or hasRole('PACIENTE')")
    @GetMapping("/buscar")
    public ResponseEntity<List<ConsultaResponseDTO>> buscarPorTermo(@RequestParam String termo) {
        return ResponseEntity.ok(consultaService.buscarPorTermo(termo));
    }
}


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

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

   
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarConsulta(@RequestBody ConsultaDTO dto) {
        try {
            return ResponseEntity.ok(consultaService.agendarConsulta(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO') or hasRole('PACIENTE')")
    @GetMapping("/all")
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    
    @PutMapping("update/{id}")
    public ResponseEntity<?> editarConsulta(
            @PathVariable Long id,
            @RequestBody LocalDate data) {
        try {
            return ResponseEntity.ok(consultaService.editarConsulta(id, data));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id) {
        try {
            consultaService.cancelarConsulta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /consultas/buscar?termo=... → Buscar por termo (data, especialidade ou nome do médico)
    @GetMapping("/buscar")
    public ResponseEntity<List<ConsultaResponseDTO>> buscarPorTermo(@RequestParam String termo) {
        return ResponseEntity.ok(consultaService.buscarPorTermo(termo));
    }
}

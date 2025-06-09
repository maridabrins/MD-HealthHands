package com.gestao.clinica.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    
    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> editarConsulta(
            @PathVariable Long id,
            @RequestParam("novaData") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate novaData) {
        try {
            return ResponseEntity.ok(consultaService.editarConsulta(id, novaData));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @DeleteMapping("/{id}")
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

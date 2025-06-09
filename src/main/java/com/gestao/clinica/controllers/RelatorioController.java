package com.gestao.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.clinica.services.RelatorioService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(value ="relatorio")
public class RelatorioController {

	@Autowired
    RelatorioService relatorio;

    @GetMapping
    public ResponseEntity<String> gerarRelatorio(@RequestParam String caminho) throws JRException {
        relatorio.gerarRelatorioPDF(caminho);
        return ResponseEntity.ok("Relatorio gerado com sucesso " + caminho);
    }


}

package com.gestao.clinica.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.clinica.dto.RelatorioConsultaDTO;
import com.gestao.clinica.entities.Consulta;
import com.gestao.clinica.repositories.ConsultaRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {


	@Autowired
    ConsultaRepository consultaRepository;

    public void gerarRelatorioPDF(String caminho) throws JRException {
        List<Consulta> consultas = consultaRepository.findAll();

        List<RelatorioConsultaDTO> dados = consultas.stream()
                .map( RelatorioConsultaDTO::new)
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatorio de Consultas");

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorios/relatorio_consultas.jrxml")
        );

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);

    }
}

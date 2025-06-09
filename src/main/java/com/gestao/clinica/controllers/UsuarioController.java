package com.gestao.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestao.clinica.dto.UsuarioSaidaDTO;
import com.gestao.clinica.entities.Usuario;
import com.gestao.clinica.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuários com paginação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioSaidaDTO>> buscarTodos(
            @RequestParam int pag,
            @RequestParam int qtd) {
        return ResponseEntity.ok(usuarioService.buscarTodos(PageRequest.of(pag, qtd)));
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID fornecido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o usuário")
    })
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.gravar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @Operation(summary = "Atualizar dados de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualização")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.alterar(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @Operation(summary = "Excluir um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado para exclusão")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
}

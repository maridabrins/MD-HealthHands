package com.gestao.clinica.dto;

import com.gestao.clinica.entities.Usuario;

public record UsuarioEntradaDTO(String login,String senha) {
	
	public Usuario toUsuario() {
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		return u;
	}
}
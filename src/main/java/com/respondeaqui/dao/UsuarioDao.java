package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Usuario;

public interface UsuarioDao {
	
	Usuario findByMatricula(String matricula);
	
	List<Usuario> ranking(); 
	
	void cadastrarUsuario(Usuario usuario);
	
	int editarPerfil(Usuario usuario);
	
	int redefinirSenha(String senha, String matricula);
}

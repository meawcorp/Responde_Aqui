package com.respondeaqui.servico;

import org.springframework.stereotype.Service;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UsuarioServico {
	
	@Autowired
	UsuarioDao usuarioDao;

	public boolean usuarioExiste(String matricula) {
		
		Usuario usuario = usuarioDao.findByMatricula(matricula);
		
		if (usuario != null) {
			return true;
		}
			return false;
	}
	
	public Usuario getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String matricula = authentication.getName();
		Usuario usuario = usuarioDao.findByMatricula(matricula);
		return usuario;
	}
	
	public String getMatricula() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String matricula = authentication.getName();
		return matricula;
	}
}

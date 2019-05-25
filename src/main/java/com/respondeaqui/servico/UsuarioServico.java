package com.respondeaqui.servico;

import org.springframework.stereotype.Service;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;

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
}

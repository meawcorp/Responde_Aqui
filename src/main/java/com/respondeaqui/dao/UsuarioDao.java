package com.respondeaqui.dao;

import com.respondeaqui.modelo.Usuario;

public interface UsuarioDao {
	
	Usuario findByMatricula(int matricula);

}
package com.respondeaqui.dao;

import com.respondeaqui.modelo.Formulario;

public interface FormularioDao {
	
	Formulario findById(int id_usuario);
	
	void criarFormulario(Formulario formulario);
}

package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Formulario;

public interface FormularioDao {
	
	Formulario findById(int id);
	
	List<Formulario> findByUserId(String matricula);
	
	void criarFormulario(Formulario formulario);
	
	int removerFormulario(int id);
	
	int editarFormulario(Formulario formulario);
}

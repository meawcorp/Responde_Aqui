package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Formulario;

public interface FormularioDao {
	
	List<Formulario> findByMatricula(String matricula);
	
	void criarFormulario(Formulario formulario);
	
	int removerFormulario(int id);
}

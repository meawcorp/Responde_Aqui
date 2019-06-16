package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Formulario;
import com.respondeaqui.modelo.Usuario;

public interface FormularioDao {
	
	Formulario findById(int id);
	
	List<Formulario> findByUserId(String matricula);

	List<Formulario> findByUser(Usuario usuario);
	
	void criarFormulario(Formulario formulario);
	
	int removerFormulario(int id);
	
	int editarFormulario(Formulario formulario);
}

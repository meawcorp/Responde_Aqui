package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Formulario;
import com.respondeaqui.modelo.Usuario;

public interface FormularioDao {
	
	Formulario findById(int id);
	
	int atualizarNumRespostas(int id);
	
	int atualizarPontos(String matricula);
	
	void confirmarRespostaForm(int id, String matricula);
	
	List<Formulario> findByUserId(String matricula);

	List<Formulario> findByUser(Usuario usuario);
	
	List<Formulario> findByMatricula(String matricula);
	
	void criarFormulario(Formulario formulario);
	
	int removerFormulario(int id);
	
	int editarFormulario(Formulario formulario);
}

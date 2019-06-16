package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.respondeaqui.modelo.Formulario;
import com.respondeaqui.modelo.Usuario;


public class FormularioRowMapper implements RowMapper<Formulario> {

	@Override
	public Formulario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Usuario usuario =  new Usuario();
		
		usuario.setMatricula(rs.getString("matricula"));
		usuario.setNome(rs.getString("nome"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setDt_nascimento(rs.getDate("dt_nascimento"));
		usuario.setTurno(rs.getString("turno").charAt(0));
		usuario.setSexo(rs.getString("sexo").charAt(0));
		usuario.setPontos(rs.getInt("pontos"));
		usuario.setFoto(rs.getInt("foto"));
		usuario.setId_cidade(rs.getInt("id_cidade"));
		usuario.setId_campus(rs.getInt("id_campus"));
		usuario.setId_curso(rs.getInt("id_curso"));
		
		Formulario formulario =  new Formulario();
		
		formulario.setId(rs.getInt("id"));
		formulario.setLink(rs.getString("link"));
		formulario.setTitulo(rs.getString("titulo"));
		formulario.setDescricao(rs.getString("descricao"));
		formulario.setDt_fechamento(rs.getDate("dt_fechamento"));
		formulario.setDt_criacao(rs.getDate("dt_criacao"));
		formulario.setN_respostas(rs.getInt("n_respostas"));
		formulario.setTurno(rs.getString("turno").charAt(0));
		formulario.setSexo(rs.getString("sexo").charAt(0));
		formulario.setId_usuario(rs.getString("id_usuario"));
		formulario.setId_cidade(rs.getInt("id_cidade"));
		formulario.setId_campus(rs.getInt("id_campus"));
		formulario.setId_curso(rs.getInt("id_curso"));
		formulario.setUsuario(usuario);
		
		return formulario;
	}

}
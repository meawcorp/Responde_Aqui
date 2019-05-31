package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.respondeaqui.modelo.Formulario;


public class FormularioRowMapper implements RowMapper<Formulario> {

	@Override
	public Formulario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
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
		
		return formulario;
	}

}
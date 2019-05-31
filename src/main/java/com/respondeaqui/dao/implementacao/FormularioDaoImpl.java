package com.respondeaqui.dao.implementacao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.FormularioDao;
import com.respondeaqui.dao.mapper.FormularioRowMapper;
import com.respondeaqui.modelo.Formulario;

@Repository
public class FormularioDaoImpl implements FormularioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Formulario findById(int id_usuario) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from formulario where id_usuario = ?", 
					new FormularioRowMapper(), 
					id_usuario);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void criarFormulario(final Formulario formulario) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into formulario (id, link, titulo, descricao, dt_fechamento, dt_criacao, n_respostas, turno, sexo, id_usuario , id_cidade, id_campus, id_curso)"
						+ "values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, formulario.getLink());
				ps.setString(2, formulario.getTitulo());
				ps.setString(3, formulario.getDescricao());
				ps.setDate(4, Date.valueOf(formulario.convertToLocalDate(formulario.getDt_fechamento())));
				ps.setDate(5, new Date(formulario.getDt_criacao().getTime()));
				ps.setInt(6, formulario.getN_respostas());
				ps.setString(7, String.valueOf(formulario.getTurno()));
				ps.setString(8, String.valueOf(formulario.getSexo()));
				ps.setInt(9, Integer.parseInt(formulario.getId_usuario()));
				ps.setInt(10, formulario.getId_cidade());
				ps.setInt(11, formulario.getId_campus());
				ps.setInt(12, formulario.getId_curso());
				return ps;
			}
		});
	}

}
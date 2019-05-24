package com.respondeaqui.dao.implementacao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.dao.mapper.UsuarioRowMapper;
import com.respondeaqui.modelo.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Usuario findByMatricula(int matricula) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from usuario where matricula = ?", 
					new UsuarioRowMapper(), 
					matricula);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void criarUsuario(final Usuario usuario) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into usuario (matricula, nome, senha, dt_nascimento, turno, sexo, pontos, foto, id_cidade, id_campus, id_curso)"
								+ "value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, usuario.getMatricula());
				ps.setString(2, usuario.getNome());
				ps.setString(3, usuario.getSenha());
				ps.setDate(4, new java.sql.Date(usuario.getDt_nascimento().getTime()));
				ps.setString(5, String.valueOf(usuario.getTurno()));
				ps.setString(6, String.valueOf(usuario.getSexo()));
				ps.setInt(7, usuario.getPontos());
				ps.setInt(8, usuario.getFoto());
				ps.setInt(9, usuario.getId_cidade());
				ps.setInt(10, usuario.getId_campus());
				ps.setInt(11, usuario.getId_curso());
				return ps;
			}
		});
	}

}
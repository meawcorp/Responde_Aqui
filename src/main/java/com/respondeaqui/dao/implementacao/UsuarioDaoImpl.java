package com.respondeaqui.dao.implementacao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.dao.mapper.UsuarioRowMapper;
import com.respondeaqui.modelo.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Usuario findByMatricula(String matricula) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from usuario where matricula = ?", 
					new UsuarioRowMapper(), 
					Integer.parseInt(matricula));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void cadastrarUsuario(final Usuario usuario) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into usuario (matricula, nome, senha, dt_nascimento, turno, sexo, pontos, foto)"
						+ "values (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, Integer.parseInt(usuario.getMatricula()));
				ps.setString(2, usuario.getNome());
				ps.setString(3, new BCryptPasswordEncoder().encode(usuario.getSenha()));
				ps.setDate(4, new java.sql.Date(usuario.getDt_nascimento().getTime()));
				ps.setString(5, String.valueOf(usuario.getTurno()));
				ps.setString(6, String.valueOf(usuario.getSexo()));
				ps.setInt(7, usuario.getPontos());
				ps.setInt(8, usuario.getFoto());
				return ps;
			}
		});
	}

}
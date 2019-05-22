package com.respondeaqui.dao.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

}
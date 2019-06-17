package com.respondeaqui.dao.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.CidadeDao;
import com.respondeaqui.dao.mapper.CidadeRowMapper;
import com.respondeaqui.modelo.Cidade;

@Repository
public class CidadeDaoImpl implements CidadeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Cidade> getCidades() {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM cidade",
					new CidadeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
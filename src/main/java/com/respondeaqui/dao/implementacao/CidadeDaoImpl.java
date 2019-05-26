package com.respondeaqui.dao.implementacao;

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
	
	public Cidade findByIdCidade(int id_cidade) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from cidade where id = ?", 
					new CidadeRowMapper(), 
					id_cidade);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
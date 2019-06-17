package com.respondeaqui.dao.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.CampusDao;
import com.respondeaqui.dao.mapper.CampusRowMapper;
import com.respondeaqui.modelo.Campus;

@Repository
public class CampusDaoImpl implements CampusDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Campus> findByCidadeId(int id_cidade) {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM campus WHERE id_cidade = ?", 
					new CampusRowMapper(), 
					id_cidade);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
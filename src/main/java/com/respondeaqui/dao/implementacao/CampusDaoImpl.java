package com.respondeaqui.dao.implementacao;

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
	
	public Campus findByIdCampus(int id_campus) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from campus where id_cidade = ?", 
					new CampusRowMapper(), 
					id_campus);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
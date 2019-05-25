package com.respondeaqui.dao.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.CursoDao;
import com.respondeaqui.dao.mapper.CursoRowMapper;
import com.respondeaqui.modelo.Curso;

@Repository
public class CursoDaoImpl implements CursoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Curso findByIdCurso(int id_curso) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from campus_curso where id_campus = ?", 
					new CursoRowMapper(), 
					id_curso);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
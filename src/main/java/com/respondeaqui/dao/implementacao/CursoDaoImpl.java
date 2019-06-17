package com.respondeaqui.dao.implementacao;

import java.util.List;

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
	
	public List<Curso> findByCampusId(int id_campus) {
		try {
			return jdbcTemplate.query(
					"SELECT curso.nome, curso.id, id_campus FROM campus, curso, campus_curso WHERE curso.id = campus_curso.id_curso AND campus.id = ?", 
					new CursoRowMapper(), 
					id_campus);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
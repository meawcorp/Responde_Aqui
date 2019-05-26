package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.respondeaqui.modelo.Curso;


public class CursoRowMapper implements RowMapper<Curso> {

	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Curso curso =  new Curso();
		
		curso.setId(rs.getInt("id"));
		curso.setNome(rs.getString("nome"));
		curso.setId_campus(rs.getInt("id_campus"));
		
		return curso;
	}
}
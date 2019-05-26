package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.respondeaqui.modelo.Cidade;


public class CidadeRowMapper implements RowMapper<Cidade> {

	@Override
	public Cidade mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Cidade cidade =  new Cidade();
		
		cidade.setId(rs.getInt("id"));
		cidade.setNome(rs.getString("nome"));
		
		return cidade;
	}
}
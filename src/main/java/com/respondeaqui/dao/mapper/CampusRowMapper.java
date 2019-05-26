package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.respondeaqui.modelo.Campus;


public class CampusRowMapper implements RowMapper<Campus> {

	@Override
	public Campus mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Campus campus =  new Campus();
		
		campus.setId(rs.getInt("id"));
		campus.setNome(rs.getString("nome"));
		campus.setId_cidade(rs.getInt("id_cidade"));
		
		return campus;
	}
}
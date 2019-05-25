package com.respondeaqui.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.respondeaqui.modelo.Usuario;


public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Usuario usuario =  new Usuario();
		
		usuario.setMatricula(rs.getString("matricula"));
		usuario.setNome(rs.getString("nome"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setDt_nascimento(rs.getDate("dt_nascimento"));
		usuario.setTurno(rs.getString("turno").charAt(0));
		usuario.setSexo(rs.getString("sexo").charAt(0));
		usuario.setPontos(rs.getInt("pontos"));
		usuario.setFoto(rs.getInt("foto"));
		usuario.setId_cidade(rs.getInt("id_cidade"));
		usuario.setId_campus(rs.getInt("id_campus"));
		usuario.setId_curso(rs.getInt("id_curso"));
		
		return usuario;
	}

}
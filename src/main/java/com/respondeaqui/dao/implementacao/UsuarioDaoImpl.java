package com.respondeaqui.dao.implementacao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.dao.mapper.UsuarioRowMapper;
import com.respondeaqui.modelo.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired DataSource ds;
	
	//buscar os 10 usuários com maior pontuação
	public List<Usuario> ranking() {
		try {
			return jdbcTemplate.query(
					"SELECT *, ROW_NUMBER () OVER (ORDER BY usr.pontos DESC) FROM usuario as usr LIMIT 10",
					new UsuarioRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//buscar usuário pela matrícula
	public Usuario findByMatricula(String matricula) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from usuario where matricula = ?", 
					new UsuarioRowMapper(), 
					Integer.parseInt(matricula));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	//cadastrar usuário
	public void cadastrarUsuario(final Usuario usuario) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into usuario (matricula, nome, senha, dt_nascimento, turno, sexo, pontos, foto, id_cidade, id_campus, id_curso)"
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, Integer.parseInt(usuario.getMatricula()));
				ps.setString(2, usuario.getNome());
				ps.setString(3, new BCryptPasswordEncoder().encode(usuario.getSenha()));
				ps.setDate(4, new java.sql.Date(usuario.getDt_nascimento().getTime()));
				ps.setString(5, String.valueOf(usuario.getTurno()));
				ps.setString(6, String.valueOf(usuario.getSexo()));
				ps.setInt(7, usuario.getPontos());
				ps.setInt(8, usuario.getFoto());
				ps.setInt(9, usuario.getId_cidade());
				ps.setInt(10, usuario.getId_campus());
				ps.setInt(11, usuario.getId_curso());
				return ps;
			}
		});
	}
	
	//editar perfil
	public int editarPerfil(Usuario usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"UPDATE usuario SET nome = ?, dt_nascimento = ?, turno = ?, sexo = ?, foto = ?, id_cidade = ?, id_campus = ?, id_curso = ? WHERE matricula = ?");
			ps.setString(1, usuario.getNome());
			ps.setDate(2, new java.sql.Date(usuario.getDt_nascimento().getTime()));
			ps.setString(3, String.valueOf(usuario.getTurno()));
			ps.setString(4, String.valueOf(usuario.getSexo()));
			ps.setInt(5, usuario.getFoto());
			ps.setInt(6, usuario.getId_cidade());
			ps.setInt(7, usuario.getId_campus());
			ps.setInt(8, usuario.getId_curso());
			ps.setInt(9, Integer.parseInt(usuario.getMatricula()));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//mudar senha
	public int redefinirSenha(String senha, String matricula) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
				"UPDATE usuario SET senha = ? WHERE matricula = ?");
			ps.setString(1, new BCryptPasswordEncoder().encode(senha));
			ps.setInt(2, Integer.parseInt(matricula));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
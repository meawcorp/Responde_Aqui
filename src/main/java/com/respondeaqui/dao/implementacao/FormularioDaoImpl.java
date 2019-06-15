package com.respondeaqui.dao.implementacao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.respondeaqui.dao.FormularioDao;
import com.respondeaqui.dao.mapper.FormularioRowMapper;
import com.respondeaqui.modelo.Formulario;

@Repository
public class FormularioDaoImpl implements FormularioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired DataSource ds;
	
	public Formulario findById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from formulario where id = ?", 
					new FormularioRowMapper(), 
					id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Formulario> findByUserId(String matricula) {
		try {
			return jdbcTemplate.query(
					"select * from formulario where id_usuario = ?", 
					new FormularioRowMapper(), 
					Integer.parseInt(matricula));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int removerFormulario(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"delete from formulario where id = ?");
			ps.setInt(1, id);
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
	
	public void criarFormulario(final Formulario formulario) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into formulario (id, link, titulo, descricao, dt_fechamento, dt_criacao, n_respostas, turno, sexo, id_usuario , id_cidade, id_campus, id_curso)"
						+ "values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, formulario.getLink());
				ps.setString(2, formulario.getTitulo());
				ps.setString(3, formulario.getDescricao());
				ps.setDate(4, Date.valueOf(formulario.convertToLocalDate(formulario.getDt_fechamento())));
				ps.setDate(5, new Date(formulario.getDt_criacao().getTime()));
				ps.setInt(6, formulario.getN_respostas());
				ps.setString(7, String.valueOf(formulario.getTurno()));
				ps.setString(8, String.valueOf(formulario.getSexo()));
				ps.setInt(9, Integer.parseInt(formulario.getId_usuario()));
				ps.setInt(10, formulario.getId_cidade());
				ps.setInt(11, formulario.getId_campus());
				ps.setInt(12, formulario.getId_curso());
				return ps;
			}
		});
	}
	
	public int editarFormulario(Formulario formulario) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"update formulario set link = ?, titulo = ?, descricao = ?, dt_fechamento = ?, sexo = ?, id_cidade = ?, id_campus = ?, id_curso = ? where id = ?");
			ps.setString(1, formulario.getLink());
			ps.setString(2, formulario.getTitulo());
			ps.setString(3, formulario.getDescricao());
			ps.setDate(4, Date.valueOf(formulario.convertToLocalDate(formulario.getDt_fechamento())));
			ps.setString(5, String.valueOf(formulario.getSexo()));
			ps.setInt(6, formulario.getId_cidade());
			ps.setInt(7, formulario.getId_campus());
			ps.setInt(8, formulario.getId_curso());
			ps.setInt(9, formulario.getId());
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
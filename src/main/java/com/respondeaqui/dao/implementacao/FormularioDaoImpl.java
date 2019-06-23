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
import com.respondeaqui.modelo.Usuario;

@Repository
public class FormularioDaoImpl implements FormularioDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired DataSource ds;
	
	public Formulario findById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM formulario AS form, usuario AS usr WHERE form.id_usuario = usr.matricula AND form.id = ?", 
					new FormularioRowMapper(), 
					id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Formulario> findByMatricula(String matricula) {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM formulario AS form, usuario AS usr WHERE (form.id_usuario = usr.matricula) AND form.id IN (SELECT f.id FROM formulario AS f, formularios_respondidos AS form_resp WHERE f.id = form_resp.id_formulario AND form_resp.id_usuario = ?)",
					new FormularioRowMapper(),
					Integer.parseInt(matricula));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Formulario> findByUserId(String matricula) {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM formulario AS form, usuario AS usr WHERE form.id_usuario = usr.matricula AND form.id_usuario = ?", 
					new FormularioRowMapper(), 
					Integer.parseInt(matricula));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Formulario> findByUser(Usuario usuario) {
		try {
			return jdbcTemplate.query(
					"SELECT * FROM formulario AS form, usuario AS usr WHERE (form.id_usuario = usr.matricula) AND form.id NOT IN (SELECT f.id FROM formulario AS f, formularios_respondidos AS form_resp WHERE f.id = form_resp.id_formulario AND f.id_usuario <> ?) AND (form.sexo = ? OR form.sexo = 'x') AND (form.turno = ? OR form.turno = 'x') AND (form.id_cidade = ? OR form.id_cidade = 0) AND (form.id_campus = ? OR form.id_campus = 0) AND (form.id_curso = ? OR form.id_curso = 0)", 
					new FormularioRowMapper(), 
					Integer.parseInt(usuario.getMatricula()), usuario.getSexo(), usuario.getTurno(), usuario.getId_cidade(), usuario.getId_campus(), usuario.getId_curso());
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
	
<<<<<<< Updated upstream
=======
	//remover todos formulários do usuário
	public int removerFormularios(String matricula) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"delete from formulario where id_usuario = ?");
			ps.setInt(1, Integer.parseInt(matricula));
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
	
	//remover todos os formulários respondidos pelo usuário
	public int removerFormulariosResp(String matricula) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"DELETE FROM formularios_respondidos WHERE id_usuario = ?");
			ps.setInt(1, Integer.parseInt(matricula));
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
	
	//criar um formulário
>>>>>>> Stashed changes
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
	
	public void confirmarRespostaForm(int id, String matricula) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into formularios_respondidos (id_formulario, id_usuario)"
						+ "values (?, ?)");
				ps.setInt(1, id);
				ps.setInt(2, Integer.parseInt(matricula));
				return ps;
			}
		});
	}
	
	public int atualizarNumRespostas(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"UPDATE formulario SET n_respostas = n_respostas + 1 WHERE formulario.id = ?");
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
	
	public int atualizarPontos(String matricula) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(
					"UPDATE usuario SET pontos = pontos + 100 WHERE usuario.matricula = ?");
			ps.setInt(1, Integer.parseInt(matricula));
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
package com.respondeaqui.modelo;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.respondeaqui.annotation.Idade;
import com.respondeaqui.annotation.Select;
import com.respondeaqui.annotation.Sexo;
import com.respondeaqui.annotation.Turno;


public class Usuario {
	@NotEmpty
	@Size(min = 6)
	private String matricula;
	@NotEmpty
	private String nome;
	@Size(min = 6, max = 9)
	private String senha;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Idade(value = 18)
	@NotNull
	private Date dt_nascimento;
	@Turno
	private char turno;
	@Sexo
	private char sexo;
	
	private int pontos;
	
	private int foto;
	@Select
	private int id_cidade;
	private String cidade;
	@Select
	private int id_campus;
	private String campus;
	@Select
	private int id_curso;
	private String curso;
	
	public Usuario() {}
	
	public Usuario(String matricula, String nome, String senha, Date dt_nascimento, char turno, char sexo,
			int foto, int id_cidade, int id_campus, int id_curso) {

		this.matricula = matricula;
		this.nome = nome;
		this.senha = senha;
		this.dt_nascimento = dt_nascimento;
		this.turno = turno;
		this.sexo = sexo;
		this.foto = foto;
		this.id_cidade = id_cidade;
		this.id_campus = id_campus;
		this.id_curso = id_curso;  
	}

	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Date getDt_nascimento() {
		return this.dt_nascimento;
	}
	
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getFoto() {
		return foto;
	}

	public void setFoto(int foto) {
		this.foto = foto;
	}

	public int getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}

	public int getId_campus() {
		return id_campus;
	}

	public void setId_campus(int id_campus) {
		this.id_campus = id_campus;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

}

package com.respondeaqui.modelo;

import java.util.Date;

public class Usuario {
	private int matricula;
	private String nome;
	private String senha;
	private Date dt_nascimento;
	private char turno;
	private char sexo;
	private int pontos;
	private int foto;
	private int id_cidade;
	private int id_campus;
	private int id_curso;
	
	public Usuario() {}
	
	public Usuario(int matricula, String nome, String senha, Date dt_nascimento, char turno, char sexo, int pontos,
			int foto, int id_cidade, int id_campus, int id_curso) {
		
		this.matricula = matricula;
		this.nome = nome;
		this.senha = senha;
		this.dt_nascimento = dt_nascimento;
		this.turno = turno;
		this.sexo = sexo;
		this.pontos = pontos;
		this.foto = foto;
		this.id_cidade = id_cidade;
		this.id_campus = id_campus;
		this.id_curso = id_curso;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
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
		return dt_nascimento;
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

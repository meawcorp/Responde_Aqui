package com.respondeaqui.modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Formulario {
	private Usuario usuario;
	private int id;
	private String link;
	private String titulo;
	private String descricao;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dt_fechamento;
	private Date dt_criacao;
	private int n_respostas;
	private char turno;
	private char sexo;
	private String id_usuario;
	private int id_cidade;
	private int id_campus;
	private int id_curso;
	
	public Formulario(){}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = extrairLink(link);
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDt_fechamento() {
		return this.dt_fechamento;
	}
	
	public void setDt_fechamento(Date dt_fechamento) {
		this.dt_fechamento = dt_fechamento;
	}
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public int getN_respostas() {
		return n_respostas;
	}
	public void setN_respostas(int n_respostas) {
		this.n_respostas = n_respostas;
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
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String string) {
		this.id_usuario = string;
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
	
    public String extrairLink(String link_form) {
		String link = link_form;
		link = link.replace("https://docs.google.com/forms/d/e/", "").replace("/viewform", "");
		return  link;
    }
    
	public LocalDate convertToLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate().plusDays(1);
	}
}

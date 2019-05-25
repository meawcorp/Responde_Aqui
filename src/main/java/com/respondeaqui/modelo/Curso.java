package com.respondeaqui.modelo;

public class Curso {
	private int id;
	private String nome;
	private int id_campus;
	
	public Curso(){}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId_campus() {
		return id_campus;
	}
	
	public void setId_campus(int id_campus) {
		this.id_campus = id_campus;
	}

}

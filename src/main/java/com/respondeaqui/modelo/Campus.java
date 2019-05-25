package com.respondeaqui.modelo;

public class Campus {
	private int id;
	private String nome;
	private int id_cidade;

	public Campus(){};
	
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
	
	public int getId_cidade() {
		return id_cidade;
	}
	
	public void setId_cidade(int id_cidade) {
		this.id_cidade = id_cidade;
	}
	
}

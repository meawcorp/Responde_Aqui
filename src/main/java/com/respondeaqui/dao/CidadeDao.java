package com.respondeaqui.dao;

import com.respondeaqui.modelo.Cidade;

public interface CidadeDao {
	
	Cidade findByIdCidade(int id_cidade);
}

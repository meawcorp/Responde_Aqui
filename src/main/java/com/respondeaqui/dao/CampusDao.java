package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Campus;

public interface CampusDao {
	
	List<Campus> findByCidadeId(int id_cidade);
}
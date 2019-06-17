package com.respondeaqui.dao;

import java.util.List;

import com.respondeaqui.modelo.Curso;

public interface CursoDao {
	
	List<Curso> findByCampusId(int id_campus);
}

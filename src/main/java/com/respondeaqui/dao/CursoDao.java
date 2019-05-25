package com.respondeaqui.dao;

import com.respondeaqui.modelo.Curso;

public interface CursoDao {
	
	Curso findByIdCurso(int id_curso);
}

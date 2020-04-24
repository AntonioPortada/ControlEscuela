package com.escuela.spring.web.app.service;

import java.util.List;

import com.escuela.spring.web.app.entity.Alumno;

public interface IAlumnoService {

	public List<Alumno> findAll();

	public void save(Alumno alumno);
	
	public Alumno findOne(Integer id);
	
	public void delete(Integer id);
}

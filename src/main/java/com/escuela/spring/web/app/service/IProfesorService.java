package com.escuela.spring.web.app.service;

import java.util.List;

import com.escuela.spring.web.app.entity.Profesor;

public interface IProfesorService {

	public List<Profesor> findAll();
	
	public Profesor findOne(Integer id);
	
	public void save(Profesor p);
	
	public void delete(Integer id);
}

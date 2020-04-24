package com.escuela.spring.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.spring.web.app.dao.IAlumnoDao;
import com.escuela.spring.web.app.entity.Alumno;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDao alumnoDao;
	
	@Transactional(readOnly = true)
	public List<Alumno> findAll(){
		
		return (List<Alumno>)alumnoDao.findAll();
	}
	
	@Transactional
	public void save(Alumno a) {
		alumnoDao.save(a);
	}
	
	@Transactional(readOnly = true)
	public Alumno findOne(Integer id) {
		
		return alumnoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void delete(Integer id) {
		
		alumnoDao.deleteById(id);
	}
}

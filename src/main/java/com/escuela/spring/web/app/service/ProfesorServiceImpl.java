package com.escuela.spring.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.spring.web.app.dao.IProfesorDao;
import com.escuela.spring.web.app.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorDao profesorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Profesor> findAll() {
		
		return (List<Profesor>)profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findOne(Integer id) {
		
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	public void save(Profesor p) {
		profesorDao.save(p);
	}	
	
	public void delete(Integer id) {
		profesorDao.deleteById(id);;
	}
}

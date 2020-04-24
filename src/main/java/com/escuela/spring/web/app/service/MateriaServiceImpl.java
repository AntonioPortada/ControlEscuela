package com.escuela.spring.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.spring.web.app.dao.IMateriaDao;
import com.escuela.spring.web.app.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaDao materiaDao;
	
	@Transactional(readOnly = true)
	public List<Materia> findAll(){
		
		return (List<Materia>)materiaDao.findAll();
	}
}

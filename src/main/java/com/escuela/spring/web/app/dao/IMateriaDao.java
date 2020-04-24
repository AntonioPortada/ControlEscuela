package com.escuela.spring.web.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.escuela.spring.web.app.entity.Materia;

public interface IMateriaDao extends CrudRepository<Materia, Integer>{

}

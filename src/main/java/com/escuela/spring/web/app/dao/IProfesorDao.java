package com.escuela.spring.web.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.escuela.spring.web.app.entity.Profesor;

public interface IProfesorDao extends CrudRepository<Profesor, Integer>{

}

package com.escuela.spring.web.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.escuela.spring.web.app.entity.Alumno;

public interface IAlumnoDao extends CrudRepository<Alumno, Integer>{

}

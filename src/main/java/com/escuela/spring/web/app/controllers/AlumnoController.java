package com.escuela.spring.web.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.escuela.spring.web.app.entity.Alumno;
import com.escuela.spring.web.app.service.AlumnoServiceImpl;
import com.escuela.spring.web.app.service.MateriaServiceImpl;

@Controller
@RequestMapping("alumno")
@SessionAttributes("alumno")
public class AlumnoController {

	private int i = 1;
	
	@Autowired
	private AlumnoServiceImpl alumnoService;
	
	@Autowired
	private MateriaServiceImpl materiaService;
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		
		Map<Integer, Alumno> a = new HashMap<Integer, Alumno>();
		
		alumnoService.findAll().forEach(action -> {
			a.put(i++, action);
		});
		
		model.addAttribute("alumnos", a);
		
		i = 1;
		return "alumno/listar";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Alumno alumno = new Alumno();
		
		model.addAttribute("alumno", alumno);
		
		return "alumno/form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("error", result.getFieldError());
			
			return "alumno/form";
		}
		
		model.addAttribute("success", "Se guardo correctamente");
		model.addAttribute("alumno", alumno);
		
		alumnoService.save(alumno);
		
		status.setComplete();
		
		//return "resultado";
		return "redirect:/alumno/listar";
	}
	
	@RequestMapping("/form/{id}")
	public String edit(@PathVariable(value="id") Integer id, Model model) {
		
		Alumno a = null;
		
		if(id!=null) {
			a = alumnoService.findOne(id);
		}
		else
			return "redirect:/alumno/listar";
		
		model.addAttribute("alumno", a);
		
		return "alumno/form";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		
		model.addAttribute("success", "Eliminado correctamente");
		alumnoService.delete(id);
		
		return "redirect:/alumno/listar";
	}
	
	@Secured("ROLE_ALUMNO")
	@RequestMapping("/detalle/{id}")
	public String detail(@PathVariable(value="id") Integer id, Model model) {
		
		Alumno a = alumnoService.findOne(id);
		
		model.addAttribute("encabezado", "Detalles del Alumno");
		model.addAttribute("alumno", a);
		model.addAttribute("materias", materiaService.findAll());
		
		return "alumno/detalle";
	}
}

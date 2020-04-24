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

import com.escuela.spring.web.app.entity.Profesor;
import com.escuela.spring.web.app.service.ProfesorServiceImpl;

@Controller
@RequestMapping("profesor")
@SessionAttributes("profesor")
public class ProfesorController {

	private int i = 1;
	
	@Autowired
	private ProfesorServiceImpl profesorService;
	
	@RequestMapping("listar")
	public String listar(Model model) {
		
		Map<Integer, Profesor> p = new HashMap<Integer, Profesor>();
		
		profesorService.findAll().forEach(action -> {
			p.put(i++, action);
		});
		
		model.addAttribute("profesores", p);
		
		i = 1;
		
		return "profesor/listar";
	}
	
	@Secured("ROLE_MAESTRO")
	@RequestMapping("detalle/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("encabezado", "Detalle del Profesor");
		model.addAttribute("profesor", profesorService.findOne(id));
		
		return "profesor/detalle";
	}
	
	@GetMapping("form")
	public String form(Model model) {
		
		Profesor p = new Profesor();
		
		model.addAttribute("profesor", p);
		
		return "profesor/form";
	}
	
	@PostMapping("form")
	public String process(@Valid Profesor p, BindingResult result, SessionStatus status, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("error", result);
			
			return "profesor/form";
		}
		
		model.addAttribute("encabezado", "Formulario de Registro");
		model.addAttribute("profesor", p);
		
		profesorService.save(p);
		
		status.setComplete();
		
		return "redirect:/profesor/listar";
	}
	
	@RequestMapping("form/{id}")
	public String edit(@PathVariable(value="id") Integer id, Model model) {
		
		Profesor p = new Profesor();
		
		if(id!=null)
			p = profesorService.findOne(id);
		else
			return "redirect:/profesor/listar";
		
		model.addAttribute("profesor", p);
		
		return "profesor/form";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable(value="id") Integer id) {
		
		profesorService.delete(id);;
		
		return "redirect:/profesor/listar";
	}
}

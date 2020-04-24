package com.escuela.spring.web.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required = false) String error, 
			            @RequestParam(value="logout", required = false) String salir,
			            Model model, Principal principal) {
		
		if(principal != null) {
			model.addAttribute("info", "Ya has iniciado sesión");
			
			return "redirect:/alumno/listar";
		}
		
		if(error != null) {
			model.addAttribute("info", "Error en el usuario y/o contraseña, intentalo de nuevo");
		}
		
		if(salir != null) {
			model.addAttribute("success", "Has salido correctamente");
		}
		
		return "login";
	}
	
	@GetMapping("/error")
	public String error() {
		
		return "error";
	}
}

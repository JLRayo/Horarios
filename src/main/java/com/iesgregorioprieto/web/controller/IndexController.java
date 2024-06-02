package com.iesgregorioprieto.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.iesgregorioprieto.web.entity.Usuario;
import com.iesgregorioprieto.web.horarios.service.CursoService;
import com.iesgregorioprieto.web.horarios.service.IAsignaturaService;
import com.iesgregorioprieto.web.horarios.service.IAulaService;
import com.iesgregorioprieto.web.horarios.service.ProfesorService;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.utilidades.Variables;

/**
 * Clase para controlar la página de inicio de la aplicación e interactuar con
 * las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Controller
public class IndexController {

	@Autowired
	@Qualifier("AulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("AsignaturaService")
	private IAsignaturaService asignaturaService;

	@Autowired
	@Qualifier("CursoService")
	private CursoService cursoService;

	@Autowired
	@Qualifier("ProfesorService")
	private ProfesorService profesorService;

	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;

	@GetMapping({ "/inicio", "/", "" })
	public String inicio(Model model) {

		model.addAttribute("titulo", "Inicio");
		model.addAttribute("totalCursos", cursoService.contar());
		model.addAttribute("totalProfesores", profesorService.contar());
		model.addAttribute("totalAulas", aulaService.contar());
		model.addAttribute("totalAsignaturas", asignaturaService.contar());			

		return "index";
	}

	@ModelAttribute("usuarioRegistrado")
	public Usuario usuarioRegistrado(Authentication authentication) {
		if (authentication != null) {
			Variables.idUsuarioRegistrado = authentication.getName();
			return usuarioService.buscarPorNombre(authentication.getName());
		} else {
			return null;
		}
	}
}

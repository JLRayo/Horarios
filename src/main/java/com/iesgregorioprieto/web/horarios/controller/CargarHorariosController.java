package com.iesgregorioprieto.web.horarios.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iesgregorioprieto.web.entity.Usuario;
import com.iesgregorioprieto.web.horarios.service.IAsignaturaService;
import com.iesgregorioprieto.web.horarios.service.IAulaService;
import com.iesgregorioprieto.web.horarios.service.ICursoService;
import com.iesgregorioprieto.web.horarios.service.IHoraService;
import com.iesgregorioprieto.web.horarios.service.IProfesorService;
import com.iesgregorioprieto.web.horarios.utilidades.LeerFicheroHorarios;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.utilidades.Variables;

/**
 * Clase para controlar la carga de horarios mediante un fichero excel e
 * interactuar con las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Controller
@RequestMapping("/cargar_horarios")
public class CargarHorariosController {
	@Autowired
	@Qualifier("HoraService")
	private IHoraService horaService;

	@Autowired
	@Qualifier("ProfesorService")
	private IProfesorService profesorService;

	@Autowired
	@Qualifier("CursoService")
	private ICursoService cursoService;

	@Autowired
	@Qualifier("AulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("AsignaturaService")
	private IAsignaturaService asignaturaService;

	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;

	@GetMapping("/cargar_horarios_alumnado")
	public String subirHorariosAlumnado(Model model, Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("usuario", usuarioService.buscarPorNombre(authentication.getName()));
		}
		model.addAttribute("alumnos", true);
		model.addAttribute("titulo", "Cargar horarios alumnado");
		return "subir_horarios";
	}

	@GetMapping("/cargar_horarios_profesorado")
	public String subirHorariosProfesorado(Model model, Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("usuario", usuarioService.buscarPorNombre(authentication.getName()));
		}
		model.addAttribute("alumnos", false);
		model.addAttribute("titulo", "Cargar horarios profesorado");
		return "subir_horarios";
	}

	@PostMapping("/extraer_horarios_alumnado")
	public String clickSubirHorariosAlumnado(@RequestParam("files") MultipartFile[] excelHorarios, Model model,
			Authentication authentication) throws IOException {
			
			System.out.println("Nulo= "+excelHorarios != null);
			System.out.println("Tamaño"+excelHorarios.length);
		LeerFicheroHorarios.ficheroAlumnado(excelHorarios, horaService, cursoService, aulaService, profesorService,
				asignaturaService, usuarioService, model, authentication);
		
		return "subir_horarios";
	}

	@PostMapping("/extraer_horarios_profesorado")
	public String clickSubirHorariosProfesorado(@RequestParam("files") MultipartFile[] excelHorarios, Model model,
			Authentication authentication) throws IOException {
			LeerFicheroHorarios.ficheroProfesorado(excelHorarios, horaService, cursoService, aulaService, profesorService,
					asignaturaService, usuarioService, model, authentication);

		
		return "subir_horarios";
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

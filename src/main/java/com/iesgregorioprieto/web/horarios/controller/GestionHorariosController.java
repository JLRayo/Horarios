package com.iesgregorioprieto.web.horarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesgregorioprieto.web.entity.Usuario;
import com.iesgregorioprieto.web.horarios.entity.Asignatura;
import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.service.IAsignaturaService;
import com.iesgregorioprieto.web.horarios.service.IAulaService;
import com.iesgregorioprieto.web.horarios.service.ICursoService;
import com.iesgregorioprieto.web.horarios.service.IHoraService;
import com.iesgregorioprieto.web.horarios.service.IProfesorService;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.utilidades.Constantes;
import com.iesgregorioprieto.web.utilidades.Variables;

/**
 * Clase para controlar la gestión de los horarios: eliminarlos, listarlos, ver
 * sus detalles, asignar el nombre a un curso o una asignatura e interactuar con
 * las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Controller
public class GestionHorariosController {
	@Autowired
	@Qualifier("HoraService")
	private IHoraService horaService;

	@Autowired
	@Qualifier("AulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("AsignaturaService")
	private IAsignaturaService asignaturaService;

	@Autowired
	@Qualifier("CursoService")
	private ICursoService cursoService;

	@Autowired
	@Qualifier("ProfesorService")
	private IProfesorService profesorService;

	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;

	/* LISTAR */
	@GetMapping("/horarios_alumnado")
	public String listarHorariosAlumnado(Model model) {
		model.addAttribute("cursos", cursoService.retornarTodos());
		model.addAttribute("titulo", "Gestión de cursos");
		return "listar";
	}

	@GetMapping("/horarios_profesorado")
	public String listarHorariosProfesorado(Model model) {
		model.addAttribute("profesores", profesorService.retornarTodos());
		model.addAttribute("titulo", "Gestión de profesores");
		return "listar";
	}

	@GetMapping("/aulas")
	public String listarHorariosAulas(Model model) {
		model.addAttribute("aulas", aulaService.retornarTodos());
		model.addAttribute("titulo", "Gestión de aulas");
		return "listar";
	}

	@GetMapping("/asignaturas")
	public String listarAsignaturas(Model model) {
		model.addAttribute("asignaturas", asignaturaService.retornarTodos());
		model.addAttribute("titulo", "Gestión de asignaturas");
		return "listar";
	}

	/* ELIMINAR */
	@RequestMapping(value = "/eliminar_curso/{idCurso}")
	public String eliminarCurso(@PathVariable(value = "idCurso") String idCurso) {
		horaService.eliminarHorasCurso(cursoService.buscar(idCurso));
		cursoService.eliminar(idCurso);
		return "redirect:/horarios_alumnado";
	}

	@RequestMapping(value = "/eliminar_profesor/{idProfesor}")
	public String eliminarProfesor(@PathVariable(value = "idProfesor") String idProfesor) {
		horaService.eliminarHorasProfesor(profesorService.buscar(idProfesor));
		profesorService.eliminar(idProfesor);
		return "redirect:/horarios_profesorado";
	}

	@RequestMapping(value = "/eliminar_aula/{idAula}")
	public String eliminarAula(@PathVariable(value = "idAula") String idAula) {
		horaService.eliminarHorasAula(aulaService.buscar(idAula));
		aulaService.eliminar(idAula);
		return "redirect:/aulas";
	}

	@RequestMapping(value = "/eliminar_asignatura/{idAsignatura}")
	public String eliminarAsignatura(@PathVariable(value = "idAsignatura") String idAsignatura) {
		horaService.eliminarHorasAsignatura(asignaturaService.buscar(idAsignatura));
		asignaturaService.eliminar(idAsignatura);
		return "redirect:/asignaturas";
	}

	/* DETALLES */
	@RequestMapping(value = "/detalles_horario_alumnado/{idCurso}")
	public String detallesHorarioCurso(@PathVariable(value = "idCurso") String idCurso, Model model) {
		ArrayList<Hora> horas;
		for (int i = 0; i < Constantes.DIAS_SEMANA.length; i++) {
			for (int j = 0; j < Constantes.TOTAL_HORAS_JORNADA; j++) {
				horas = (ArrayList<Hora>) horaService.buscarHorasCursoDiaPosicion(
						cursoService.buscar(idCurso.replace("%", " ")), Constantes.DIAS_SEMANA[i], j + 1);
				model.addAttribute("horas" + Constantes.DIAS_SEMANA[i].replace("é", "e") + j,
						infoHoras(horas, "curso"));
			}
		}
		model.addAttribute("titulo", "Detales curso " + idCurso);
		model.addAttribute("curso", cursoService.buscar(idCurso));
		return "detalles_horario";
	}

	@RequestMapping(value = "/detalles_horario_profesorado/{idProfesor}")
	public String detallesHorarioProfesor(@PathVariable(value = "idProfesor") String idProfesor, Model model) {
		ArrayList<Hora> horas;
		for (int i = 0; i < Constantes.DIAS_SEMANA.length; i++) {
			for (int j = 0; j < Constantes.TOTAL_HORAS_JORNADA; j++) {
				horas = (ArrayList<Hora>) horaService.buscarHorasProfesorDiaPosicion(
						profesorService.buscar(idProfesor.replace("%", " ")), Constantes.DIAS_SEMANA[i], j + 1);
				model.addAttribute("horas" + Constantes.DIAS_SEMANA[i].replace("é", "e") + j,
						infoHoras(horas, "profesor"));
			}
		}
		model.addAttribute("titulo", "Detalles profesor " + idProfesor);
		return "detalles_horario";
	}

	@RequestMapping(value = "/detalles_horario_aula/{idAula}")
	public String detallesHorarioAula(@PathVariable(value = "idAula") String idAula, Model model) {
		ArrayList<Hora> horas;
		for (int i = 0; i < Constantes.DIAS_SEMANA.length; i++) {
			for (int j = 0; j < Constantes.TOTAL_HORAS_JORNADA; j++) {
				horas = (ArrayList<Hora>) horaService.buscarHorasAulaDiaPosicion(
						aulaService.buscar(idAula.replace("%", " ")), Constantes.DIAS_SEMANA[i], j + 1);
				model.addAttribute("horas" + Constantes.DIAS_SEMANA[i].replace("é", "e") + j, infoHoras(horas, "aula"));
			}
		}
		model.addAttribute("titulo", "Detalles aula " + idAula.replace("%", " "));
		return "detalles_horario";
	}

	@RequestMapping(value = "/detalles_horario_asignatura/{idAsignatura}")
	public String detallesHorarioAsignatura(@PathVariable(value = "idAsignatura") String idAsignatura, Model model) {
		ArrayList<Hora> horas;
		for (int i = 0; i < Constantes.DIAS_SEMANA.length; i++) {
			for (int j = 0; j < Constantes.TOTAL_HORAS_JORNADA; j++) {
				horas = (ArrayList<Hora>) horaService.buscarHorasAsignaturaDiaPosicion(
						asignaturaService.buscar(idAsignatura.replace("%", " ")), Constantes.DIAS_SEMANA[i], j + 1);
				model.addAttribute("horas" + Constantes.DIAS_SEMANA[i].replace("é", "e") + j,
						infoHoras(horas, "asignatura"));
			}
		}
		model.addAttribute("titulo", "Detalles asignatura " + idAsignatura.replace("%", " "));
		model.addAttribute("asignatura", asignaturaService.buscar(idAsignatura));
		return "detalles_horario";
	}

	/* GUARDAR NOMBRE */
	@PostMapping("/detalles_horario_asignatura/guardar_nombre")
	public String guardarNombreAsignatura(Asignatura asignatura) {
		asignaturaService.guardar(asignatura);
		return "redirect:/detalles_horario_asignatura/" + asignatura.getIdAsignatura();
	}

	@PostMapping("/detalles_horario_alumnado/guardar_nombre")
	public String guardarNombreCurso(Curso curso) {
		cursoService.guardar(curso);
		return "redirect:/detalles_horario_alumnado/" + curso.getIdCurso();
	}

	/*
	 * Extrae la información de una lista de horas como una cadena de caracteres
	 * para html. Para que los saltos de línea sean efectivos, el atributo de
	 * thymeleaf en la plantilla debe ser 'utext' en lugar de 'text'
	 * 
	 * @param horas son las horas que se van a procesar
	 * 
	 * @param tipoEntidad es la entidad para la que se requiere el horario, para
	 * seleccionar la información que se desea mostrar. Las opciones son "curso",
	 * "profesor", "aula", "asignatura"
	 * 
	 * @return una cadena de caracteres con la información de las horas y saltos de
	 * línea de html
	 * 
	 */
	private String infoHoras(List<Hora> horas, String tipoEntidad) {
		String stringHorasDia;
		int contador;

		stringHorasDia = "";
		contador = 0;
		switch (tipoEntidad) {
		case "curso":

			for (Hora hora : horas) {
				if (contador > 0) {
					stringHorasDia += "<br/>-----------<br/>";
				}
				stringHorasDia += hora.getAsignatura().getIdAsignatura() + "<br/>" + hora.getProfesor().getIdProfesor()
						+ "<br/>" + hora.getAula().getIdAula();
				contador++;
			}
			break;
		case "profesor":
			for (Hora hora : horas) {
				if (contador > 0) {
					stringHorasDia += "<br/>-----------<br/>";
				}
				stringHorasDia += hora.getAsignatura().getIdAsignatura() + "<br/>" + hora.getCurso().getIdCurso()
						+ "<br/>" + hora.getAula().getIdAula();
				contador++;
			}
			break;
		case "aula":
			for (Hora hora : horas) {
				if (contador > 0) {
					stringHorasDia += "<br/>-----------<br/>";
				}
				stringHorasDia += hora.getAsignatura().getIdAsignatura() + "<br/>" + hora.getCurso().getIdCurso()
						+ "<br/>" + hora.getProfesor().getIdProfesor();
				contador++;
			}
			break;
		case "asignatura":
			for (Hora hora : horas) {
				if (contador > 0) {
					stringHorasDia += "<br/>-----------<br/>";
				}
				stringHorasDia += hora.getCurso().getIdCurso() + "<br/>" + hora.getProfesor().getIdProfesor() + "<br/>"
						+ hora.getAula().getIdAula();
				contador++;
			}
			break;
		}
		return stringHorasDia;
	}

	/*
	 * @ModelAttribute("cursos") public List<Curso> cursos() { return
	 * cursoService.retornarTodos(); }
	 * 
	 * @ModelAttribute("profesores") public List<Profesor> profesores() { return
	 * profesorService.retornarTodos(); }
	 */
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

package com.iesgregorioprieto.web.horarios.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.service.IAsignaturaService;
import com.iesgregorioprieto.web.horarios.service.IAulaService;
import com.iesgregorioprieto.web.horarios.service.ICursoService;
import com.iesgregorioprieto.web.horarios.service.IHoraService;
import com.iesgregorioprieto.web.horarios.service.IProfesorService;
import com.iesgregorioprieto.web.service.IUsuarioService;

/**
 * Clase para controlar la gestión de los horarios: eliminarlos, listarlos, ver
 * sus detalles, asignar el nombre a un curso o una asignatura e interactuar con
 * las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@RestController
@RequestMapping("/rest")
public class CursoRestController {
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

	// /rest/horas-cursos

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/cursos/horas") public List<Hora> restHoras() { return
	 * cursoService.retornarTodos(); }
	 */

	// /rest/cursos
	// @ResponseBody
	@SuppressWarnings("unchecked")
	@GetMapping("/cursos")
	public JSONObject restCursos() {

		JSONObject padre = new JSONObject();
		JSONArray cursos = new JSONArray();
		JSONObject cursoJson;

		for (Curso curso : cursoService.retornarTodos()) {
			cursoJson = new JSONObject();
			cursoJson.put("idCurso", curso.getIdCurso());
			cursoJson.put("nombreCurso", curso.getNombre());
			cursos.add(cursoJson);
		}

		padre.put("curso", cursos);

		System.out.println(padre);

		return padre;
	}

	// /rest/horas/BTO-1CI
	@SuppressWarnings("unchecked")
	// @ResponseBody
	@GetMapping("/horas/{idCurso}")
	public JSONObject restHoras(@PathVariable(value = "idCurso") String idCurso) {
		JSONObject padre;
		JSONArray horasJson;
		JSONObject horaJson;
		JSONObject claseJson;
		JSONObject profesorJson;
		JSONObject aulaJson;
		JSONArray listaProfesoresJson;
		JSONObject asignaturaJson;
		JSONObject cursoJson;
	
		horasJson = new JSONArray();
		ArrayList<Long> horasDescartadas = new ArrayList<Long>();

		for (Hora hora : horaService.buscarHorasCurso(cursoService.buscar(idCurso))) {
			if (!horasDescartadas.contains(hora.getId())) {
				claseJson = new JSONObject();
				horaJson = new JSONObject();
				aulaJson= new JSONObject();
				asignaturaJson = new JSONObject();
				cursoJson = new JSONObject();

				listaProfesoresJson = new JSONArray();
				profesorJson = new JSONObject();
				// Profesores
				for (Hora horaClase : horaService.buscarCursoDiaPosicionJornadaAsignaturaAula(hora.getCurso(),
						hora.getDia(), hora.getPosicionJornada(), hora.getAsignatura(), hora.getAula())) {
					profesorJson = new JSONObject();
					profesorJson.put("idProfesor", horaClase.getProfesor().getIdProfesor());
					profesorJson.put("nombreProfesor", horaClase.getProfesor().getNombreProfesor());

					listaProfesoresJson.add(profesorJson);
					horasDescartadas.add(horaClase.getId());
				}

				// Asignatura
				asignaturaJson.put("idAsignatura", hora.getAsignatura().getIdAsignatura());
				asignaturaJson.put("nombreAsignatura", hora.getAsignatura().getNombre());

				// Aula
				aulaJson.put("idAula", hora.getAula().getIdAula());
				//clase
				claseJson.put("aula", aulaJson);
				claseJson.put("profesor", listaProfesoresJson);
				claseJson.put("asignatura", asignaturaJson);

				// Curso
				cursoJson.put("idCurso", hora.getCurso().getIdCurso());
				cursoJson.put("nombreCurso", hora.getCurso().getNombre());
				// Hora
				horaJson.put("dia", hora.getDia());
				horaJson.put("posicionJornada", hora.getPosicionJornada());
				horaJson.put("curso", cursoJson);

				horaJson.put("clase", claseJson);
				// Lista horas
				horasJson.add(horaJson);
			}

		}
		padre = new JSONObject();
		padre.put("hora", horasJson);
		return padre;
	}

}

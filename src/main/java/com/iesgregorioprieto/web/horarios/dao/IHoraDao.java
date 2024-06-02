package com.iesgregorioprieto.web.horarios.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.horarios.entity.Asignatura;
import com.iesgregorioprieto.web.horarios.entity.Aula;
import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.entity.Profesor;

/**
 * Interfaz para gestionar el repositorio de la tabla 'horas'.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IHoraDao extends CrudRepository<Hora, Long> {

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param curso           curso al que se imparten las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * 
	 * @return lista de horas de un curso concreto, un día concreto y una posición en la jornada concreta
	 */
	public List<Hora> findByCursoAndDiaAndPosicionJornada(Curso curso, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param profesor        profesor que imparte las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * 
	 * @return lista de horas de un profesor concreto, un día concreto y una posición en la jornada concreta
	 */
	public List<Hora> findByProfesorAndDiaAndPosicionJornada(Profesor profesor, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param aula            aula en la que se imparten las horas
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @return lista de horas de un aula concreta, un día concreto y una posición en la jornada concreta
	 */
	public List<Hora> findByAulaAndDiaAndPosicionJornada(Aula aula, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @return lista de horas de una asignatura concreta, un día concreto y una posición en la jornada concreta
	 */
	public List<Hora> findByAsignaturaAndDiaAndPosicionJornada(Asignatura asignatura, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas de un determinado
	 * curso
	 * 
	 * @param curso curso al que se imparten las horas.
	 * @return lista de horas de un curso concreto.
	 */
	public List<Hora> findByCurso(Curso curso);

	/**
	 * Busca en la base de datos y devuelve un listado de horas de un determinado
	 * profesor.
	 * 
	 * @param profesor profesor que imparte las horas.
	 * @return lista de horas de un profesor concreto.
	 */
	public List<Hora> findByProfesor(Profesor profesor);
	/**
	 * Busca en la base de datos y devuelve un listado de horas de una determinada aula.
	 * 
	 * @param aula aula en la que se imparten las horas.
	 * @return lista de horas de un aula concreta.
	 */
	public List<Hora> findByAula(Aula aula);
	/**
	 * Busca en la base de datos y devuelve un listado de horas de una determinada asignatura.
	 * 
	 * @param asignatura asignatura que se imparte en las horas.
	 * @return lista de horas de una asignatura concreta.
	 */
	public List<Hora> findByAsignatura(Asignatura asignatura);
	
	

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param curso           curso al que se imparten las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @param profesor        profesor que imparte las horas.
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param aula            aula en la que se imparten las horas
	 * @return una hora contreta en la que todos los parametros coinciden
	 */
	public Hora findByCursoAndDiaAndPosicionJornadaAndProfesorAndAsignaturaAndAula(Curso curso, String dia,
			int posicionJornada, Profesor profesor, Asignatura asignatura, Aula aula);
	//int posicionJornada, Asignatura asignatura, Curso curso, Aula aula, String dia
	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param curso           curso al que se imparten las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param aula            aula en la que se imparten las horas
	 * @return una hora contreta en la que todos los parametros coinciden
	 */
	public List<Hora> findByCursoAndDiaAndPosicionJornadaAndAsignaturaAndAula(Curso curso, String dia,
			int posicionJornada, Asignatura asignatura, Aula aula);
}

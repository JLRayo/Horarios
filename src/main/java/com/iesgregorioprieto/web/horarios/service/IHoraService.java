package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import com.iesgregorioprieto.web.horarios.entity.Asignatura;
import com.iesgregorioprieto.web.horarios.entity.Aula;
import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.entity.Profesor;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "horas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IHoraService {
	/**
	 * Devuelve una lista con todas las horas.
	 * 
	 * @return lista con todas las horas como un objeto.
	 */
	public List<Hora> retornarTodos();

	/**
	 * Guarda una hora en la base de datos.
	 * 
	 * @param hora hora que se va a guardar.
	 */
	public void guardar(Hora hora);

	/**
	 * Busca en la base de datos y devuelve un listado de horas de un determinado
	 * curso
	 * 
	 * @param curso curso al que se imparten las horas.
	 * @return lista de horas de un curso concreto
	 */
	public List<Hora> buscarHorasCurso(Curso curso);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param curso           curso al que se imparten las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * 
	 * @return lista de horas de un curso concreto, un día concreto y una posición
	 *         en la jornada concreta
	 */
	public List<Hora> buscarHorasCursoDiaPosicion(Curso curso, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param profesor        profesor que imparte las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * 
	 * @return lista de horas de un profesor concreto, un día concreto y una
	 *         posición en la jornada concreta
	 */
	public List<Hora> buscarHorasProfesorDiaPosicion(Profesor profesor, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param aula            aula en la que se imparten las horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @return lista de horas de un aula concreta, un día concreto y una posición en
	 *         la jornada concreta
	 */
	public List<Hora> buscarHorasAulaDiaPosicion(Aula aula, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @return lista de horas de una asignatura concreta, un día concreto y una
	 *         posición en la jornada concreta
	 */
	public List<Hora> buscarHorasAsignaturaDiaPosicion(Asignatura asignatura, String dia, int posicionJornada);

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param asignatura      asignatura que se imparte en esas horas.
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @param curso curso al que se imparten las horas.
	 * @param aula aula en la que se imparten las horas.
	 * @return lista de horas de una asignatura concreta, un día concreto y una
	 *         posición en la jornada concreta.
	 */
	public List<Hora> buscarCursoDiaPosicionJornadaAsignaturaAula(Curso curso, String dia,
			int posicionJornada, Asignatura asignatura, Aula aula);
	/**
	 * Elimina las horas de un determinado curso.
	 * 
	 * @param profesor profesor cuyas horas se quieren eliminar.
	 */
	public void eliminarHorasCurso(Curso curso);

	/**
	 * Elimina las horas de un determinado profesor.
	 * 
	 * @param profesor profesor cuyas horas se quieren eliminar.
	 */
	public void eliminarHorasProfesor(Profesor profesor);
	/**
	 * Elimina las horas de una determinada asigantura.
	 * 
	 * @param asignatura asignatura cuyas horas se quieren eliminar.
	 */
	public void eliminarHorasAsignatura(Asignatura asignatura);
	/**
	 * Elimina las horas de una determinada aula.
	 * 
	 * @param aula aula cuyas horas se quieren eliminar.
	 */
	public void eliminarHorasAula(Aula aula);
}

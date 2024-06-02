package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.horarios.dao.IHoraDao;
import com.iesgregorioprieto.web.horarios.entity.Asignatura;
import com.iesgregorioprieto.web.horarios.entity.Aula;
import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.entity.Profesor;

/**
 * Clase para gestionar los servicios con los datos de la tabla "horas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("HoraService")
public class HoraService implements IHoraService {
	@Autowired
	private IHoraDao horaDao;

	/**
	 * Devuelve una lista con todas las horas.
	 * 
	 * @return lista con todas las horas como un objeto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Hora> retornarTodos() {
		return (List<Hora>) horaDao.findAll();
	}

	/**
	 * Guarda una hora en la base de datos.
	 * 
	 * @param hora hora que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Hora hora) {
		horaDao.save(hora);
	}

	/**
	 * Busca en la base de datos y devuelve un listado de horas de un determinado
	 * curso
	 * 
	 * @param curso curso al que se imparten las horas.
	 * @return lista de horas de un curso concreto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Hora> buscarHorasCurso(Curso curso) {
		return horaDao.findByCurso(curso);
	}

	/**
	 * Elimina las horas de un determinado curso.
	 * 
	 * @param profesor profesor cuyas horas se quieren eliminar.
	 */
	@Override
	@Transactional
	public void eliminarHorasCurso(Curso curso) {
		horaDao.deleteAll(buscarHorasCurso(curso));
	}

	/**
	 * Elimina las horas de un determinado profesor.
	 * 
	 * @param profesor profesor cuyas horas se quieren eliminar.
	 */
	@Override
	@Transactional
	public void eliminarHorasProfesor(Profesor profesor) {
		horaDao.deleteAll(horaDao.findByProfesor(profesor));
	}
	/**
	 * Elimina las horas de una determinada asigantura.
	 * 
	 * @param asignatura asignatura cuyas horas se quieren eliminar.
	 */
	@Override
	public void eliminarHorasAsignatura(Asignatura asignatura) {
		horaDao.deleteAll(horaDao.findByAsignatura(asignatura));
		
	}
	/**
	 * Elimina las horas de una determinada aula.
	 * 
	 * @param aula aula cuyas horas se quieren eliminar.
	 */
	@Override
	public void eliminarHorasAula(Aula aula) {
		horaDao.deleteAll(horaDao.findByAula(aula));		
	}

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
	@Override
	@Transactional(readOnly = true)
	public List<Hora> buscarHorasCursoDiaPosicion(Curso curso, String dia, int posicionJornada) {
		return horaDao.findByCursoAndDiaAndPosicionJornada(curso, dia, posicionJornada);
	}

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
	@Override
	@Transactional(readOnly = true)
	public List<Hora> buscarHorasProfesorDiaPosicion(Profesor profesor, String dia, int posicionJornada) {
		return horaDao.findByProfesorAndDiaAndPosicionJornada(profesor, dia, posicionJornada);
	}

	/**
	 * Busca en la base de datos y devuelve un listado de horas cuyos campos
	 * coincidan con los parámetros introducidos.
	 * 
	 * @param aula            aula en la que se imparten las horas
	 * @param dia             día en el que se imparten las horas.
	 * @param posicionJornada posición en la jornada en la que se encuentra la hora.
	 * @return lista de horas de un aula concreta, un día concreto y una posición en
	 *         la jornada concreta
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Hora> buscarHorasAulaDiaPosicion(Aula aula, String dia, int posicionJornada) {
		return horaDao.findByAulaAndDiaAndPosicionJornada(aula, dia, posicionJornada);
	}

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
	@Override
	@Transactional(readOnly = true)
	public List<Hora> buscarHorasAsignaturaDiaPosicion(Asignatura asignatura, String dia, int posicionJornada) {
		return horaDao.findByAsignaturaAndDiaAndPosicionJornada(asignatura, dia, posicionJornada);
	}
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
	@Override
	public List<Hora> buscarCursoDiaPosicionJornadaAsignaturaAula(Curso curso, String dia, int posicionJornada,
			Asignatura asignatura, Aula aula) {
		
		return horaDao.findByCursoAndDiaAndPosicionJornadaAndAsignaturaAndAula(curso, dia, posicionJornada, asignatura, aula);
	}



}

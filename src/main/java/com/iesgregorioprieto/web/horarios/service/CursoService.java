package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.horarios.dao.ICursoDao;
import com.iesgregorioprieto.web.horarios.entity.Curso;

/**
 * Clase para gestionar los servicios con los datos de la tabla "cursos".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("CursoService")
public class CursoService implements ICursoService {
	@Autowired
	private ICursoDao cursoDao;

	/**
	 * Devuelve una lista con todos los cursos.
	 * 
	 * @return lista con todos los cursos como un objeto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Curso> retornarTodos() {
		return (List<Curso>) cursoDao.findAll();
	}

	/**
	 * Guarda un curso en la base de datos.
	 * 
	 * @param curso curso que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Curso curso) {
		cursoDao.save(curso);
	}

	/**
	 * Busca un curso a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idCurso el id del curso que se quiere buscar.
	 * @return El objeto de tipo "Curso" con los datos del curso.
	 */
	@Override
	@Transactional(readOnly = true)
	public Curso buscar(String idCurso) {
		return cursoDao.findById(idCurso).orElse(null);
	}

	/**
	 * Elimina un curso a partir de su id.
	 * 
	 * @param idAula id del curso.
	 */
	@Override
	@Transactional
	public void eliminar(String idCurso) {
		cursoDao.deleteById(idCurso);
	}

	/**
	 * Cuenta los cursos de la base de datos.
	 * 
	 * @return la cantidad de cursos.
	 */
	@Override
	public long contar() {
		return cursoDao.count();
	}

}

package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import com.iesgregorioprieto.web.horarios.entity.Curso;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "cursos".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface ICursoService {
	/**
	 * Devuelve una lista con todos los cursos.
	 * 
	 * @return lista con todos los cursos como un objeto.
	 */
	public List<Curso> retornarTodos();

	/**
	 * Guarda un curso en la base de datos.
	 * 
	 * @param curso curso que se va a guardar.
	 */
	public void guardar(Curso idCurso);

	/**
	 * Busca un curso a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idCurso el id del curso que se quiere buscar.
	 * @return El objeto de tipo "Curso" con los datos del curso.
	 */
	public Curso buscar(String idCurso);

	/**
	 * Elimina un curso a partir de su id.
	 * 
	 * @param idCurso id del curso.
	 */
	public void eliminar(String idCurso);

	/**
	 * Cuenta los cursos de la base de datos.
	 * 
	 * @return la cantidad de cursos.
	 */
	public long contar();
}

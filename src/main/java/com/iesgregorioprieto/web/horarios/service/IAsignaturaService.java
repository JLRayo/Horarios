package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import com.iesgregorioprieto.web.horarios.entity.Asignatura;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "asignaturas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IAsignaturaService {
	/**
	 * Devuelve una lista con todas las asignaturas.
	 * 
	 * @return lista con todas las asignaturas como un objeto.
	 */
	public List<Asignatura> retornarTodos();

	/**
	 * Guarda una asignatura en la base de datos.
	 * 
	 * @param asignatura asignatura que se va a guardar.
	 */
	public void guardar(Asignatura asignatura);

	/**
	 * Busca una asignatura a partir de su id en la base de datos y la devuelve como
	 * un objeto.
	 * 
	 * @param idAsignatura el id de la asignatura que se quiere buscar.
	 * @return El objeto de tipo "Asignatura" con los datos de la asignatura.
	 */
	public Asignatura buscar(String idAsignatura);

	/**
	 * Elimina una asignatura a partir de su id.
	 * 
	 * @param idAsignatura id de la asignatura.
	 */
	public void eliminar(String idAsignatura);

	/**
	 * Cuenta las asignaturas de la base de datos.
	 * 
	 * @return la cantidad de asignaturas.
	 */
	public long contar();
}

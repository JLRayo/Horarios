package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import com.iesgregorioprieto.web.horarios.entity.Profesor;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "profesores".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IProfesorService {
	/**
	 * Devuelve una lista con todos los profesores.
	 * 
	 * @return lista con todos los profesores como un objeto.
	 */
	public List<Profesor> retornarTodos();

	/**
	 * Guarda un profesor en la base de datos.
	 * 
	 * @param profesor profesor que se va a guardar.
	 */
	public void guardar(Profesor profesor);

	/**
	 * Busca un profesor a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idProfesor el id del profesor que se quiere buscar.
	 * @return El objeto de tipo "Profesor" con los datos del profesor.
	 */
	public Profesor buscar(String idProfesor);

	/**
	 * Elimina un profesor a partir de su id.
	 * 
	 * @param idProfesor id del profesor.
	 */
	public void eliminar(String idProfesor);

	/**
	 * Cuenta los profesores de la base de datos.
	 * 
	 * @return la cantidad de profesores.
	 */
	public long contar();
}

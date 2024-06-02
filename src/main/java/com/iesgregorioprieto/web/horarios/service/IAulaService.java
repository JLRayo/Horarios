package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import com.iesgregorioprieto.web.horarios.entity.Aula;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "aulas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IAulaService {
	/**
	 * Devuelve una lista con todas las aulas.
	 * 
	 * @return lista con todas las aulas como un objeto.
	 */
	public List<Aula> retornarTodos();

	/**
	 * Guarda un aula en la base de datos.
	 * 
	 * @param aula aula que se va a guardar.
	 */
	public void guardar(Aula aula);

	/**
	 * Busca un aula a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idAula el id del aula que se quiere buscar.
	 * @return El objeto de tipo "Aula" con los datos del aula.
	 */
	public Aula buscar(String nombreAula);

	/**
	 * Elimina un aula a partir de su id.
	 * 
	 * @param idAula id de la aula.
	 */
	public void eliminar(String nombreAula);

	/**
	 * Cuenta las aulas de la base de datos.
	 * 
	 * @return la cantidad de aulas.
	 */
	public long contar();
}

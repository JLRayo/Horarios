package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.horarios.dao.IAulaDao;
import com.iesgregorioprieto.web.horarios.entity.Aula;

/**
 * Clase para gestionar los servicios con los datos de la tabla "aulas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("AulaService")
public class AulaService implements IAulaService {
	@Autowired
	private IAulaDao aulaDao;

	/**
	 * Devuelve una lista con todas las aulas.
	 * 
	 * @return lista con todas las aulas como un objeto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Aula> retornarTodos() {
		return (List<Aula>) aulaDao.findAll();
	}

	/**
	 * Guarda un aula en la base de datos.
	 * 
	 * @param aula aula que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Aula aula) {
		aulaDao.save(aula);
	}

	/**
	 * Busca un aula a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idAula el id del aula que se quiere buscar.
	 * @return El objeto de tipo "Aula" con los datos del aula.
	 */
	@Override
	@Transactional(readOnly = true)
	public Aula buscar(String nombreAula) {
		return aulaDao.findById(nombreAula).orElse(null);
	}

	/**
	 * Elimina un aula a partir de su id.
	 * 
	 * @param idAula id de la aula.
	 */
	@Override
	@Transactional
	public void eliminar(String nombreAula) {
		aulaDao.deleteById(nombreAula);
	}

	/**
	 * Cuenta las aulas de la base de datos.
	 * 
	 * @return la cantidad de aulas.
	 */
	@Override
	public long contar() {
		return aulaDao.count();
	}
}

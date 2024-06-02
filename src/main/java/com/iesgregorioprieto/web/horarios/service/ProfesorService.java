package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.horarios.dao.IProfesorDao;
import com.iesgregorioprieto.web.horarios.entity.Profesor;

/**
 * Clase para gestionar los servicios con los datos de la tabla "profesores".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("ProfesorService")
public class ProfesorService implements IProfesorService {
	@Autowired
	private IProfesorDao profesorDao;

	/**
	 * Devuelve una lista con todos los profesores.
	 * 
	 * @return lista con todos los profesores como un objeto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Profesor> retornarTodos() {
		return (List<Profesor>) profesorDao.findAll();
	}

	/**
	 * Guarda un profesor en la base de datos.
	 * 
	 * @param profesor profesor que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Profesor profesor) {
		profesorDao.save(profesor);
	}

	/**
	 * Busca un profesor a partir de su id en la base de datos y la devuelve como un
	 * objeto.
	 * 
	 * @param idProfesor el id del profesor que se quiere buscar.
	 * @return El objeto de tipo "Profesor" con los datos del profesor.
	 */
	@Override
	@Transactional(readOnly = true)
	public Profesor buscar(String idProfesor) {
		return profesorDao.findById(idProfesor).orElse(null);
	}

	/**
	 * Elimina un profesor a partir de su id.
	 * 
	 * @param idAula id del profesor.
	 */
	@Override
	@Transactional
	public void eliminar(String idProfesor) {
		profesorDao.deleteById(idProfesor);
	}

	/**
	 * Cuenta los profesores de la base de datos.
	 * 
	 * @return la cantidad de profesores.
	 */
	@Override
	public long contar() {
		return profesorDao.count();
	}
}

package com.iesgregorioprieto.web.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.horarios.dao.IAsignaturaDao;
import com.iesgregorioprieto.web.horarios.entity.Asignatura;

/**
 * Clase para gestionar los servicios con los datos de la tabla "asignaturas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("AsignaturaService")
public class AsignaturaService implements IAsignaturaService {
	@Autowired
	private IAsignaturaDao asignaturaDao;

	/**
	 * Devuelve una lista con todas las asignaturas.
	 * 
	 * @return lista con todas las asignaturas como un objeto.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> retornarTodos() {
		return (List<Asignatura>) asignaturaDao.findAll();
	}

	/**
	 * Guarda una asignatura en la base de datos.
	 * 
	 * @param asignatura asignatura que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Asignatura asignatura) {
		asignaturaDao.save(asignatura);
	}

	/**
	 * Busca una asignatura a partir de su id en la base de datos y la devuelve como
	 * un objeto.
	 * 
	 * @param idAsignatura el id de la asignatura que se quiere buscar.
	 * @return El objeto de tipo "Asignatura" con los datos de la asignatura.
	 */
	@Override
	@Transactional(readOnly = true)
	public Asignatura buscar(String idAsignatura) {
		return asignaturaDao.findById(idAsignatura).orElse(null);
	}

	/**
	 * Elimina una asignatura a partir de su id.
	 * 
	 * @param idAsignatura id de la asignatura.
	 */
	@Override
	@Transactional
	public void eliminar(String idAsignatura) {
		asignaturaDao.deleteById(idAsignatura);
	}

	/**
	 * Cuenta las asignaturas de la base de datos.
	 * 
	 * @return la cantidad de asignaturas.
	 */
	@Override
	public long contar() {
		return asignaturaDao.count();
	}
}

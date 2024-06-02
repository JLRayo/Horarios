package com.iesgregorioprieto.web.horarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.horarios.entity.Asignatura;

/**
 * Interfaz para gestionar el repositorio de la tabla 'asignaturas'
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IAsignaturaDao extends CrudRepository<Asignatura, String> {
}

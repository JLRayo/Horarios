package com.iesgregorioprieto.web.horarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.horarios.entity.Profesor;
/**
 * Interfaz para gestionar el repositorio de la tabla 'profesores'
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IProfesorDao extends CrudRepository<Profesor, String>{

}

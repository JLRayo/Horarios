package com.iesgregorioprieto.web.horarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.horarios.entity.Curso;
/**
 * Interfaz para gestionar el repositorio de la tabla 'cursos'
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface ICursoDao extends CrudRepository<Curso, String>{

}

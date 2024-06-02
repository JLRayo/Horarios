package com.iesgregorioprieto.web.horarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.horarios.entity.Aula;
/**
 * Interfaz para gestionar el repositorio de la tabla 'aulas'
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IAulaDao extends CrudRepository<Aula, String>{
}

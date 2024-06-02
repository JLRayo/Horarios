package com.iesgregorioprieto.web.dao;

import org.springframework.data.repository.CrudRepository;

import com.iesgregorioprieto.web.entity.Usuario;

/**
 * Interfaz para gestionar el repositorio de la tabla 'usuarios'
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	public Usuario findByNombreUsuario(String nombreUsuario);
}

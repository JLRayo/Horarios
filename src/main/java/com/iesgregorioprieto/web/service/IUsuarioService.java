package com.iesgregorioprieto.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iesgregorioprieto.web.entity.Usuario;

/**
 * Interfaz para definir la clase que se encargará de gestionar los servicios
 * con los datos de la tabla "usuarios".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service
public interface IUsuarioService {
	/**
	 * Devuelve una lista con todos los usuarios.
	 * 
	 * @return lista con todas las usuarios como un objeto.
	 */
	public List<Usuario> retornarTodos();

	/**
	 * Guarda un usuario en la base de datos.
	 * 
	 * @param usuario usuario que se va a guardar.
	 */
	public void guardar(Usuario usuario);

	/**
	 * Busca un usuario a partir de su id en la base de datos y lo devuelve como un
	 * objeto.
	 * 
	 * @param idUsuario el id del usuario que se quiere buscar.
	 * @return El objeto de tipo "Usuario" con los datos del usuario.
	 */
	public Usuario buscarPorNombre(String idUsuario);

	/**
	 * Elimina un usuario a partir de su id.
	 * 
	 * @param idUsuario id del usuario.
	 */
	public void eliminar(Long idUsuario);

	/**
	 * Actualiza un usuario de la base de datos con los datos del usuario
	 * introducido.
	 * 
	 * @param usuario del que se extraerán los datos.
	 * @return el usuario de la base de datos con sus parámetros modificados.
	 */
	public Usuario actualizarUsuario(Usuario usuarioFormulario);
}

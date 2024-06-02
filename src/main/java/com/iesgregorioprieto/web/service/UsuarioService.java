package com.iesgregorioprieto.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.dao.IUsuarioDao;
import com.iesgregorioprieto.web.entity.Usuario;

/**
 * Clase para gestionar los servicios con los datos de la tabla "usuarios".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("UsuarioService")
public class UsuarioService implements IUsuarioService {
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	/**
	 * Devuelve una lista con todos los usuarios.
	 * 
	 * @return lista con todos los usuarios como un objeto.
	 */
	@Transactional(readOnly = true)
	public List<Usuario> retornarTodos() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	/**
	 * Guarda un usuario en la base de datos.
	 * 
	 * @param usuario usuario que se va a guardar.
	 */
	@Override
	@Transactional
	public void guardar(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	/**
	 * Busca un usuario a partir de su id en la base de datos y lo devuelve como un
	 * objeto.
	 * 
	 * @param nombreUsuario el id del usuario que se quiere buscar.
	 * @return El objeto de tipo "Usuario" con los datos del usuario.
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorNombre(String nombreUsuario) {
		return usuarioDao.findByNombreUsuario(nombreUsuario);
	}

	/**
	 * Elimina un usuario a partir de su id.
	 * 
	 * @param idUsuario id del usuario.
	 */
	@Override
	@Transactional
	public void eliminar(Long idUsuario) {
		usuarioDao.deleteById(idUsuario);
	}
	/**
	 * Actualiza un usuario de la base de datos con los datos del usuario
	 * introducido.
	 * 
	 * @param usuario del que se extraerán los datos.
	 * @return el usuario de la base de datos con sus parámetros modificados.
	 */
	@Override
	@Transactional
	public Usuario actualizarUsuario(Usuario usuarioFormulario) {
		Usuario usuarioEnDb = usuarioDao.findById(usuarioFormulario.getId()).orElse(null);
		if (!usuarioFormulario.getContrasena().isEmpty()) {
			usuarioEnDb.setContrasena(usuarioFormulario.getContrasena());
		}
		usuarioEnDb.setNombreUsuario(usuarioFormulario.getNombreUsuario());
		return usuarioDao.save(usuarioEnDb);
	}

}

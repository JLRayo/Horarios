package com.iesgregorioprieto.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesgregorioprieto.web.entity.Usuario;

/**
 * Clase para gestionar los usuarios con JPA.
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Service("jpaUserDetailsService")
public class JPAUserDetalisService implements UserDetailsService {
	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;

	/**
	 * Crea los usuarios con sus detalles a partir de la base de datos para la
	 * autentificación
	 * 
	 * @param nombreUsuario el nombre del usuario que queremos registrar
	 * 
	 * @return Un objeto con los detalles del usuario que implementa la interfaz
	 *         UserDetalis, si no encuentra el cliente especificado lanza una excepción
	 *         UsernameNotFoundException
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario;
		usuario = usuarioService.buscarPorNombre(nombreUsuario);
		if (usuario != null) {
			return new JPAUserDetails(usuario);
		}
		throw new UsernameNotFoundException("No existe el usuario " + nombreUsuario);
	}
}

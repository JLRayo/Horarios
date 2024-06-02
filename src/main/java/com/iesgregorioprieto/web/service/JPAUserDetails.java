package com.iesgregorioprieto.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iesgregorioprieto.web.entity.Role;
import com.iesgregorioprieto.web.entity.Usuario;

/**
 * Clase para gestionar los detalles de los usuarios con JPA.
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public class JPAUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	/**
	 * Constructor.
	 * 
	 * @param usuario el usuario cuyos detalles se desean registrar.
	 */
	public JPAUserDetails(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Extrae los roles del usuario que se pasó en el constructor.
	 * 
	 * @return lista con los roles del usuario.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return authorities;
	}

	/**
	 * Obtiene y devuelve la contraseña del usuario.
	 * 
	 * @return la contraseña del usuario.
	 */
	@Override
	public String getPassword() {
		return usuario.getContrasena();
	}

	/**
	 * Devuelve el nombre de usuario.
	 * 
	 * @return el nombre del usuario.
	 */
	@Override
	public String getUsername() {
		return usuario.getNombreUsuario();
	}

	/**
	 * Indica si la cuenta no ha expirado.
	 * 
	 * @return true.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indica si la cuenta no está bloqueada.
	 * 
	 * @return true.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indica si las credenciales de la cuenta no han expirado.
	 * 
	 * @return true.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indica si la cuenta está habilitada.
	 * 
	 * @return true si lo está, falso en caso contrario.
	 */
	@Override
	public boolean isEnabled() {
		return usuario.getEnabled();
	}

	/**
	 * Establece el nombre del usuario.
	 * 
	 * @param nombreUsuario el nuevo nombre para el usuario.
	 */
	public void setUsername(String nombreUsuario) {
		this.usuario.setNombreUsuario(nombreUsuario);
	}

	/**
	 * Establece la contraseña del usuario.
	 * 
	 * @param contrasena la nueva contraseña para el usuario.
	 */
	public void setPassword(String contrasena) {
		this.usuario.setContrasena(contrasena);
	}
}

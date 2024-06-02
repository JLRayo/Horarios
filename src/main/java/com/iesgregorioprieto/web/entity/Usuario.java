package com.iesgregorioprieto.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import javax.persistence.*;

/**
 * Clase para trabajar con las entidades de la tabla "usuarios"
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
/**
 * @author lordc
 *
 */
@Entity
@Table(name = "users")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", unique = true)
	private String nombreUsuario;
	@Column(name = "password", nullable = false)
	private String contrasena;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "ultima_sesion")
	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date ultimaSesion;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private List<Role> roles;

	/**
	 * Constructor de Usuario sin parametros
	 * 
	 */
	public Usuario() {
	}

	/**
	 * Devuelve un booleano que indica si la cuenta está activada
	 * 
	 * @return true si está activada, false si está desactivada
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * Establece si la cuenta está activada o no.
	 * 
	 * @param enabled true para activar la cuenta, false para desactivarla
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Devuelve una cadena de caracteres con el nombre del usuario
	 * 
	 * @return el nombre del usuario.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Establece el nombre del usuario
	 * 
	 * @param nombreUsuario el nuevo nombre del usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Devuelve una cadena de caracteres con la contraseña del usuario
	 * 
	 * @return el nombre del usuario.
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña del usuario
	 * 
	 * @param contrasena la nueva contraseña del usuario
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Devuelve un objeto tipo Date con la fecha en la que el usuario cerró sesión
	 * por última vez.
	 * 
	 * @return Date con la fecha en la que el usuario inició sesión por última vez.
	 */

	public Date getUltimaSesion() {
		return ultimaSesion;
	}

	/**
	 * Establece la fecha en la que el usuario cerró sesión por última vez.
	 * 
	 * @param ultimaSesion la fecha en la que el usuario cerró sesión por última
	 *                     vez.
	 */
	public void setUltimaSesion(Date ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

package com.iesgregorioprieto.web.horarios.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Clase para trabajar con las entidades de la tabla "asignaturas".
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private String idAsignatura;
	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "asignatura")
	//@JsonManagedReference
	private List<Hora> horas;

	/**
	 * Constructor sin parametros de Asignatura.
	 */
	public Asignatura() {
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de Asignatura.
	 * 
	 * @param idAsignatura id de la asignatura.
	 */
	public Asignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de Asignatura.
	 * 
	 * @param idAsignatura id de la asignatura.
	 * @param nombre       nombre completo de la asignatura.
	 */
	public Asignatura(String idAsignatura, String nombre) {
		this.idAsignatura = idAsignatura;
		this.nombre = nombre;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Devuelve una cadena de caracteres con el id de la asignatura.
	 * 
	 * @return el id de la asignatura.
	 */
	public String getIdAsignatura() {
		return idAsignatura;
	}

	/**
	 * Establece el id de la asignatura.
	 * 
	 * @param idAsignatura el nuevo id de la asignatura.
	 */
	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	/**
	 * Devuelve una cadena de caracteres con el nombre de la asignatura.
	 * 
	 * @return el nombre de la asignatura.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la asignatura.
	 * 
	 * @param nombre el nuevo nombre de la asignatura.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuele una lista de las horas de la asignatura.
	 * 
	 * @return lista de horas de la asignatura.
	 */
	public List<Hora> getHoras() {
		return horas;
	}

	/**
	 * Establece la lista de horas de la asignatura.
	 * 
	 * @param horas la nueva lista de horas de la asignatura.
	 */
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	/**
	 * Añade una hora a la lista de horas de la asignatura.
	 * 
	 * @param hora la hora que se añadirá.
	 */
	public void addHora(Hora hora) {
		this.horas.add(hora);
	}
}

package com.iesgregorioprieto.web.horarios.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Clase para trabajar con las entidades de la tabla "profesores"
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Entity
@Table(name = "profesores")
public class Profesor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String idProfesor;

	@Column(name = "nombre")
	private String nombreProfesor;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor", cascade = CascadeType.REMOVE)
	//@JsonManagedReference
	private List<Hora> horas;

	/**
	 * Constructor de profesor sin parámetros
	 */
	public Profesor() {
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de profesor
	 * 
	 * @param idProfesor id del profesor
	 */
	public Profesor(String idProfesor) {
		this.idProfesor = idProfesor;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de profesor
	 * 
	 * @param idProfesor     id del profesor
	 * @param nombreProfesor nombre del profesor
	 */
	public Profesor(String idProfesor, String nombreProfesor) {
		this.idProfesor = idProfesor;
		this.nombreProfesor = nombreProfesor;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Devuelve una cadena de caracteres con el id del profesor.
	 * 
	 * @return el id del profesor.
	 */
	public String getIdProfesor() {
		return idProfesor;
	}

	/**
	 * Establece el id del profesor.
	 * 
	 * @param idProfesor el nuevo id del profesor.
	 */
	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	/**
	 * Devuelve una cadena de caracteres con el nombre completo del profesor.
	 * 
	 * @return nombre completo del profesor.
	 */
	public String getNombreProfesor() {
		return nombreProfesor;
	}

	/**
	 * Establece el nombre completo profesor.
	 * 
	 * @param nombreProfesor el nuevo nombre del profesor.
	 */
	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	/**
	 * Devuele una lista de las horas del profesor.
	 * 
	 * @return lista de horas del profesor.
	 */
	public List<Hora> getHoras() {
		return horas;
	}

	/**
	 * Establece la lista de horas del profesor.
	 * 
	 * @param horas la nueva lista de horas del profesor.
	 */
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	/**
	 * Añade una hora a la lista de horas del profesor.
	 * 
	 * @param hora la hora que se añadirá.
	 */
	public void addHora(Hora hora) {
		this.horas.add(hora);
	}
}

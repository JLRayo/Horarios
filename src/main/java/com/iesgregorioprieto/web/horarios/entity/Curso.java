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
 * Clase para trabajar con las entidades de la tabla "cursos"
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private String idCurso;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
	//@JsonManagedReference
	private List<Hora> horas;

	/**
	 * Constructor de Curso sin parámetros.
	 */
	public Curso() {
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de Curso.
	 * 
	 * @param idCurso id del curso.
	 */
	public Curso(String idCurso) {
		this.idCurso = idCurso;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de curso.
	 * 
	 * @param idCurso id del curso.
	 * @param nombre  nombre completo del curso.
	 */
	public Curso(String idCurso, String nombre) {
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Devuelve una cadena de caracteres con el id del curso.
	 * 
	 * @return el id del curso.
	 */
	public String getIdCurso() {
		return idCurso;
	}

	/**
	 * Establece el id del curso.
	 * 
	 * @param idAsignatura el nuevo id del curso.
	 */
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	/**
	 * Devuelve una cadena de caracteres con el nombre completo del curso.
	 * 
	 * @return nombre completo del curso.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre completo del curso.
	 * 
	 * @param nombre el nuevo nombre del curso.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuele una lista de las horas del curso.
	 * 
	 * @return lista de horas del curso.
	 */

	public List<Hora> getHoras() {
		return horas;
	}

	/**
	 * Establece la lista de horas del curso.
	 * 
	 * @param horas la nueva lista de horas del curso.
	 */
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	/**
	 * Añade una hora a la lista de horas del curso.
	 * 
	 * @param hora la hora que se añadirá.
	 */
	public void addHora(Hora hora) {
		this.horas.add(hora);
	}
}

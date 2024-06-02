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
 * Clase para trabajar con las entidades de la tabla "aulas"
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Entity
@Table(name = "aulas")
public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String idAula;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aula")
	//@JsonManagedReference
	private List<Hora> horas;

	/**
	 * Constructor sin parámetros de Aula
	 */
	public Aula() {
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Constructor de Aula
	 * 
	 * @param idAula id del aula
	 */
	public Aula(String idAula) {
		this.idAula = idAula;
		this.horas = new ArrayList<Hora>();
	}

	/**
	 * Devuelve una cadena de caracteres con el id del aula.
	 * 
	 * @return el id de la aula.
	 */
	public String getIdAula() {
		return idAula;
	}

	/**
	 * Establece el id del aula.
	 * 
	 * @param idAula el nuevo id del aula.
	 */
	public void setIdAula(String idAula) {
		this.idAula = idAula;
	}

	/**
	 * Devuele una lista de las horas del aula.
	 * 
	 * @return lista de horas del aula.
	 */
	public List<Hora> getHoras() {
		return horas;
	}

	/**
	 * Establece la lista de horas del aula.
	 * 
	 * @param horas la nueva lista de horas del aula.
	 */
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	/**
	 * Añade una hora a la lista de horas del aula.
	 * 
	 * @param hora la hora que se añadirá.
	 */
	public void addHora(Hora hora) {
		this.horas.add(hora);
	}
}

package com.iesgregorioprieto.web.horarios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Clase para trabajar con las entidades de la tabla "horas"
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Entity
@Table(name = "horas")
public class Hora implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	/* @GeneratedValue(strategy = GenerationType.SEQUENCE) para postgre */
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "posicion_jornada")
	private int posicionJornada;

	@Column(name = "dia")
	private String dia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asignatura")
	//@JsonBackReference
	private Asignatura asignatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso")
	//@JsonBackReference
	private Curso curso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aula")
	//@JsonBackReference
	private Aula aula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesor")
	//@JsonBackReference
	private Profesor profesor;

	/**
	 * Constructor sin parámetros de Hora.
	 */
	public Hora() {

	}

	/**
	 * Constructor de Hora.
	 *
	 * @param posicionJornada posicion en la jornada de la hora.
	 * @param dia             día en el que se imparte la hora.
	 * @param asignatura      asignatura que se imparte en la hora.
	 * @param curso           curso al que se imparte la hora.
	 * @param aula            aula en la que se imparte la hora.
	 * @param profesor        profesor que imparte en esa hora.
	 */
	public Hora(int posicionJornada, String dia, Asignatura asignatura, Curso curso, Aula aula, Profesor profesor) {
		this.posicionJornada = posicionJornada;
		this.dia = dia;
		this.asignatura = asignatura;
		this.curso = curso;
		this.aula = aula;
		this.profesor = profesor;

	}

	/**
	 * Devuelve un número, el id de la hora.
	 * 
	 * @return el id de la hora.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el id de la hora.
	 * 
	 * @param id el id de la hora.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve un número, la posición en la jornada de la hora.
	 * 
	 * @return posición en la jornada en la que se imparte la hora.
	 */
	public int getPosicionJornada() {
		return posicionJornada;
	}

	/**
	 * Establece la posición en la jornada en la que trasncurre la hora.
	 * 
	 * @param posicionJornada posición en la jornada en la que se imparte la hora.
	 */
	public void setPosicionJornada(int posicionJornada) {
		this.posicionJornada = posicionJornada;
	}

	/**
	 * Devuelve una cadena de caracteres que indica el día en el que transcurre la
	 * hora.
	 * 
	 * @return el día en el que trascurre la hora.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Establece el día en el que trasncurre la hora.
	 * 
	 * @param dia día en el que transcurre la hora.
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Devuelve la asignatura en la que se imparte la hora.
	 * 
	 * @return la asignatura que se imparte durante la hora.
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}

	/**
	 * Establece la asignatura que se imparte en la hora.
	 * 
	 * @param asignatura asignatura que se imparte durante la hora.
	 */
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	/**
	 * Devuelve el curso al que se instruye en la hora.
	 * 
	 * @return el curso al que se instruye en la hora.
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * Establece el curso al que se instruye durante la hora.
	 * 
	 * @param curso curso al que se instruye durante la hora
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * Devuelve el aula en la que se imparte la hora.
	 * 
	 * @return el aula en la que se imparte la hora.
	 */
	public Aula getAula() {
		return aula;
	}

	/**
	 * Establece el aula donde se imparte la hora.
	 * 
	 * @param aula aula donde se imparte la hora.
	 */
	public void setAula(Aula aula) {
		this.aula = aula;
	}

	/**
	 * Devuelve el profesor que imparte clase durante la hora.
	 * 
	 * @return el profesor que imparte clase la hora.
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * Establece el profesor que imparte clase durante la hora.
	 * 
	 * @param profesor profesor que imparte clase durante la hora.
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
}

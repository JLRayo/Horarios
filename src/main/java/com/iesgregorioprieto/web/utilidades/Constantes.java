package com.iesgregorioprieto.web.utilidades;

/**
 * Clase para almacenar las constantes que se utilizarán en la aplicación.
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public class Constantes {
	/**
	 * Array estático que contiene cadenas de caracteres con los días de la semana,
	 * ordenados
	 */
	public static final String[] DIAS_SEMANA = new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
	/**
	 * Líneas del fichero excel de los alumnos que hay que saltarse (Recreo, medio
	 * día, etc...)
	 */
	public static final int[] LINEA_SALTAR_ALUMNOS = new int[] { 0, 1, 4, 7, 11, 15 };
	/**
	 * Total de horas que tiene una jornada desde la mañana hasta la noche
	 */
	public static final int TOTAL_HORAS_JORNADA = 13;
}

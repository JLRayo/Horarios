package com.iesgregorioprieto.web.horarios.utilidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.iesgregorioprieto.web.horarios.entity.Asignatura;
import com.iesgregorioprieto.web.horarios.entity.Aula;
import com.iesgregorioprieto.web.horarios.entity.Curso;
import com.iesgregorioprieto.web.horarios.entity.Hora;
import com.iesgregorioprieto.web.horarios.entity.Profesor;
import com.iesgregorioprieto.web.horarios.service.IAsignaturaService;
import com.iesgregorioprieto.web.horarios.service.IAulaService;
import com.iesgregorioprieto.web.horarios.service.ICursoService;
import com.iesgregorioprieto.web.horarios.service.IHoraService;
import com.iesgregorioprieto.web.horarios.service.IProfesorService;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.utilidades.Constantes;

/**
 * Clase para procesar los ficheros excel y almacenar la información extraida en
 * la base de datos.
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
public class LeerFicheroHorarios {

	/**
	 * Extrae los datos del fichero excel de los alumnos y los guarda en la base de
	 * datos
	 * 
	 * @param excelAlumnos      es un array de los ficheros
	 * 
	 * @param horaService       es el servicio de las horas
	 * 
	 * @param cursoService      servicio de los cursos
	 * 
	 * @param aulaService       servicio de las aulas
	 * 
	 * @param profesorService   servicio de los profesores
	 * 
	 * @param asignaturaService servicio de las asignaturas
	 * 
	 * @param usuarioService    servicio del usuario
	 * 
	 * @param model             modelo al que se enviará el nombre de los ficheros
	 *                          con los que ha existido un problema
	 * @param authentication    autenticación del usuario registrado
	 * 
	 * 
	 * @return True si el fichero se ha procesado correctamente y false si ha
	 *         ocurrido algún error o se ha lanzado una excepción.
	 */
	public static Boolean ficheroAlumnado(MultipartFile[] excelAlumnos, IHoraService horaService,
			ICursoService cursoService, IAulaService aulaService, IProfesorService profesorService,
			IAsignaturaService asignaturaService, IUsuarioService usuarioService, Model model,
			Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("usuario", usuarioService.buscarPorNombre(authentication.getName()));
		}
		model.addAttribute("alumnos", true);
		model.addAttribute("titulo", "Cargar horarios alumnado");
		/*
		 * Workbook workbook; try { workbook =
		 * WorkbookFactory.create(reapExcelDataFile.getInputStream()); //Hoja Sheet
		 * sheet = workbook.getSheetAt(0);
		 * 
		 * Row row = sheet.getRow(0);
		 * 
		 * Cell cell = row.getCell(0);
		 * 
		 * 
		 * DataFormatter dataFormatter = new DataFormatter(); ; return true;
		 * 
		 * } catch (EncryptedDocumentException | IOException e) { return false; }
		 */
		String nombreFichero = "";
		String lineaFichero = "";
		String celdaFichero = "";

		try {
			for (MultipartFile fichero : excelAlumnos) {
				nombreFichero = fichero.getOriginalFilename();
				Workbook workbook;// Fichero sobre el que se trabaja

				Sheet sheet;// Página del fichero
				Iterator<Sheet> sheetIterator;// Iterador de páginas

				Iterator<Row> rowIterator;// Iterador de las filas

				Iterator<Cell> cellIterator;// Iterador de las celdas
				Cell cell;// Celda

				DataFormatter dataFormatter;// Para dar formato a las celdas
				String contenidoCelda;

				boolean saltar;// Hay líneas como la del recreo, que no hace falta leerlas.

				StringTokenizer tokenizer;// Para extraer los tokens de cada celda (profesores y asignaturas)
				String token;
				StringTokenizer tokenizerAula;// Para extraer el aula
				String idAula;

				// Objetos que se guardarán en la base de datos
				Curso curso;
				Aula aula;
				Hora hora;
				ArrayList<Profesor> profesores;
				ArrayList<Asignatura> asignaturas;

				workbook = WorkbookFactory.create(fichero.getInputStream());

				// Recorre las hojas
				sheetIterator = workbook.sheetIterator();
				// Se da formato a los datos
				dataFormatter = new DataFormatter();

				while (sheetIterator.hasNext()) {
					sheet = sheetIterator.next();

					// La primera celda es el curso
					curso =cursoService.buscar(celdaFichero);
					if(curso == null) {
						curso = new Curso(dataFormatter.formatCellValue(sheet.getRow(0).getCell(0)).trim());
						cursoService.guardar(curso);
					}
					
					// Si el curso ya existe, se borran sus horas
					horaService.eliminarHorasCurso(curso);
					// Recorre las filas
					rowIterator = sheet.rowIterator();
					while (rowIterator.hasNext()) {

						// Fila del fichero
						Row row;// Si no se declara aquí, da error en el booleano "saltar"
						row = rowIterator.next();
						lineaFichero = "" + (row.getRowNum() + 1);
						cellIterator = row.cellIterator();

						// Recorre las celdas
						while (cellIterator.hasNext()) {
							cell = cellIterator.next();
							celdaFichero = "" + (cell.getColumnIndex() + 1);
							// Si la linea es de las que no se requiere información, se salta
							saltar = Arrays.stream(Constantes.LINEA_SALTAR_ALUMNOS).anyMatch(i -> i == row.getRowNum());

							/*
							 * Si no es la celda donde están marcadas las horas, una de las líneas donde se
							 * indica "Recreo" o "Medio día" o la celda no está vacía, pasará
							 */
							if (cell.getColumnIndex() != 0 && !saltar && !celdaVacia(cell)) {
								contenidoCelda = dataFormatter.formatCellValue(cell).trim();
								/*
								 * Como puede haber varios profesores y varias asignaturas en un mismo aula a la
								 * vez, Se guardan en listas y luego, se guardan las distintas combinaciones en
								 * distintas horas
								 */
								profesores = new ArrayList<Profesor>();
								asignaturas = new ArrayList<Asignatura>();

								tokenizer = new StringTokenizer(contenidoCelda, "\n");

								/*
								 * Se recorre cada celda usando como token los saltos de línea ya que estos
								 * separan la información sobre las distintas entidades.
								 */
								while (tokenizer.hasMoreTokens()) {

									token = tokenizer.nextToken();
									// Si contiene "-" y números tras este o es una tutoría, será una asignatura
									if (esAsignatura(token) || token.contains("Es de tutorí")) {
										if (token.contains("Es de tutorí")) {
											asignaturas.add(new Asignatura("Tutoría"));
										} else {
											
											if(asignaturaService.buscar(token) != null) {
												asignaturas.add(asignaturaService.buscar(token));
											}else {
												asignaturas.add(new Asignatura(token));
											}
										}
										// Si contiene "," o "." será el nombre de un profesor
									} else if (token.contains(",") || token.contains(".")) {
										
										if(profesorService.buscar(token) != null) {
											profesores.add(profesorService.buscar(token));
										}else {
											profesores.add(new Profesor(token));
										}
										/*
										 * Si contiene "(" será un aula. Las horas marcan el final del proceso de
										 * extracción de entidades, por lo que se guardarán los profesores y asignaturas
										 * junto con el aula
										 */
									} else if (token.contains("(")) {

										idAula = "";
										if (token.contains("NP")) {
											idAula = "NP";
										} else {
											tokenizerAula = new StringTokenizer(token, " ");
											tokenizerAula.nextToken();// Se salta el primer token
											idAula = tokenizerAula.nextToken();
										}

										// Se recorren los profesores extraidos
										for (Profesor profesor : profesores) {
											// Se recorren las asignaturas extraidas
											for (Asignatura asignatura : asignaturas) {
												asignaturaService.guardar(asignatura);
												profesorService.guardar(profesor);
												aula = new Aula(idAula);
												aulaService.guardar(aula);

												hora = new Hora();
												hora.setAsignatura(asignatura);
												hora.setProfesor(profesor);
												hora.setAula(aula);
												hora.setCurso(curso);

												/*
												 * Según las "líneas inútiles" que se han saltado, su posición en la
												 * jornada será la línea del fichero en a que se encuentran menos la
												 * cantidad de líneas que no interesan
												 */
												if (row.getRowNum() < 4) {
													hora.setPosicionJornada(row.getRowNum() - 1);
												} else if (row.getRowNum() < 7) {
													hora.setPosicionJornada(row.getRowNum() - 2);
												} else if (row.getRowNum() < 11) {
													hora.setPosicionJornada(row.getRowNum() - 3);
												} else if (row.getRowNum() < 15) {
													hora.setPosicionJornada(row.getRowNum() - 4);
												} else if (row.getRowNum() < 19) {
													hora.setPosicionJornada(row.getRowNum() - 5);
												}

												/*
												 * El día de la semana al que pertenecen es la columna en la que se
												 * encuentra la celda menos 1
												 */
												hora.setDia(Constantes.DIAS_SEMANA[cell.getColumnIndex() - 1]);

												// Se guarda la hora
												horaService.guardar(hora);
											}
										}
										/*
										 * Tras guardar la información de esa celda se vacían los profesores y las
										 * asignaturas para empezar de nuevo
										 */
										profesores.clear();
										asignaturas.clear();
									} else {
										model.addAttribute("errores", "Error en el fichero: " + nombreFichero
												+ "<br />Línea " + lineaFichero + "<br />Columna " + celdaFichero
												+ "<br/>No se puede interpretar correctamente la estructura de los datos: "
												+ token);
										System.out.println(token);
										return false;
									}
								}
							}
						}
					}
				}
			}
			model.addAttribute("correctos", "Todos los horarios se han registrado correctamente.");
			return true;
		} catch (EncryptedDocumentException | IOException | NoSuchElementException e) {
			model.addAttribute("errores", "Error en el fichero: " + nombreFichero + "<br />Línea " + lineaFichero
					+ "<br />Columna " + celdaFichero);
			return false;
		}
	}

	/**
	 * Extrae los datos del fichero excel de los profesores y los guarda en la base
	 * de datos
	 * 
	 * @param excelProfesores   es un array de los ficheros
	 * 
	 * @param horaService       es el servicio de las horas
	 * 
	 * @param cursoService      servicio de los cursos
	 * 
	 * @param aulaService       servicio de las aulas
	 * 
	 * @param profesorService   servicio de los profesores
	 * 
	 * @param asignaturaService servicio de las asignaturas
	 * 
	 * @param usuarioService    servicio del usuario
	 * 
	 * @param model             modelo al que se enviará el nombre de los ficheros
	 *                          con los que ha existido un problema
	 * 
	 * @param authentication    autenticación del usuario registrado
	 * 
	 * @return True si el fichero se ha procesado correctamente y false si ha
	 *         ocurrido algún error o se ha lanzado una excepción.
	 */

	public static Boolean ficheroProfesorado(MultipartFile[] excelProfesores, IHoraService horaService,
			ICursoService cursoService, IAulaService aulaService, IProfesorService profesorService,
			IAsignaturaService asignaturaService, IUsuarioService usuarioService, Model model,
			Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("usuario", usuarioService.buscarPorNombre(authentication.getName()));
		}
		model.addAttribute("alumnos", false);
		model.addAttribute("titulo", "Cargar horarios profesorado");
		String nombreFichero = "";
		try {
			for (MultipartFile fichero : excelProfesores) {
				nombreFichero = fichero.getOriginalFilename();
				Workbook workbook;// Fichero sobre el que se trabaja

				Sheet sheet;// Página del fichero
				Iterator<Sheet> sheetIterator;// Iterador de páginas

				DataFormatter dataFormatter;// Para dar formato a las celdas

				// Objetos que se guardarán en la base de datos
				Profesor profesor;

				String nombreCompletoProfesor;
				String idProfesor;

				workbook = WorkbookFactory.create(fichero.getInputStream());

				// Recorre las hojas
				sheetIterator = workbook.sheetIterator();
				// Se da formato a los datos
				dataFormatter = new DataFormatter();

				while (sheetIterator.hasNext()) {
					sheet = sheetIterator.next();

					// La primera celda es el profesor

					StringTokenizer tokenizerNombreProfesor = new StringTokenizer(
							dataFormatter.formatCellValue(sheet.getRow(0).getCell(0)).trim(), "(");
					nombreCompletoProfesor = tokenizerNombreProfesor.nextToken().trim();
					idProfesor = tokenizerNombreProfesor.nextToken().replace(")", "").trim();
					profesor = new Profesor(idProfesor, nombreCompletoProfesor);
					profesorService.guardar(profesor);
				}
			}
			model.addAttribute("correctos", "Todos los horarios se han registrado correctamente.");
			return true;
		} catch (EncryptedDocumentException | IOException | NoSuchElementException e) {
			model.addAttribute("errores", "Error en el fichero: "
					+ nombreFichero); /*
										 * "<br />Línea " + lineaFichero + "<br />Columna " + celdaFichero);
										 */
			return false;
		}

	}

	/**
	 * Comprueba si una celda está vacía
	 * 
	 * @param cell es la celda que hay que comprobar
	 * @return True si está vacía, false en caso contrario
	 */
	private static boolean celdaVacia(Cell cell) {
		DataFormatter dataFormatter = new DataFormatter();
		String cellValue = dataFormatter.formatCellValue(cell).trim();
		cellValue = cellValue.replace("\n", "").replace("\r", "").trim();
		if (cell != null && cell.getCellType() != CellType.BLANK && cellValue.length() > 0) {
			return false;
		}
		return true;
	}

	private static boolean esAsignatura(String cadena) {
		StringTokenizer tokenizer;
		@SuppressWarnings("unused")
		int numero;
		if (cadena.contains("-")) {
			tokenizer = new StringTokenizer(cadena, "-");
			/*
			 * Algunas asignaturas tienen dos '-'. Se cuentan los tokens y si hay más de
			 * dos, significa que es uno de estos casos
			 */
			if (tokenizer.countTokens() > 2) {
				tokenizer.nextToken();
			}
			tokenizer.nextToken();
			try {
				numero = Integer.parseInt(tokenizer.nextToken());
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}
}

package com.iesgregorioprieto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 * Clase que ejecuta la aplicación web backend de las aplicaciones del IES
 * Gregorio Prieto de Valdepeñas
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@SpringBootApplication
public class BackendAppsIesGregorioPrietoApplication implements CommandLineRunner{
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BackendAppsIesGregorioPrietoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
	}

}

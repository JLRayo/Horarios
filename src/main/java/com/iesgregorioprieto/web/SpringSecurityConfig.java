package com.iesgregorioprieto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.iesgregorioprieto.web.service.JPAUserDetalisService;

/**
 * Clase de configuración de la seguridad. En ella se define la autenticación
 *
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Bean con el servicio de los detalles del usuario.
	 * 
	 * @return objeto que implementa la interfaz UserDetailsService.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new JPAUserDetalisService();
	}

	/**
	 * Bean que devuelve el proveedor de autenticación, en el cual se especifica el
	 * servicio de detalles de usuario y el encriptador de contraseñas.
	 * 
	 * @returnel proveedor de autenticación.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}

	/**
	 * Bean que devuelve el encriptador de contraseñas.
	 * 
	 * @return el encriptador de contraseñas.
	 */
	@Bean // (name ="passwordEncoder")
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {

		/*
		 * builder.userDetailsService(userDetailsService()).init(builder);
		 * builder.userDetailsService(userDetailsService()).passwordEncoder(
		 * passwordEncoder());
		 */
		builder.authenticationProvider(authenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**", "/login" , "/rest/**" ).permitAll()
				/* .antMatchers("/login").hasAnyRole("ADMIN") */.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/inicio", true).permitAll().and()
				.logout().permitAll();

	}
}

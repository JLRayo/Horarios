package com.iesgregorioprieto.web.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesgregorioprieto.web.entity.Usuario;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.utilidades.Variables;

/**
 * Clase para controlar la página de login de la aplicación e interactuar con
 * las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Controller
public class LoginController {
	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;

	@GetMapping({ "/login" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya has iniciado sesión");
			return "redirect:/inicio";
		}
		if (logout != null) {
			model.addAttribute("cerrarSesion", true);
			SimpleDateFormat format;
			String dateString;
			Date date;
			try {
				format = new SimpleDateFormat("dd-MM-yyyy ' a las ' HH:mm:ss");
				dateString = format.format(new Date());
				date = format.parse(dateString);
				Usuario usuario = usuarioService.buscarPorNombre(Variables.idUsuarioRegistrado);
				if(usuario != null) {
					usuario.setUltimaSesion(date);
					usuarioService.guardar(usuario);
				}
			} catch (ParseException e) {
			}
		}
		if (error != null) {
			model.addAttribute("error", true);
			return "login";
		}

		return "login";
	}

}

package com.iesgregorioprieto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iesgregorioprieto.web.entity.Usuario;
import com.iesgregorioprieto.web.service.IUsuarioService;
import com.iesgregorioprieto.web.service.JPAUserDetails;
import com.iesgregorioprieto.web.service.JPAUserDetalisService;
import com.iesgregorioprieto.web.utilidades.Variables;

/**
 * Clase para controlar la página de usuarios de la aplicación e interactuar con
 * las plantilas html relacionadas con estos procesos.
 * 
 * @author José Luis Sánchez-Pastor Rayo
 * @since 2022
 */
@Controller
public class UsuarioController {

	@Autowired
	@Qualifier("UsuarioService")
	private IUsuarioService usuarioService;
	@Autowired
	JPAUserDetalisService userDetailsService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/usuario")
	public String usuario(Model model) {
		model.addAttribute("titulo", "Detalles de usuario");
		return "detalles_usuario";
	}
	/*
	 * @Autowired private ApplicationContext appContext;
	 */

	@PostMapping("/usuario/guardar")
	// @ResponseStatus(code = HttpStatus.CREATED)
	public String guardarUsuario(Usuario usuarioFormulario, Model model,
			@AuthenticationPrincipal JPAUserDetails usuarioRegistrado) throws Exception {
		
		if(!usuarioFormulario.getContrasena().isEmpty()) {
			usuarioFormulario.setContrasena(passwordEncoder.encode(usuarioFormulario.getContrasena()));
		}		
		Usuario usuarioguardado = usuarioService.actualizarUsuario(usuarioFormulario);
		usuarioRegistrado.setUsername(usuarioguardado.getNombreUsuario());
		usuarioRegistrado.setPassword(usuarioguardado.getContrasena());
		Variables.idUsuarioRegistrado = usuarioguardado.getNombreUsuario();
		model.addAttribute("cambios", true);
		model.addAttribute("titulo", "Detalles de usuario");
		return "detalles_usuario";
	}

	@ModelAttribute("usuarioRegistrado")
	public Usuario usuarioRegistrado(Authentication authentication) {
		if (authentication != null) {
			Variables.idUsuarioRegistrado = authentication.getName();
			return usuarioService.buscarPorNombre(authentication.getName());
		} else {
			return null;
		}
	}
}

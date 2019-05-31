package com.respondeaqui.controlador;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.respondeaqui.dao.FormularioDao;
import com.respondeaqui.modelo.Formulario;

@Controller
public class FormularioControlador {
	
	@Autowired
	private FormularioDao formularioDao;

	@GetMapping("/criarformulario")
	public String criarFormulario(Model model) {

		model.addAttribute("formulario", new Formulario());
		return "newForm";
	}
	
	@PostMapping("/criarformulario")
    public String criarFormulario(Model model, @Valid Formulario formulario, BindingResult bindingResult) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuarioAtual = authentication.getName();
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "newForm";
		}
		
		formulario.setId_usuario(usuarioAtual);
		formulario.setDt_criacao(new Date());
		
		formularioDao.criarFormulario(formulario);
		model.addAttribute("formulario", new Formulario());
		return "newForm";
	}
}

package com.respondeaqui.controlador;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/meusformularios")
	public String exibirMeuFormulario(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuarioAtual = authentication.getName();
		
		List<Formulario> formularios = formularioDao.findByMatricula(usuarioAtual);
		
		model.addAttribute("formularios", formularios);
		return "myForms";
	}
	
	@RequestMapping(value = "/meusformularios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int apagarFormulario(@RequestParam("id") int id) {
		return formularioDao.removerFormulario(id);
	}
	
	/*@ResponseBody @GetMapping(value="meusformularios/apagar")
	public String apagarFormulario(@RequestBody String id){
		
			System.out.println(id);
			return "myForms";
	}*/
}

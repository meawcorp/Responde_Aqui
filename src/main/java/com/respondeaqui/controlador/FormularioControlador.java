package com.respondeaqui.controlador;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.respondeaqui.dao.FormularioDao;
import com.respondeaqui.modelo.Formulario;
import com.respondeaqui.servico.UsuarioServico;

@Controller
public class FormularioControlador {
	
	@Autowired
	private FormularioDao formularioDao;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@GetMapping("/responderformulario")
	public String exibirFormulariosResp(Model model) {
		
		List<Formulario> formularios = formularioDao.findByUser(usuarioServico.getUsuario());
		
		model.addAttribute("formularios", formularios);
		model.addAttribute("usuario", usuarioServico.getUsuario());
		return "timeline";
	}

	@GetMapping("/responderformulario/{id}/{titulo}")
	public String responderFormulario(Model model, @PathVariable("id") int id) {
		
		Formulario formulario = formularioDao.findById(id);
		
		model.addAttribute("formulario", formulario);
		model.addAttribute("usuario", usuarioServico.getUsuario());
		return "form";
	}
	
	@GetMapping("/criarformulario")
	public String criarFormulario(Model model) {
		model.addAttribute("formulario", new Formulario());
		model.addAttribute("usuario", usuarioServico.getUsuario());
		model.addAttribute("module", "newForm");
		return "newForm";
	}
	
	@PostMapping("/criarformulario")
    public String criarFormulario(Model model, @Valid Formulario formulario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "newForm";
		}
		
		formulario.setId_usuario(usuarioServico.getMatricula());
		formulario.setDt_criacao(new Date());
		
		formularioDao.criarFormulario(formulario);
		model.addAttribute("formulario", new Formulario());
		return "newForm";
	}
	
	@GetMapping("/meusformularios")
	public String exibirMeusFormularios(Model model) {
		
		List<Formulario> formularios = formularioDao.findByUserId(usuarioServico.getMatricula());
		
		model.addAttribute("formularios", formularios);
		model.addAttribute("usuario", usuarioServico.getUsuario());
		model.addAttribute("module", "meusForms");
		return "myForms";
	}
	
	@RequestMapping(value = "/meusformularios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int removerFormulario(@RequestParam("id") int id) {
		int rs = formularioDao.removerFormulario(id);
		return rs;
	}
	
	@GetMapping("/editarformulario/{id}/{titulo}")
	public String editarFormulario(Model model, @PathVariable("id") int id) {
		
		Formulario formulario = formularioDao.findById(id);

		model.addAttribute("formulario", formulario);
		model.addAttribute("usuario", usuarioServico.getUsuario());
		return "editForm";
	}
	
	@RequestMapping(value = "/editarformulario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int editarFormulario(@RequestBody Formulario formulario) {
		int rs = formularioDao.editarFormulario(formulario);
		return rs;
	}
}

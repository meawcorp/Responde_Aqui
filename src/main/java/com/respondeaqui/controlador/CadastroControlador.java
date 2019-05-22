package com.respondeaqui.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.respondeaqui.modelo.Usuario;
import com.respondeaqui.servico.UsuarioServico;

@Controller
public class CadastroControlador {
	
	@Autowired
	private UsuarioServico usuarioServico;

	@GetMapping("/cadastro")
	public String cadastroForm(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "view/cadastro";
	}
	
	
	@PostMapping("/cadastro")
    public String cadastrarUsuario(Model model, @Valid Usuario usuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "view/cadastro";
		}
		
		if(usuarioServico.usuarioExiste(usuario.getMatricula())) {
			model.addAttribute("existe", true);
			return "view/cadastro";
		}
		
		//usuarioServico.criarUsuario(usuario);
		return "view/login";
	}

}

package com.respondeaqui.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.modelo.Usuario;
import com.respondeaqui.servico.UsuarioServico;

@Controller
public class CadastroControlador {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioServico usuarioServico;

	@GetMapping("/cadastro")
	public String cadastroForm(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "register";
	}
	
	@PostMapping("/cadastro")
    public String cadastrarUsuario(Model model, @Valid Usuario usuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		if(usuarioServico.usuarioExiste(usuario.getMatricula())) {
			model.addAttribute("existe",true);
			return "register";
		}
		
		//usuario.setCidade(cidadeDao.findByIdCidade(usuario.getId_cidade()).getNome());
		//usuario.setCampus(campusDao.findByIdCampus(usuario.getId_campus()).getNome());
		//usuario.setCurso(cursoDao.findByIdCurso(usuario.getId_curso()).getNome());
		
		usuarioDao.criarUsuario(usuario);
		return "login";
	}
}

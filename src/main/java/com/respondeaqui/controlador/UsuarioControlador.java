package com.respondeaqui.controlador;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.respondeaqui.dao.CampusDao;
import com.respondeaqui.dao.CidadeDao;
import com.respondeaqui.dao.CursoDao;
import com.respondeaqui.dao.UsuarioDao;
import com.respondeaqui.modelo.Campus;
import com.respondeaqui.modelo.Cidade;
import com.respondeaqui.modelo.Curso;
import com.respondeaqui.modelo.Usuario;
import com.respondeaqui.servico.UsuarioServico;

@Controller
public class UsuarioControlador {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private CidadeDao cidadeDao;
	
	@Autowired
	private CampusDao campusDao;
	
	@Autowired
	private CursoDao cursoDao;
	
	@Autowired
	private UsuarioServico usuarioServico;

	@GetMapping("/cadastro")
	public String cadastrarUsuario(Model model) {
		List<Cidade> cidades = cidadeDao.getCidades();
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("cidades", cidades);
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
		
		usuarioDao.cadastrarUsuario(usuario);
		return "login";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {

		model.addAttribute("usuario", usuarioServico.getUsuario());
		return "profile";
	}
	
	@RequestMapping(value = "/selectcampus/{id_cidade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Campus> selecionarCampus(@PathVariable("id_cidade") int id_cidade) {
		List<Campus> campus = campusDao.findByCidadeId(id_cidade);
		return campus;
	}
	
	@RequestMapping(value = "/selectcurso/{id_campus}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> selecionarCurso(@PathVariable("id_campus") int id_campus) {
		List<Curso> cursos = cursoDao.findByCampusId(id_campus);
		return cursos;
	}
}

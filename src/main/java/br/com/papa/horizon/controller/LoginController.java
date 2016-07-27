package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.UsuarioDao;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Usuario;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("login")
public class LoginController {
	
	UsuarioDao usuarioDao = new UsuarioDao();
	
	@RequestMapping
	public ModelAndView login(HttpSession httpSession) throws Exception {


		
		Gson gson = new Gson();
		
		
		Map<String, Object> retorno = new HashMap<String, Object>();

		return new ModelAndView("login").addObject("result",
				gson.toJson(retorno));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	
	@RequestMapping("/validarAcesso")
	public ModelAndView validaAcesso(Usuario usuario, HttpSession session){
		Usuario usu = new Usuario();
		Funcionario funcionario = new Funcionario();
		ModelAndView mv = new ModelAndView();
		
		try{
			usu = usuarioDao.localizaUsuario(usuario.getLogin());
			usuario.setId(usu.getId());
			usuario.setNivelAcesso(usu.getNivelAcesso());
			
		}catch(Exception e){
			System.out.println("ERRO AO VALIDAR USUARIO: " +e);
		}
		if(usuario.getSenha() == usu.getSenha()){
			if(usuario.getLogin() == usu.getLogin()){
				mv = new ModelAndView("redirect:clienteCadastro");							
			}
		}else{
			mv = new ModelAndView("login");
			
		}
		
		return mv;
	}

}

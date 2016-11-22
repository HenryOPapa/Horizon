
package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.UsuarioDao;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.FuncionarioVO;

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

	/*
	 * Primeiro metodo ao acessar a tela
	 * a tela de login. Responsavel por direcionar
	 * para a tela 
	 * "localhost:8080/horizon/login" 
	 */

	@RequestMapping
	public ModelAndView login(HttpSession httpSession) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		return new ModelAndView("login").addObject("result",
				gson.toJson(retorno));
	}
	
	


	/*
	 *Método acionado quando usuario loga
	 *no sistema, responsavel por validar
	 *se o usuario realmente existe e 
	 *direcionar o mesmo para a tela de menu 
	 */


	@RequestMapping(value = "/validarAcesso", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> validaAcesso(@RequestBody Usuario usuario , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		Usuario usu = new Usuario();
		DadosPadraoVO dadosPadraoVO = new DadosPadraoVO();
		Boolean validaLogin;

		try{
			usu = usuarioDao.localizaUsuario(usuario.getLogin());
			usuario.setId(usu.getId());
			usuario.setNivelAcesso(usu.getNivelAcesso());

		}catch(Exception e){
			System.out.println("ERRO AO VALIDAR USUARIO: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(usuario.getSenha().equals(usu.getSenha())){
			if(usuario.getLogin().equals(usu.getLogin())){
				
				FuncionarioVO funcionario = usuarioDao.localizaFuncionario(usu.getIdFuncionario());
				dadosPadraoVO.setFuncionario(funcionario);
				dadosPadraoVO.setUsuario(usu);
				httpSession.setAttribute("dadosPadraoVO", dadosPadraoVO);
				validaLogin = true;
				
				result.put("dadosPadraoVO", dadosPadraoVO);
				result.put("validaLogin", validaLogin);
				return new ResponseEntity<String>(gson.toJson(result), HttpStatus.OK);							
			}else{
			}
		}
		validaLogin = false;
		result.put("validaLogin", validaLogin);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}

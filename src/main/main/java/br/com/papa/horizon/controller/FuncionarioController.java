package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.dao.UsuarioDao;
import br.com.papa.horizon.entity.Especialidade;
//import br.com.papa.horizon.entity.Contato;
//import br.com.papa.horizon.entity.Documento;
//import br.com.papa.horizon.entity.Endereco;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Email;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("cadastroFuncionario")
public class FuncionarioController extends Exception{

	private FuncionarioDao funcionarioDao;

	@RequestMapping
	public ModelAndView cadastroFuncionario(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		funcionarioDao = new FuncionarioDao();
		List<Especialidade> especialidades = funcionarioDao.localizarEspecialidade();
		retorno.put("especialidades", especialidades);
		return new ModelAndView("funcionario/funcionarioCadastro").addObject("result",gson.toJson(retorno));
	}


	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarFuncionario(@RequestBody Funcionario funcionario , @RequestParam(value = "nivelAcesso") String nivelAcesso, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		funcionarioDao = new FuncionarioDao();

		try{
			funcionarioDao.save(funcionario);
			criarUsuario(funcionario, nivelAcesso);
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVO FUNCIONARIO: " +e);
			return new ResponseEntity<String>(gson.toJson(funcionario), HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}




	/*
	 * O metodo 'criarUsuario' tem como objetivo
	 * coletar o nome do funcionario adicionado
	 * e criar automaticamente um usuario de acesso
	 */
	public void criarUsuario(Funcionario funcionario, String nivelAcesso){

		UUID uuid = UUID.randomUUID();  
		Usuario usuario = new Usuario();
		UsuarioDao dao = new UsuarioDao();
		Email email = new Email();
		String[] split = funcionario.getNome().split(" ");
		if (split != null && split.length > 0){
			String login =  split[0] + "." + split[split.length - 1];
			login = login.toLowerCase();
			usuario.setLogin(login);
			usuario.setSenha(uuid.toString().substring(0,6));
			usuario.setNivelAcesso(nivelAcesso);
			usuario.setIdFuncionario(funcionario.getId());
		}

		try{ 
			dao.save(usuario);
			email.enviaEmailCadastroFuncionario(usuario.getSenha(), usuario.getLogin(), funcionario);
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR USUARIO: " + e);
		}
	}

}

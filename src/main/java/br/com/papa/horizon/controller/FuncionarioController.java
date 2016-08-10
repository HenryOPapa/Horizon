package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.dao.UsuarioDao;
//import br.com.papa.horizon.entity.Contato;
//import br.com.papa.horizon.entity.Documento;
//import br.com.papa.horizon.entity.Endereco;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Usuario;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@RequestMapping("cadastroFuncionario")
public class FuncionarioController extends Exception{

	FuncionarioDao funcionarioDao = new FuncionarioDao();
	
	@RequestMapping
	public ModelAndView cadastroFuncionario(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Funcionario> funcionarios = funcionarioDao.findAll();
		
		retorno.put("funcionarios", funcionarios);
		
		return new ModelAndView("funcionario/funcionarioCadastro").addObject("result",gson.toJson(retorno));
	}
	
	
	@RequestMapping("/adicionaFuncionario")//Método invocado pelo form action da jsp
	public String adiciona(Funcionario funcionario, Usuario usuario){
		funcionario.setUsuario(criarUsuario(funcionario, usuario));
		FuncionarioDao dao = new FuncionarioDao();
		dao.save(funcionario);

		return "contaAdicionadaSucesso";
	}

	@RequestMapping("/listaFuncionarios")//URL de Acesso 
	public ModelAndView lista(){

		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> funcionarios = dao.findAll();

		ModelAndView mv = new ModelAndView("listaFuncionario");
		mv.addObject("funcionarios", funcionarios);

		return mv;

	}
	
	@RequestMapping("/removeFuncionario")//URL de Acesso 
	public String remover(Funcionario funcionario){
		
		FuncionarioDao dao = new FuncionarioDao();
		dao.delete(funcionario);

		return "redirect:listaFuncionarios";
	}


	/*
	 * O metodo 'criarUsuario' tem como objetivo
	 * coletar o nome do funcionario adicionado
	 * e criar automaticamente um usuario de acesso
	 */
	public Usuario criarUsuario(Funcionario funcionario, Usuario usuario){

		String nomeCompleto = funcionario.getNome();
		String[] split = nomeCompleto.split(" ");
		if (split != null && split.length > 0){
			String primeiroNome = split[0];
			String ultimoNome = split[split.length - 1];
			String login = primeiroNome + "." +ultimoNome;

			UsuarioDao dao = new UsuarioDao();
			usuario.setLogin(login);
			usuario.setSenha("senha");
						

		}
		return usuario;
	}

}

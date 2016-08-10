package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.entity.Funcionario;

import com.google.gson.Gson;


/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("listaFuncionarios")
public class ListaFuncionarioController {
		
	FuncionarioDao funcionarioDao = new FuncionarioDao();
	
	
	@RequestMapping
	public ModelAndView cadastroFuncionario(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Funcionario> funcionarios = funcionarioDao.findAll();
		
		retorno.put("funcionarios", funcionarios);
		
		return new ModelAndView("funcionario/listaFuncionario").addObject("result",gson.toJson(retorno));
	}

	
	
}

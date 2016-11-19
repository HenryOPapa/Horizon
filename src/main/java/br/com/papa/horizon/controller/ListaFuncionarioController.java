package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.FuncionarioVO;

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
	public ModelAndView cadastroFuncionario(HttpSession session){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<FuncionarioVO> funcionarios = funcionarioDao.localizarFuncionarios();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) session.getAttribute("dadosPadraoVO");

		
		retorno.put("funcionarios", funcionarios);
		
		if("total".equals(dadosPadraoVO.getUsuario().getNivelAcesso())){
			return new ModelAndView("funcionario/listaFuncionario").addObject("result",gson.toJson(retorno));
		}else{
			return new ModelAndView("onlyAdmin").addObject("result",gson.toJson(null));			
		}
		
	}
	
	
	@RequestMapping(value = "/atualizarFuncionario", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> atualizarFuncionario(@RequestBody Funcionario funcionario , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		funcionarioDao = new FuncionarioDao();

		try{
			funcionarioDao.update(funcionario);
			return new ResponseEntity<String>(gson.toJson(funcionario), HttpStatus.OK);							

		}catch(Exception e){
			System.out.println("ERRO AO ATUALIZAR O FUNCIONARIO: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
	
}

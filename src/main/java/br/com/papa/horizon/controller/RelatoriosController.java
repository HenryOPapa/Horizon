package br.com.papa.horizon.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.FuncionarioVO;

import com.google.gson.Gson;


@Controller
@RequestMapping("relatorios")
public class RelatoriosController {
	
	FuncionarioDao funcionarioDao;
	
	@RequestMapping
	public ModelAndView menu(HttpSession httpSession) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) httpSession.getAttribute("dadosPadraoVO");
		funcionarioDao = new FuncionarioDao();
		List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();
		
		
		try{
			funcionarios = funcionarioDao.localizarFuncionarios();
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR FUNCIONARIOS "+e);
		}
		
		retorno.put("dadosPadraoVO", dadosPadraoVO);
		retorno.put("funcionarios", funcionarios);
		return new ModelAndView("relatorios").addObject("result",
				gson.toJson(retorno));
	}
	
	
	@RequestMapping(value = "/atualizarFiltroFuncionario", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> atualizarFiltroFuncionario(@RequestBody Funcionario funcionario,
														@RequestParam (value="idTarefa") int idTarefa,
														HttpSession httpSession) throws Exception { 
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) httpSession.getAttribute("dadosPadraoVO");
		FuncionarioDao dao = new FuncionarioDao();
		
		if(idTarefa < 3){
			Orcamento orcamento = new Orcamento();
			
		}else{
			
		}
		
		
		
		try{
			
			
			
		}catch(Exception e){
			System.out.println("ERRO AO ATUALIZAR SENHA: "+e);
		}
		
		retorno.put("dadosPadraoVO", dadosPadraoVO);

		
		return new ResponseEntity<String>(gson.toJson(retorno), HttpStatus.OK);							
	}
	
		

}

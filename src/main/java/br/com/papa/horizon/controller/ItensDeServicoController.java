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

import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.dao.ServicoDao;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.Servico;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("itensDeServico")
public class ItensDeServicoController {
	
	private PecasDao pecaDao;
	private ServicoDao servicoDao;
		
	@RequestMapping
	public ModelAndView cadastroPeca(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		
		pecaDao = new PecasDao();
		List<Peca> pecas = pecaDao.findAll();		
		
		retorno.put("pecas", pecas);
		
		return new ModelAndView("itensDeServico").addObject("result",
				gson.toJson(retorno));
	}
	
	@RequestMapping(value = "/cadastrarServico", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarServico(@RequestBody Servico servico , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			servicoDao = new ServicoDao();
			servicoDao.save(servico);
			return new ResponseEntity<String>(gson.toJson(servico), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVA PECA: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/cadastrarPeca", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarPeca(@RequestBody Peca peca , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			pecaDao.save(peca);
			return new ResponseEntity<String>(gson.toJson(peca), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVA PECA: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	

}

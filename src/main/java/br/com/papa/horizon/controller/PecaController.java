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
import br.com.papa.horizon.entity.Peca;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("cadastroPeca")
public class PecaController {
	
	PecasDao pecaDao = new PecasDao();
		
	@RequestMapping
	public ModelAndView cadastroPeca(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();

		List<Peca> pecas = pecaDao.findAll();		
		
		retorno.put("pecas", pecas);
		
		return new ModelAndView("cadastroPeca").addObject("result",
				gson.toJson(retorno));
	}
	
	@RequestMapping(value = "/cadastrarPeca", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> validaAcesso(@RequestBody Peca peca , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		PecasDao dao = new PecasDao();

		try{
			dao.save(peca);
			return new ResponseEntity<String>(gson.toJson(peca), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVA PECA: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	

}

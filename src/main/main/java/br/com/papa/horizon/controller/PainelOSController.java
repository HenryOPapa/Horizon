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
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Usuario;

import com.google.gson.Gson;
@Controller
@RequestMapping("painelOS")
public class PainelOSController {


	@RequestMapping
	public ModelAndView cadastroCliente(HttpSession session){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		
		try{	
		}catch(Exception e){
			System.out.println(""+e);
		}

		return new ModelAndView("painelOS").addObject("result",
				gson.toJson(retorno));
	}


	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarCliente(HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();

		try{
			
		}catch(Exception e){
			System.out.println("" +e);
			return new ResponseEntity<String>(gson.toJson(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	




	

	

}

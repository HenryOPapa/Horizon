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

import br.com.papa.horizon.dao.ClienteDao;
//import br.com.papa.horizon.dao.EnderecoDao;
import br.com.papa.horizon.entity.Cliente;

import com.google.gson.Gson;
//import br.com.papa.horizon.entity.Contato;
//import br.com.papa.horizon.entity.Documento;
//import br.com.papa.horizon.entity.Endereco;

/**
 * 
 * @author  
 *
 */

@Controller
@RequestMapping("cadastroCliente")
public class ClienteController {
	
	ClienteDao clienteDao = new ClienteDao();
	
	/**
	 * 
	 * Este método irá direcionar a aplicação 
	 * para a tela de cadastro de novo cliente
	 * 
	 * http://localhost:8080/horizon/cadastroCliente
	 */
	@RequestMapping
	public ModelAndView cadastroCliente(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		
		return new ModelAndView("cliente/clienteCadastro").addObject("result",
				gson.toJson(retorno));
	}
	
	
	/**
	 *Método responsável por
	 *receber os dados digitados
	 *e salvar o novo cliente
	 * @param cliente
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
//		Email email = new Email();

		try{
			clienteDao.save(cliente);
//			email.enviaEmailCadastroCliente(cliente.getEmail());
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVO CLIENTE: " +e);
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}

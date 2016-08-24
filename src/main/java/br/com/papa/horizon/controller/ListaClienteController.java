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

import br.com.papa.horizon.dao.ClienteDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.util.GsonExclusionStrategy;

import com.google.gson.Gson;

/**
 * 
 * @author 
 *
 */

@Controller
@RequestMapping("listaClientes")
public class ListaClienteController {
	
	ClienteDao clienteDao = new ClienteDao();
	
	/**
	 * 
	 * Este método irá direcionar a aplicação 
	 * para a tela de lista de clientes
	 * 
	 * http://localhost:8080/horizon/listaClientes
	 */
	@RequestMapping
	public ModelAndView listaClientes()throws Exception{

Gson gson = new Gson();
gson = GsonExclusionStrategy.createGsonFromBuilder(
			new GsonExclusionStrategy(Equipamento.class));
		Map<String, Object> retorno = new HashMap<String, Object>();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		try{
			clientes = clienteDao.findAll();
			
		}catch(Exception e){
			System.out.println("ERRO AO LISTAR OS CLIENTES: "+ e);
		}	
		
		retorno.put("clientes", clientes);
		
		return new ModelAndView("cliente/listaCliente").addObject("result",
				gson.toJson(retorno));
	}
	
	@RequestMapping(value = "/atualizarCliente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();


		try{
			clienteDao.update(cliente);
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO ATUALIZAR O CLIENTE: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/excluirCliente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> excluirCliente(@RequestBody Cliente cliente , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();

		try{
			clienteDao.delete(cliente.getId_cliente());
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO ATUALIZAR O CLIENTE: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/adicionarEquipamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adicionarEquipamento(@RequestParam(value = "id") Long id,@RequestBody Equipamento equipamento, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Equipamento> equipamentos  = new ArrayList<>();
		Cliente cliente = new Cliente();
		try{
			cliente = clienteDao.findById(id);
			equipamentos.add(equipamento);
			cliente.setEquipamentos(equipamentos);
			clienteDao.update(cliente);
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR EQUIPAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

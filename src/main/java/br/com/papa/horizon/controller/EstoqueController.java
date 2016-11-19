package br.com.papa.horizon.controller;

import java.util.Date;
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

import br.com.papa.horizon.dao.EstoqueDao;
import br.com.papa.horizon.entity.Estoque;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.log.LOGNotaFiscal;

import com.google.gson.Gson;


/**
 * 
 * @author Diana.Ramos / Felipe.Rodrigo
 *
 */

@Controller
@RequestMapping("estoque")
public class EstoqueController {
	
/*	Criando dentro de EstoqueController
	2 objetos de tipo EstoqueDao e LOGMainController*/
	
	private EstoqueDao estoqueDao;
	private LOGMainController logger;
	
	/**
	 * Metodo encarregado de buscar 
	 * todas as pecas cadastradas no sistema
	 * e exibir na tela de estoque para
	 * atualizar as quantidades
	 * 
	 * http://localhost:8080/horizon/estoque
	 */
		
	@RequestMapping
	public ModelAndView cadastroPeca(){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		
		estoqueDao = new EstoqueDao();
		
			List<Peca> pecas = 	estoqueDao.buscaPecas();
			List<Estoque> estoque = estoqueDao.findAll();
		
		retorno.put("pecas", pecas);
		retorno.put("estoque", estoque);

		
		return new ModelAndView("estoque").addObject("result",
				gson.toJson(retorno));
	}
	
	
	/**
	 * Método que ira receber a peca e a quantidade
	 * da mesma para atualizar o banco de estoque
	 * 
	 */
	@RequestMapping(value = "/atualizarEstoque", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> atualizarEstoque(@RequestBody Estoque estoque , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		logger = new LOGMainController();
		Peca peca = new Peca(); //Criando novo objeto peca
		estoqueDao = new EstoqueDao();//Criando nova instancia Data Acess Object
		Estoque retorno = new Estoque();//Criando novo objeto de Estoque
		
		try{
			retorno =  estoqueDao.pecaEmEstoque(estoque.getIdPeca());//Recupera a peca cadastrada em estoque que referencia a peca cadastrada
		}catch(Exception e){
			System.out.println("PECA NAO CADASTRADA NO ESTOQUE: " +e);
		}
		
		//Valida se a peca já esta cadastrada no estoque e atualiza o estoque
		if(estoque.getIdPeca() == retorno.getIdPeca()){ 
			estoque.setDescricaoPeca(retorno.getDescricaoPeca());
			retorno.setQuantidade(retorno.getQuantidade() + estoque.getQuantidade());
			try{
				estoqueDao.update(retorno);
				logger.salvarLogNotaFiscal(estoque);//Salvando LOG de nota fiscal do IF
			}catch(Exception e){
				System.out.println("ERRO AO ATUALIZAR ESTOQUE: " +e);
			}
		}else{
			peca = localizarPeca(estoque); //Aqui estamos chamando um método privado logo abaixo...
			logger.salvarLogNotaFiscal(estoque);//Salvando LOG de nota fiscal do ELSE
			estoqueDao.save(estoque);
		}
		List<Estoque> estoqueList = estoqueDao.findAll();
		
		result.put("estoque", estoqueList);
		return new ResponseEntity<String>(gson.toJson(result), HttpStatus.OK);							
	}
	
	//Henry é lindo \o\
	
	/*
	 * ###############################################################################################
	 * ################################### INICIO METODOS PRIVADOS ###################################
	 * ############################################################################################### 
	 */
	
	//Método responsavel por localizar uma peca na Tabela Peca
	
	public Peca localizarPeca(Estoque estoque){ //Método chamado pela linha 102 ;)
		Peca peca = new Peca();
		estoqueDao = new EstoqueDao();
		
		try{
			peca = estoqueDao.localizaPeca(estoque.getIdPeca());
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR PECA: "+e);
		}
		
		estoque.setDescricaoPeca(peca.getDescricao());
		return peca;
	}
	
	
//We are warriors, warriors of the World
}

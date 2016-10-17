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

import br.com.papa.horizon.dao.ClienteDao;
import br.com.papa.horizon.dao.EquipamentoDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.entity.PecaUtilizada;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

import com.google.gson.Gson;


/**
 * 
 * @author henry.papa
 *
 */

@Controller
@RequestMapping("aprovarReprovarOrcamento")
public class AprovarReprovarOrcamentoController {
	
	OrcamentoDao orcamentoDao;
	Usuario usuario;
	
	
	//Método responsavel por localizar orcamentos CONCLUIDOS e exibilos em lista
	@RequestMapping
	public ModelAndView aprovarReprovarOrcamento(HttpSession session){
		Gson gson = new Gson();		
		usuario = (Usuario) session.getAttribute("usuario");

		orcamentoDao = new OrcamentoDao();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		
		try{		
		 orcamentos =  orcamentoDao.getOrcamentosConcluidos(StatusOrcamento.CONCLUIDO);
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
		}
		retorno.put("orcamentos", orcamentos);
		return new ModelAndView("aprovarReprovarOrcamento").addObject("result",
				gson.toJson(retorno));
	}
	
	//Método responsavel por detalhar o orcamento selecionado
	@RequestMapping(value = "/detalharOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		usuario = new Usuario();
		Cliente cliente = new Cliente();
		Equipamento equipamento = new Equipamento();
		List<PecaUtilizada> itensDeServico = new ArrayList<PecaUtilizada>();
		usuario = (Usuario) httpSession.getAttribute("usuario");
		Map<String, Object> result = new HashMap<String, Object>();
		

		try{
			itensDeServico = orcamentoDao.getItensDeServico(orcamento.getId_orcamento()); //Recurando as pecas utilizadas atraves do id do orcamento
			cliente =  identificarCliente(orcamento.getIdCliente()); 
			equipamento = identificarEquipamento(orcamento.getIdEquipamento());	
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento());
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR ITENS DE ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		httpSession.setAttribute("cliente", cliente);
		result.put("cliente", cliente);
		result.put("equipamento", equipamento);
		result.put("orcamento", orcamento);
		result.put("itensDeServico", itensDeServico);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	
	//Método responsavel por mudar o STATUS do orcamento para REPROVADO
	@RequestMapping(value = "/reprovarOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> reprovarOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();
		

		try{
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento());
			orcamento.setStatusOrcamento(StatusOrcamento.REPROVADO);
			orcamentoDao.update(orcamento);
			
		}catch(Exception e){
			System.out.println("ERRO AO REPROVAR ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	//Método responsavel por APROVAR o Orcamento e gerar a O.S.
	@RequestMapping(value = "/aprovarOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> aprovarOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		OrdemDeServico ordemServico = new OrdemDeServico();
		List<ItensOrdemServico> itensServico = new ArrayList<ItensOrdemServico>();
		orcamentoDao = new OrcamentoDao();
		

		try{
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento());
			orcamento.setStatusOrcamento(StatusOrcamento.APROVADO);
			orcamentoDao.update(orcamento);
		}catch(Exception e){
			System.out.println("ERRO AO APROVAR ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		try{
			ordemServico = gerarOS(orcamento, ordemServico);
			itensServico = adicionarItensDeServico(orcamento, ordemServico);
		}catch(Exception e){
			System.out.println("ERRO AO GERAR O.S.: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	
	
	/*
	 * ###############################################################################################
	 * ################################### INICIO METODOS PRIVADOS ###################################
	 * ############################################################################################### 
	 */
		
	

	private Equipamento identificarEquipamento(Long idEquipamento) {
		EquipamentoDao dao = new EquipamentoDao();
		Equipamento equipamento = new Equipamento();
		Equipamento retorno= new Equipamento();
		retorno = dao.findById(idEquipamento);
		equipamento.setId_equipamento(retorno.getId_equipamento());
		equipamento.setMarca(retorno.getMarca());
		equipamento.setModelo(retorno.getModelo());
		equipamento.setNumeroSerie(retorno.getNumeroSerie());
		equipamento.setTipoEquipamento(retorno.getTipoEquipamento());
		return equipamento;
	}
	
	
	private Cliente identificarCliente(Long idCliente) {
		ClienteDao dao = new ClienteDao();
		Cliente cliente = new Cliente();
		Cliente retorno = new Cliente();
		retorno = dao.findById(idCliente);
		cliente.setCep(retorno.getCep());
		cliente.setCidade(retorno.getCidade());
		cliente.setCpf(retorno.getCpf());
		cliente.setDataNascimento(retorno.getDataNascimento());
		cliente.setEmail(retorno.getEmail());
		cliente.setId_cliente(retorno.getId_cliente());
		cliente.setNome(retorno.getNome());
		cliente.setTelefone(retorno.getTelefone());
		
		return cliente;
	}
	
	
	private List<ItensOrdemServico> adicionarItensDeServico(Orcamento orcamento, OrdemDeServico ordemServico) {
		orcamentoDao = new OrcamentoDao();
		List<PecaUtilizada> itensOrcamento = new ArrayList<PecaUtilizada>();
		ItensOrdemServico itemServico = new ItensOrdemServico();
		List<ItensOrdemServico> itensServico = new ArrayList<ItensOrdemServico>();
		
		itensOrcamento = orcamentoDao.getItensDeServico(orcamento.getId_orcamento());
		
		for(PecaUtilizada item : itensOrcamento ){
			itemServico.setDescricao(item.getDescricao());
			itemServico.setQuantidade(item.getQuantidade());
			itemServico.setValor(item.getValor());
			itemServico.setIdOS(ordemServico.getIdOrdemServico());
			itensServico.add(itemServico);
		}
		
		return itensServico;
	}
	
	//Método responsavel por criar a Ordem de Servico a partir do Orcamento
	private OrdemDeServico gerarOS(Orcamento orcamento, OrdemDeServico ordemServico){
		
		ordemServico.setIdCliente(ordemServico.getIdCliente());
		ordemServico.setIdEquipamento(ordemServico.getIdEquipamento());
		ordemServico.setIdEspecialidade(ordemServico.getIdEspecialidade());
		ordemServico.setObservacao(ordemServico.getObservacao());
		ordemServico.setPontos(ordemServico.getPontos());
		ordemServico.setValorTotal(ordemServico.getValorTotal());
		ordemServico.setRelato(orcamento.getRelato());
		ordemServico.setStatusOrdemServico(StatusOrdemDeServico.BACKLOG);
		
		return ordemServico;
	}
}

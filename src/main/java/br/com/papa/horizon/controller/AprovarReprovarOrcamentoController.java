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

import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.OrdemDeServicoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;
import br.com.papa.horizon.vo.ClienteVO;
import br.com.papa.horizon.vo.EquipamentoVO;
import br.com.papa.horizon.vo.OrcamentoVO;

import com.google.gson.Gson;


/**
 * 
 * @author henry.papa
 *
 */

@Controller
@RequestMapping("aprovarReprovarOrcamento")
public class AprovarReprovarOrcamentoController {
	
	OrdemDeServicoDao ordemDeServicoDao;
	OrcamentoDao orcamentoDao;
	Usuario usuario;
	
	
	/**
	 * 	Método inicial, ira recuperar do banco todos os orcamentos 
	 * com STATUS de CONCLUIDO e envia para tela.
	 * @param session
	 * @return Lista de orcamentos concluidos
	 * 
	 */
	@RequestMapping
	public ModelAndView aprovarReprovarOrcamento(HttpSession session){
		Gson gson = new Gson();		
		usuario = (Usuario) session.getAttribute("usuario");

		orcamentoDao = new OrcamentoDao();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<OrcamentoVO> orcamentos = new ArrayList<OrcamentoVO>();
		
		try{		
		 orcamentos =  orcamentoDao.getOrcamentosConcluidos(StatusOrcamento.CONCLUIDO);
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
		}
		retorno.put("orcamentos", orcamentos);
		return new ModelAndView("aprovarReprovarOrcamento").addObject("result",
				gson.toJson(retorno));

	}
	
	/**
	 * 
	 * @param orcamento  Recuperamos o ID do orcamento selecionado para localizar no banco
	 * @param httpSession
	 * @return Retorna todos os dados de orcamento, cliente e equipamento para tela, assim como  a lista de itens utilizados
	 * @throws Exception
	 */
	@RequestMapping(value = "/detalharOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		orcamentoDao = new OrcamentoDao();
		List<ItensOrcamento> itensOrcamento = new ArrayList<ItensOrcamento>();
		Map<String, Object> result = new HashMap<String, Object>();
		

		try{
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento()); //Localizar o orcamento pelo ID
			itensOrcamento = orcamentoDao.getItensDeServico(orcamento.getId_orcamento()); //Recurando as pecas utilizadas atraves do id do orcamento			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR ITENS DE ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		EquipamentoVO equipamentoVO = (EquipamentoVO) createEquipamentoVO(orcamento.getEquipamento()); //Populando o VO de equipamento 
		ClienteVO clienteVO = (ClienteVO) createClienteVO(orcamento.getCliente());//Populando o VO de cliente
		
		result.put("cliente", clienteVO);
		result.put("equipamento", equipamentoVO);
		result.put("orcamento", createOrcamentoVO(orcamento)); //Passa como parametro o retorno do metodo de populacao de VO de orcamento
		result.put("itensOrcamento", itensOrcamento);
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
		ordemDeServicoDao = new OrdemDeServicoDao();
		

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
			ordemServico = adicionarItensDeServico(orcamento, ordemServico);
			ordemDeServicoDao.update(ordemServico);
			
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
	
	private Object createEquipamentoVO(Equipamento equipamento) {
		EquipamentoVO equipamentoVO = new EquipamentoVO();
		equipamentoVO.setId_equipamento(equipamento.getId_equipamento());
		equipamentoVO.setMarca(equipamento.getMarca());
		equipamentoVO.setModelo(equipamento.getModelo());
		equipamentoVO.setNumeroSerie(equipamento.getNumeroSerie());
		equipamentoVO.setTipoEquipamento(equipamento.getTipoEquipamento());
		return equipamentoVO;
	}
	
	
	private Object createClienteVO(Cliente cliente) {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setId(cliente.getId_cliente());
		clienteVO.setNome(cliente.getNome());
		clienteVO.setCpf(cliente.getCpf());
		return clienteVO;
	}
	
	public OrcamentoVO createOrcamentoVO(Orcamento orcamento){
		OrcamentoVO orcamentoVO = new OrcamentoVO();		
		orcamentoVO.setObservacao(orcamento.getObservacao());
		orcamentoVO.setPontos(orcamento.getPontos());
		orcamentoVO.setRelato(orcamento.getRelato());
		orcamentoVO.setStatusOrcamento(orcamento.getStatusOrcamento());
		orcamentoVO.setValorTotal(orcamento.getValorTotal());
		orcamentoVO.setId_orcamento(orcamento.getId_orcamento());
				
		return orcamentoVO;
	}
			
	private OrdemDeServico adicionarItensDeServico(Orcamento orcamento, OrdemDeServico ordemServico) {
		orcamentoDao = new OrcamentoDao();
		
		List<ItensOrcamento> itensOrcamento = new ArrayList<ItensOrcamento>();
		ItensOrdemServico itemServico = new ItensOrdemServico();
		
		for(ItensOrcamento item : itensOrcamento ){
			itemServico.setDescricao(item.getDescricao());
			itemServico.setQuantidade(item.getQuantidade());
			itemServico.setValor(item.getValor());
			ordemServico.addItemOS(itemServico);
		}
		
		return ordemServico;
	}
	
	//Método responsavel por criar a Ordem de Servico a partir do Orcamento
	private OrdemDeServico gerarOS(Orcamento orcamento, OrdemDeServico ordemServico){
		Cliente cliente = orcamento.getCliente();
		ordemServico.setEquipamento(orcamento.getEquipamento());
		ordemServico.setEspecialidade(orcamento.getEspecialidade());
		ordemServico.setStatusOrdemServico(StatusOrdemDeServico.TODO);
		ordemServico.setObservacao(orcamento.getObservacao());
		ordemServico.setPontos(orcamento.getPontos());
		ordemServico.setValorTotal(orcamento.getValorTotal());
		ordemServico.setRelato(orcamento.getRelato());
		cliente.addOrdemServico(ordemServico);
		
		return ordemServico;
	}
}

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

import br.com.papa.horizon.dao.OrdemDeServicoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;
import br.com.papa.horizon.vo.ClienteVO;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.EquipamentoVO;
import br.com.papa.horizon.vo.ItensOrdemServicoVO;
import br.com.papa.horizon.vo.OrdemDeServicoVO;

import com.google.gson.Gson;
@Controller
@RequestMapping("painelOS")
public class PainelOSController {
	
	OrdemDeServicoDao ordemDeServicoDao;

	/**
	 * Método que ira carregar todas as OS na tela
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping
	public ModelAndView cadastroCliente(HttpSession session){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		ordemDeServicoDao = new OrdemDeServicoDao();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) session.getAttribute("dadosPadraoVO"); //Recuperando o funcionario e o usuario da sessao
		List<OrdemDeServicoVO> ordensDeServicoTODO = new ArrayList<OrdemDeServicoVO>();
		List<OrdemDeServicoVO> ordensDeServicoWIP = new ArrayList<OrdemDeServicoVO>();
		List<OrdemDeServicoVO> ordensDeServicoDONE = new ArrayList<OrdemDeServicoVO>();
		
		try{	
			ordensDeServicoTODO = ordemDeServicoDao.localizarTODO(StatusOrdemDeServico.TODO);
			ordensDeServicoWIP = ordemDeServicoDao.localizarWIP(StatusOrdemDeServico.WIP);
			ordensDeServicoDONE = ordemDeServicoDao.localizarDONE(StatusOrdemDeServico.DONE);
		}catch(Exception e){
			System.out.println(""+e);
		}
		
		retorno.put("ordensDeServicoTODO", ordensDeServicoTODO);
		retorno.put("ordensDeServicoWIP", ordensDeServicoWIP);
		retorno.put("ordensDeServicoDONE", ordensDeServicoDONE);
		retorno.put("dadosPadraoVO", dadosPadraoVO);
		return new ModelAndView("painelOS").addObject("result",
				gson.toJson(retorno));
	}


	@RequestMapping(value = "/iniciarTarefa", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> iniciarTarefa(@RequestBody OrdemDeServicoVO ordemDeServicoVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		OrdemDeServico ordemDeServico = new OrdemDeServico();
		ordemDeServicoDao = new OrdemDeServicoDao();
		ordemDeServico = ordemDeServicoDao.findById(ordemDeServicoVO.getIdOrdemServico());

		try{
			ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.WIP);
			ordemDeServicoDao.update(ordemDeServico);
		}catch(Exception e){
			System.out.println("ERRO AO INICIAR TAREFA" +e);
			return new ResponseEntity<String>(gson.toJson(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/finalizarTarefa", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> finalizarTarefa(@RequestBody OrdemDeServicoVO ordemDeServicoVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		OrdemDeServico ordemDeServico = new OrdemDeServico();
		ordemDeServicoDao = new OrdemDeServicoDao();
		ordemDeServico = ordemDeServicoDao.findById(ordemDeServicoVO.getIdOrdemServico());

		try{
			ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.DONE);
			ordemDeServicoDao.update(ordemDeServico);
		}catch(Exception e){
			System.out.println("ERRO AO INICIAR TAREFA" +e);
			return new ResponseEntity<String>(gson.toJson(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detalharTarefa", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharTarefa(@RequestBody OrdemDeServicoVO ordemDeServicoVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		OrdemDeServico ordemDeServico = new OrdemDeServico();
		ordemDeServicoDao = new OrdemDeServicoDao();

		try{
			ordemDeServico = ordemDeServicoDao.findById(ordemDeServicoVO.getIdOrdemServico());
			
		}catch(Exception e){
			System.out.println("ERRO AO INICIAR TAREFA" +e);
			return new ResponseEntity<String>(gson.toJson(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("cliente", createClienteVO(ordemDeServico.getCliente()));
		result.put("equipamento", createEquipamentoVO(ordemDeServico.getEquipamento()));
		result.put("ordemDeServicoVO", createOSVO(ordemDeServico));
		result.put("itensOrdemDeServico", createItensDeOSVO(ordemDeServicoDao.getItensOrdemServico(ordemDeServico.getIdOrdemServico())));
		

		
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

	private Object createOSVO(OrdemDeServico ordemDeServico) {
		OrdemDeServicoVO ordemDeServicoVO = new OrdemDeServicoVO();
		ordemDeServicoVO.setObservacao(ordemDeServico.getObservacao());
		ordemDeServicoVO.setPontos(ordemDeServico.getPontos());
		ordemDeServicoVO.setRelato(ordemDeServico.getRelato());
		ordemDeServicoVO.setStatusOrdemServico(ordemDeServico.getStatusOrdemServico());
		ordemDeServicoVO.setValorTotal(ordemDeServico.getValorTotal());
		ordemDeServicoVO.setIdOrdemServico(ordemDeServico.getIdOrdemServico());
		ordemDeServicoVO.setDataCriacao(ordemDeServico.getDataCriacao());
		ordemDeServicoVO.setEspecialidade(ordemDeServico.getEspecialidade().getDescricao());
		return ordemDeServicoVO;
	}
	
	private List<ItensOrdemServicoVO> createItensDeOSVO(List<ItensOrdemServico> itensOrdemServico){
		List<ItensOrdemServicoVO> itensOrdemServicoVO = new ArrayList<ItensOrdemServicoVO>();
		ItensOrdemServicoVO itenOrdemServico;
		
		for(ItensOrdemServico item : itensOrdemServico){
			itenOrdemServico = new ItensOrdemServicoVO();
			
			itenOrdemServico.setDescricao(item.getDescricao());
			itenOrdemServico.setIdItemOrdemServico(item.getIdItemServico());
			itenOrdemServico.setQuantidade(item.getQuantidade());
			itenOrdemServico.setValor(item.getValor());
			itensOrdemServicoVO.add(itenOrdemServico);
			
		}
		return itensOrdemServicoVO;
	}
	

	

	

}

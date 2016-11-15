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
import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.dao.ServicoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.Servico;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.vo.ClienteVO;
import br.com.papa.horizon.vo.EquipamentoVO;
import br.com.papa.horizon.vo.EspecialidadeVO;
import br.com.papa.horizon.vo.OrcamentoAuxiliarVO;
import br.com.papa.horizon.vo.OrcamentoVO;

import com.google.gson.Gson;
 

/****
 *  @author Drackor
 *
 */
@Controller
@RequestMapping("manutencaoOrcamento")
public class ManutencaoOrcamentoController {
	OrcamentoDao orcamentoDao;
	Usuario usuario;
	
	List<Peca> pecasUtilizadasOrcamento = new ArrayList<Peca>();
	List<Servico> servicosUtilizadosOrcamento = new ArrayList<Servico>();
	
	@RequestMapping
	public ModelAndView manutencaoOrcamento(HttpSession session){
		Gson gson = new Gson();		usuario = (Usuario) session.getAttribute("usuario");

		orcamentoDao = new OrcamentoDao();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<OrcamentoVO> orcamentos = new ArrayList<OrcamentoVO>();
		
		
		try{		
		 orcamentos =  orcamentoDao.buscarOrcamentos();
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
		}
		retorno.put("orcamentos", orcamentos);
		return new ModelAndView("manutencaoOrcamento").addObject("result",
				gson.toJson(retorno));
	}
	
	
	/**
	 * 
	 * @param orcamento Recebe o objeto orcamento selecionado na lista
	 * @param httpSession
	 * @return Atraves do ID irá recuperar os dados de orcamento assim como 
	 * cliente, equipamento e especialidade vinculadas a ele, ira montar cada objeto
	 * dentro de seu respectivo VO e enviar para o Front.
	 * @throws Exception
	 */
	@RequestMapping(value = "/detalharOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		orcamentoDao = new OrcamentoDao();
		usuario = (Usuario) httpSession.getAttribute("usuario");
		Map<String, Object> result = new HashMap<String, Object>();
		List<ItensOrcamento> itensDeServico = new ArrayList<ItensOrcamento>();

		

		try{
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento());
				
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR ITENS DE ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("orcamento", createOrcamentoVO(orcamento));
		result.put("cliente", createClienteVO(orcamento.getCliente()));
		result.put("equipamento", createEquipamentoVO(orcamento.getEquipamento()));
		result.put("especialidade", createEspecialidadeVO(orcamento.getEspecialidade()));
		result.put("pecas", findPecas());
		result.put("servicos", findServicos());
		result.put("itensDeServico", itensDeServico);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	


	/**
	 * 
	 * @param peca Recebe a peça selecionadas na lista
	 * @param httpSession
	 * @return Apos adicionar a peca a lista de pecas utilizadas,
	 * retorna para a tela as pecas ja cadastradas no orcamento
	 * @throws Exception
	 */

	@RequestMapping(value = "/adicionarPeca", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adicionarPeca(@RequestBody ItensOrcamento itemDeServico , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();
		

		try{

		}catch(Exception e){
			System.out.println("ERRO: " +e);
			return new ResponseEntity<String>(gson.toJson(itemDeServico), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("itemDeServico", itemDeServico);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	

	
	/**
	 * 
	 * @param servico Recebe servico selecionado na lista
	 * @param httpSession
	 * @return Apos adicionar o servico na lista de servicos utilizados,
	 * retorna para a tela os servicos ja cadastrados no orcamento
	 * @throws Exception
	 */
	@RequestMapping(value = "/adicionarServico", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adicionarServico(@RequestBody Servico servico , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();
		ItensOrcamento itemDeServico = new ItensOrcamento();
		List<ItensOrcamento> itensDeServico = new ArrayList<ItensOrcamento>();
		

		try{
			itemDeServico = orcamentoDao.localizarServico(servico.getId_servico()); 
			servico = orcamentoDao.localizarServicoUnico(servico.getId_servico());
			this.servicosUtilizadosOrcamento.add(servico);
		}catch(Exception e){
			System.out.println("ERRO: " +e);
			return new ResponseEntity<String>(gson.toJson(itemDeServico), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("itemDeServico", itemDeServico);
		result.put("itensDeServico", itensDeServico);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> finalizar(@RequestBody OrcamentoAuxiliarVO orcamentoAuxiliarVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();	
		Orcamento orcamento = new Orcamento();
		orcamento = orcamentoDao.findById(orcamentoAuxiliarVO.getOrcamento().getId_orcamento());
		double valorTotal = 0;
		
		
		for (ItensOrcamento item : orcamentoAuxiliarVO.getItensOrcamento()){
			item.setIdOrcamento(orcamento.getId_orcamento());
			valorTotal += item.getValorTotal();
		}
		orcamento.setObservacao(orcamentoAuxiliarVO.getOrcamento().getObservacao());
		orcamento.setPontos(orcamentoAuxiliarVO.getOrcamento().getPontos());
		orcamento.setValorTotal(valorTotal);
		orcamento.setStatusOrcamento(StatusOrcamento.CONCLUIDO);
		try{
			orcamentoDao.salvarItensDeOrcamento(orcamentoAuxiliarVO.getItensOrcamento());		
			orcamentoDao.update(orcamento);
//			orcamentoDao.enviarEmailCliente(cliente, orcamento);

		}catch(Exception e){
			System.out.println("ERRO: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}


	/*
	 * ###############################################################################################
	 * ################################### INICIO METODOS PRIVADOS ###################################
	 * ############################################################################################### 
	 */
	

	private Object findPecas() {
		PecasDao dao = new PecasDao();
		return dao.findAll();
	}
	
	
	private Object findServicos() {
		ServicoDao dao = new ServicoDao();
		return dao.findAll();
	}
	
	
	private Object createEspecialidadeVO(Especialidade especialidade) {
		EspecialidadeVO especialidadeVO = new EspecialidadeVO();
		especialidadeVO.setId_especialidade(especialidade.getId_especialidade());
		especialidadeVO.setDescricao(especialidade.getDescricao());
		return especialidadeVO;
	}
	
	
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

	private Object createOrcamentoVO(Orcamento orcamento) {
		OrcamentoVO orcamentoVO = new OrcamentoVO();
		orcamentoVO.setObservacao(orcamento.getObservacao());
		orcamentoVO.setPontos(orcamento.getPontos());
		orcamentoVO.setRelato(orcamento.getRelato());
		orcamentoVO.setStatusOrcamento(orcamento.getStatusOrcamento());
		orcamentoVO.setValorTotal(orcamento.getValorTotal());
		orcamentoVO.setId_orcamento(orcamento.getId_orcamento());
		return orcamentoVO;
	}

}

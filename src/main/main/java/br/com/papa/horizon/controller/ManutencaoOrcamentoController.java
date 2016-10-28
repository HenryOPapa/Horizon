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
import br.com.papa.horizon.dao.EquipamentoDao;
import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.dao.ServicoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Estoque;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaUtilizada;
import br.com.papa.horizon.entity.Servico;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrcamento;

import com.google.gson.Gson;
 

//Este método irá abrir a tela passando a url localhost:8080/horizon/manutencaoOrcamento

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
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		
		
		try{		
		 orcamentos =  orcamentoDao.findAll();
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
		}
		retorno.put("orcamentos", orcamentos);
		return new ModelAndView("manutencaoOrcamento").addObject("result",
				gson.toJson(retorno));
	}
	
	
	@RequestMapping(value = "/detalharOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		usuario = new Usuario();
		Cliente cliente = new Cliente();
		Equipamento equipamento = new Equipamento();
		Especialidade especialidade = new Especialidade();
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		List<Peca> pecas = new ArrayList<Peca>();
		List<Servico> servicos = new ArrayList<Servico>();
		
		usuario = (Usuario) httpSession.getAttribute("usuario");
		Map<String, Object> result = new HashMap<String, Object>();
		

		try{
			cliente =  identificarCliente(orcamento.getIdCliente());
			equipamento = identificarEquipamento(orcamento.getIdEquipamento());
			especialidade = identificarEspecialidade(orcamento.getIdEspecialidade());
			pecas = identificarPecas();
			servicos = identificarServicos();
						
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR ITENS DE ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		httpSession.setAttribute("cliente", cliente);
		result.put("cliente", cliente);
		result.put("equipamento", equipamento);
		result.put("especialidade", especialidade);
		result.put("orcamento", orcamento);
		result.put("pecas", pecas);
		result.put("servicos", servicos);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	


	@RequestMapping(value = "/adicionarPeca", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adicionarPeca(@RequestBody Peca peca , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();
		PecaUtilizada itemDeServico = new PecaUtilizada();
		List<PecaUtilizada> itensDeServico = new ArrayList<PecaUtilizada>();
		

		try{
			itemDeServico = orcamentoDao.localizarPecaUnica(peca.getIdPeca()); 
			peca = orcamentoDao.localizaPecaUnica(peca.getIdPeca());
			this.pecasUtilizadasOrcamento.add(peca);
		}catch(Exception e){
			System.out.println("ERRO: " +e);
			return new ResponseEntity<String>(gson.toJson(itemDeServico), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("itemDeServico", itemDeServico);
		result.put("itensDeServico", itensDeServico);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	


	@RequestMapping(value = "/adicionarServico", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adicionarServico(@RequestBody Servico servico , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();
		PecaUtilizada itemDeServico = new PecaUtilizada();
		List<PecaUtilizada> itensDeServico = new ArrayList<PecaUtilizada>();
		

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
	
	@RequestMapping(value = "/salvarAlteracoesOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> salvarOrcamento(@RequestBody Orcamento orcamento, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();		

		try{
			Integer ponto = orcamento.getPontos();
			String observacao = orcamento.getObservacao();
			orcamento = orcamentoDao.findById(orcamento.getId_orcamento()); 
			orcamento.setPontos(ponto);
			orcamento.setObservacao(observacao);
			orcamentoDao.update(orcamento);
			httpSession.setAttribute("orcamento", orcamento);

		}catch(Exception e){
			System.out.println("ERRO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> finalizar(@RequestBody List<PecaUtilizada> itensDeServico,
										@RequestParam(value = "valorTotal") Double valorTotal, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		orcamentoDao = new OrcamentoDao();	
		Orcamento orcamento = new Orcamento();
		orcamento = (Orcamento) httpSession.getAttribute("orcamento");
		Cliente cliente = new Cliente();
		cliente = (Cliente) httpSession.getAttribute("cliente");

		try{
			
			for (int i = 0; i < itensDeServico.size(); i++) {
				itensDeServico.get(i).setIdOrcamento(orcamento.getId_orcamento());
				orcamento.setValorTotal(valorTotal);
			}	
			orcamento.setStatusOrcamento(StatusOrcamento.CONCLUIDO);
			orcamentoDao.update(orcamento);
			orcamentoDao.enviarEmailCliente(cliente, orcamento);
			orcamentoDao.salvarItensDeOrcamento(itensDeServico);
			httpSession.removeAttribute("cliente");

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
	
	private List<Servico> identificarServicos() {
		ServicoDao dao = new ServicoDao();
		return dao.findAll();
	}
	
	private List<Peca> identificarPecas() {
		PecasDao dao = new PecasDao();
		return dao.findAll();
	}
	
	private Especialidade identificarEspecialidade(Long idEspecialidade) {
		EspecialidadeDao dao = new EspecialidadeDao();
		Especialidade especialidade = new Especialidade();
		especialidade = dao.findById(idEspecialidade);
	
		return especialidade;
	}
	
	
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
	

//	
//	private void adicionarPeca(PecaUtilizada itemDeServico) {
//		Peca peca = new Peca();
//		peca.setDescricao(itemDeServico.getDescricao());
//		peca.setQuantidadeMinima(itemDeServico.getQuantidade());
//		peca.setValor(itemDeServico.getValor());
//		this.pecasUtilizadasOrcamento.add(peca);
//		
//	}
//	
//	private void adicionarServico() {
//		// TODO Auto-generated method stub
//		
//	}





}

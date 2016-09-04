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
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.entity.PecaOrdemServico;
import br.com.papa.horizon.entity.PecaUtilizada;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("novoOrcamento")
public class OrcamentoCadastroController {
	OrcamentoDao orcamentoDao = new OrcamentoDao();
	Usuario usuario;

	@RequestMapping
	public ModelAndView cadastroCliente(HttpSession session){
		Gson gson = new Gson();
		usuario = (Usuario) session.getAttribute("usuario");
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Especialidade> especialidades = new ArrayList<Especialidade>();
		
		try{	
		especialidades = (List<Especialidade>) orcamentoDao.localizaEspecialidade();
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ESPECIALIDADES: "+e);
		}
		retorno.put("especialidades", especialidades);
		return new ModelAndView("orcamentoCadastro").addObject("result",
				gson.toJson(retorno));
	}


	@RequestMapping(value = "/localizarCliente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		usuario = new Usuario();
		usuario = (Usuario) httpSession.getAttribute("usuario");
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();
		Map<String, Object> result = new HashMap<String, Object>();

		cliente = orcamentoDao.procuraPorCpf(cliente.getCpf());
		try{
			httpSession.setAttribute("cliente", cliente);
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR CLIENTE: " +e);
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		equipamentos = cliente.getEquipamentos();
		cliente.setEquipamentos(new ArrayList());
		result.put("usuario", usuario);
		result.put("cliente", cliente);
		result.put("equipamentos", equipamentos);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salvarOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> salvarOrcamento(@RequestBody Orcamento orcamento, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		Cliente cliente = new Cliente();
		Equipamento equipamento = new Equipamento();
		Especialidade especialidade = new Especialidade();
		cliente = (Cliente) httpSession.getAttribute("cliente");
		

		try{
			equipamento = orcamentoDao.procuraEquipamento(orcamento.getNumSerieEquipamento());
			especialidade = orcamentoDao.procuraEspecialidade(Long.parseLong(orcamento.getIdEspecialidade()));
			orcamento.setCliente(cliente);
			orcamento.setEquipamento(equipamento);
			orcamento.setEspecialidade(especialidade);

			
			
			orcamentoDao.update(orcamento);
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR ORCAMENTO: " +e);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpSession.removeAttribute("cliente");
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}



	
	
	
	
	
	
	

	
	
	
	
	
	/**
	 * Inicio dos metodos privados
	 */


	public Double calculaTotal(List<PecaUtilizada> pecaUtilizadas){
		double total = 0;
		for(PecaUtilizada peca : pecaUtilizadas){
			total = total + peca.getValor() * peca.getQuantidade();
		}
		return total;
	}


	public OrdemDeServico gerarOrdemDeServico(Orcamento orcamento, OrdemDeServico novaOrdem){
		PecaOrdemServico pecaOrdem = new PecaOrdemServico();
		List<PecaOrdemServico> pecasOrdem = new ArrayList<PecaOrdemServico>();
		novaOrdem.setCliente(orcamento.getCliente());
//		novaOrdem.setEquipamentoDanificado(orcamento.getEquipamentoDanificado());
//		novaOrdem.setEspecialidade(orcamento.getEspecialidade());
		novaOrdem.setObservacao(orcamento.getObservacao());
		
		for(PecaUtilizada peca : orcamentoDao.localizaPecasUtilizadas(orcamento.getId_orcamento())){
			pecaOrdem.setDescricao(peca.getDescricao());
			pecaOrdem.setValor(peca.getValor());
			pecaOrdem.setQuantidade(peca.getQuantidade());
			novaOrdem.addPeca(pecaOrdem);
		}
		
		pecasOrdem.add(pecaOrdem);
		novaOrdem.setRelato(orcamento.getRelato());
		novaOrdem.setServicos(orcamento.getServicos());
		novaOrdem.setValorTotal(orcamento.getValorTotal());
		novaOrdem.setStatusOrdemDeServico(StatusOrdemDeServico.BACKLOG);
		try{
			OrdemDeServicoDao dao = new OrdemDeServicoDao();
			dao.adicionaNovaOrdem(orcamento.getCliente(), novaOrdem);
			dao.save(novaOrdem);
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR ORDEM DE SERVICO: "+e);
		}


		return novaOrdem;
	}

}

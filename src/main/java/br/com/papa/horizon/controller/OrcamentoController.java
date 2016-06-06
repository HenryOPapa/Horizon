package br.com.papa.horizon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.OrdemDeServicoDao;
import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaOrdemServico;
import br.com.papa.horizon.entity.PecaUtilizada;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class OrcamentoController {
	OrcamentoDao orcamentoDao = new OrcamentoDao();

	/* novoOrcamento
	 * Primeira tela do orcamento,
	 * onde a recepcionista ira 
	 * localizar o cliente pelo CPF	 * 
	 */


	@RequestMapping("/novoOrcamento")
	public ModelAndView novoOrcamento(HttpSession session){
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();
		session.removeAttribute("orcamento");
		ModelAndView mv = new ModelAndView("cadastroOrcamento");
		return mv.addObject("especialidades", especialidades);
	}


	/*orcamento
	 * Este método trabalha em conjunto com o novoOrcamento
	 * para gerar um novo orcamento, aqui é onde localizamos as 
	 * categorias de trabalho, e os equipamentos vinculados
	 * ao cliente
	 */ 

	@RequestMapping("/orcamento")
	public ModelAndView orcamento(HttpSession session){	
		Orcamento orcamento = new Orcamento();
		orcamento = (Orcamento) session.getAttribute("orcamento");
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();

		ModelAndView mv = new ModelAndView("cadastroOrcamento");
		mv.addObject("especialidades", especialidades);
		mv.addObject("orcamento", orcamento);
		mv.addObject("equipamentos", orcamento.getCliente().getEquipamentos());
		return mv;
	}

	/*cadastraOrcamento
	 * Apos selecionada a categoria, o cliente
	 * e a máquina e o relato, este método
	 * irá salvar o orcamento no banco de dados	 * 
	 */

	@RequestMapping("/cadastraOrcamento")
	public String cadastraOrcamento (String relato,String equipamentoDanificado,String categoriaOrcamento, HttpSession session){
		try{
			Orcamento orcamento = new Orcamento();
			orcamento = (Orcamento) session.getAttribute("orcamento");
			orcamento.setRelato(relato);
			orcamento.setEquipamentoDanificado(equipamentoDanificado);
			orcamento.setEspecialidade(categoriaOrcamento);
			orcamento.setStatusOrcamento(StatusOrcamento.EM_ABERTO);
			orcamentoDao.adicionaOrcamento(orcamento.getCliente(), orcamento);

		}catch (Exception e){
			System.out.println("ERRO AO CADASTRAR O ORCAMENTO" + e);
		}
		return "redirect:novoOrcamento";	

	}

	/*adicionaOrcamento
	 * Este metodo garante que o orcamento
	 * seja adicionado ao cliente
	 * para que exista um registro do ID do 
	 * orcamento na tabela de CLIENTE	 * 
	 */
	@RequestMapping("/adicionaOrcamento")
	public String adicionaOrcamento (Cliente cliente, Orcamento orcamento){
		orcamentoDao.adicionaOrcamento(cliente, orcamento);
		return "orcamentoSucesso";
	}


	/*localizaCliente
	 * Metodo responsavel por localizar
	 * o cliente para o novo orcamento
	 * atraves do CPF digitado na tela
	 */

	@RequestMapping("/localizaCliente")
	public String localizaCliente(String cpf, HttpSession session){
		try{
			Orcamento orcamento = new Orcamento();
			Cliente cliente = new Cliente();
			cliente = orcamentoDao.procuraPorCpf(cpf);
			orcamento.setCliente(cliente);
			List<Equipamento> equipamentos = new ArrayList();
			equipamentos = cliente.getEquipamentos();
			if(cliente != null){
				session.setAttribute("orcamento", orcamento);
			}


		}catch(Exception e){
			System.out.println("ERRO AO RECUPERAR CLIENTE: " + e);

		}
		return "redirect:orcamento";			
	}

	/**
	 * Inicio dos métodos para MANTER ORCAMENTO
	 * Tela que o orcamentista irá abrir para
	 * finalizar o orcamento
	 */

	/*localizarOrcamento
	 * Irá recuperar todos os orcamentos
	 * cadastrados no banco	 * 
	 */
	@RequestMapping("/localizarOrcamento")
	public ModelAndView localizarOrcamento(){
		OrcamentoDao dao = new OrcamentoDao();	
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		orcamentos = dao.findAll();

		ModelAndView mv = new ModelAndView("localizarOrcamento");
		mv.addObject("orcamentos",orcamentos);
		return mv;
	}

	/*
	 * manterOrcamento
	 * Este serviço é responsavel pela
	 * manutenção do orcamento pelo técnico,
	 * ele recupera a lista de peças para utilizar, 
	 * armazena e exibi as peças utilizadas, assim como
	 * salva as alterações feita pelo tecnico no orcamento
	 */

	@RequestMapping("/manterOrcamento")
	public ModelAndView manterOrcamento(HttpSession session, String codOrcamento,Integer quantidade, Peca peca){
		Cliente cliente = new Cliente();	
		Orcamento orcamento = new Orcamento();
		PecasDao pecaDao = new PecasDao();
		PecaUtilizada pecaUtilizada = new PecaUtilizada();
		orcamento = orcamentoDao.findById(Long.parseLong(codOrcamento));


		try{
			if(peca.getDescricao() != null){
				peca = pecaDao.localizaPecaPorNome(peca.getDescricao());
				pecaUtilizada.setDescricao(peca.getDescricao());
				pecaUtilizada.setValor(peca.getValor());
				pecaUtilizada.setQuantidade(quantidade);
				
				orcamento = orcamentoDao.adicionarPeca(orcamento, pecaUtilizada);
			}

		}catch(Exception e){
			System.out.println("ERRO AO INCLUIR PECA: "+e);
		}

		List<Peca> pecas =  orcamentoDao.localizarPecas();
		List<PecaUtilizada> pecasInclusas = orcamentoDao.localizaPecasUtilizadas(Long.parseLong(codOrcamento));
		orcamento.setValorTotal(calculaTotal(pecasInclusas));
		cliente = orcamento.getCliente();	
		session.setAttribute("orcamento", orcamento);
		ModelAndView mv = new ModelAndView("editarOrcamento");
		mv.addObject("pecasInclusas", pecasInclusas);
		mv.addObject("pecas", pecas);
		mv.addObject("orcamento", orcamento);
		mv.addObject("cliente", cliente);

		return mv;
	}

	@RequestMapping("/finalizarOrcamento")
	public String finalizarOrcamento(HttpSession session, String observacao){
		Orcamento orcamento = new Orcamento();
		orcamento = (Orcamento) session.getAttribute("orcamento");
		orcamento.setObservacao(observacao);
		orcamento.setStatusOrcamento(StatusOrcamento.CONCLUIDO);
		orcamentoDao.atualizarOrcamento(orcamento);
		session.removeAttribute("orcamento");
		return "redirect:localizarOrcamento";
	} 

	@RequestMapping("/reprovarOrcamento")
	public String reprovarOrcamento(String codOrcamento){
		Orcamento orcamento = new Orcamento();
		orcamento = orcamentoDao.findById(Long.parseLong(codOrcamento));
		orcamento.setStatusOrcamento(StatusOrcamento.REPROVADO);
		orcamentoDao.atualizarOrcamento(orcamento);
		return "redirect:localizarOrcamento";
	}

	@RequestMapping("/aprovarOrcamento")
	public String aprovarOrcamento(String codOrcamento){
		OrdemDeServico novaOrdem = new OrdemDeServico();
		Orcamento orcamento = new Orcamento();
		orcamento = orcamentoDao.findById(Long.parseLong(codOrcamento));
		orcamento.setStatusOrcamento(StatusOrcamento.APROVADO);
		orcamentoDao.atualizarOrcamento(orcamento);

		novaOrdem = gerarOrdemDeServico(orcamento, novaOrdem);

		return "redirect:localizarOrcamento";
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
		novaOrdem.setEquipamentoDanificado(orcamento.getEquipamentoDanificado());
		novaOrdem.setEspecialidade(orcamento.getEspecialidade());
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

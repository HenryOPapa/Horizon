package br.com.papa.horizon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.PecaUtilizadaDao;
import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaUtilizada;

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
	public ModelAndView manterOrcamento(HttpSession session, String codOrcamento, Peca peca){
		Cliente cliente = new Cliente();	
		Orcamento orcamento = new Orcamento();
		PecasDao pecaDao = new PecasDao();
		


		try{
			if(peca.getDescricao() != null){
				orcamento = orcamentoDao.findById(Long.parseLong(codOrcamento));
				peca = pecaDao.localizaPecaPorNome(peca.getDescricao());
				PecaUtilizada pecaUtilizada = new PecaUtilizada();
				pecaUtilizada.setDescricao(peca.getDescricao());
				pecaUtilizada.setValor(peca.getValor());
				orcamento = orcamentoDao.adicionarPeca(orcamento, pecaUtilizada);		
			}

		}catch(Exception e){
			System.out.println("ERRO AO INCLUIR PECA: "+e);
		}
		
		orcamento = orcamentoDao.findById(Long.parseLong(codOrcamento));
		List<Peca> pecas =  orcamentoDao.localizarPecas();
		List<PecaUtilizada> pecasInclusas = orcamentoDao.localizaPecasUtilizadas(Long.parseLong(codOrcamento));
		cliente = orcamento.getCliente();	
		ModelAndView mv = new ModelAndView("editarOrcamento");
		mv.addObject("pecasInclusas", pecasInclusas);
		mv.addObject("pecas", pecas);
		mv.addObject("orcamento", orcamento);
		mv.addObject("cliente", cliente);

		return mv;
	}






}

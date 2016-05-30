package br.com.papa.horizon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class OrcamentoController {
	OrcamentoDao orcamentoDao = new OrcamentoDao();
	
	/*
	  Metodos responsaveis por cadastrar e manter 
	  o cadastro de um novo orcamento	 
	 */
	

	@RequestMapping("/novoOrcamento")
	public ModelAndView novoOrcamento(HttpSession session){
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();
		session.removeAttribute("orcamento");
		ModelAndView mv = new ModelAndView("cadastroOrcamento");
		return mv.addObject("especialidades", especialidades);
	}

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
	

	@RequestMapping("/adicionaOrcamento")
	public String adicionaOrcamento (Cliente cliente, Orcamento orcamento){
		orcamentoDao.adicionaOrcamento(cliente, orcamento);
		return "orcamentoSucesso";
	}
	
	
	/*
	 * Metodo responsavel por localizar
	 * o cliente para o novo orcamento
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
	
	@RequestMapping("/localizarOrcamento")
	public ModelAndView localizarOrcamento(){
		OrcamentoDao dao = new OrcamentoDao();	
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		orcamentos = dao.findAll();
		ModelAndView mv = new ModelAndView("localizarOrcamento");
		mv.addObject("orcamentos",orcamentos);
		return mv;
	}
	
	@RequestMapping("/manterOrcamento")
	public ModelAndView manterOrcamento(Orcamento orcamento){
		Cliente cliente = new Cliente();		
		PecasDao pDao = new PecasDao();
		OrcamentoDao dao = new OrcamentoDao();
		orcamento = dao.findById(orcamento.getId_orcamento());
		List<Peca> pecas =  pDao.findAll();
		cliente = orcamento.getCliente();
		ModelAndView mv = new ModelAndView("editarOrcamento");
		mv.addObject("orcamento", orcamento);
		mv.addObject("cliente", cliente);
		mv.addObject("pecas", pecas);
		
		return mv;
	}
	
	

}

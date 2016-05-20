package br.com.papa.horizon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class OrcamentoController {
	OrcamentoDao orcamentoDao = new OrcamentoDao();

	@RequestMapping("/novoOrcamento")
	public ModelAndView novoOrcamento(HttpSession session){
		//		OrcamentoDao dao = new OrcamentoDao();
		//		List<Especialidade> especialidades = (List<Especialidade>) dao.localizaEspecialidade();
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();
		session.removeAttribute("orcamento");
		ModelAndView mv = new ModelAndView("cadastroOrcamento");
		return mv.addObject("especialidades", especialidades);
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

	@RequestMapping("/cadastraOrcamento")
	public String cadastraOrcamento (Equipamento teste,String relato,String equipamentoDanificado, HttpSession session){
		try{
			Orcamento orcamento = new Orcamento();
			orcamento = (Orcamento) session.getAttribute("orcamento");
			orcamento.setRelato(relato);
			orcamento.setEquipamentoDanificado(equipamentoDanificado);
			orcamentoDao.adicionaOrcamento(orcamento.getCliente(), orcamento);

		}catch (Exception e){
			System.out.println("ERRO AO CADASTRAR O ORCAMENTO" + e);
		}
		return "redirect:novoOrcamento";	

	}

	@RequestMapping("/adicionaOrcamento")
	public String adicionaOrcamento (Cliente cliente, Orcamento orcamento){
		orcamentoDao.adicionaOrcamento(cliente, orcamento);
		return "orcamentoSucesso";
	}

}

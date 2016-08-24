package br.com.papa.horizon.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.ClienteDao;
import br.com.papa.horizon.dao.EquipamentoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;

@Controller
public class EquipamentoController {
	
//	@RequestMapping("/adicionaEquipamento")
//	public String adicionaEquipamento(Cliente cliente, Equipamento equipamento){
//		EquipamentoDao dao = new EquipamentoDao();
//		dao.adicionarEquipamento(cliente, equipamento);
//		return "redirect:listaClientes";
//		
//	}

	@RequestMapping("/adicionarEquipamentoAoCliente")
	public ModelAndView adicionarEquipamentoAoCliente(Cliente cliente){
		
		EquipamentoDao dao = new EquipamentoDao();
		Cliente cliente2 = new Cliente();
		cliente2 = dao.localizarDonoEquipamento(cliente);
		
		ModelAndView mv = new ModelAndView("cadastroEquipamento");
		mv.addObject("cliente", cliente);	
		 
		return mv;
	}

}

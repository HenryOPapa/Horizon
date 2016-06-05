package br.com.papa.horizon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.PecasDao;
import br.com.papa.horizon.entity.Peca;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class PecaController {
	
	
	
	@RequestMapping("/cadastroPeca")
	public ModelAndView cadastroEspecialidade(){
		PecasDao dao = new PecasDao();
		List<Peca> pecas = dao.findAll();		
		ModelAndView mv = new ModelAndView("cadastroPeca");
		mv.addObject("pecas",pecas);
		
		return mv;
	}
	
	@RequestMapping("/novaPeca")
	public String adicionaEspecialidade(Peca peca){
		PecasDao dao = new PecasDao();
		dao.save(peca);
		
		return "redirect:cadastroPeca";		
		
	}
	
	

}

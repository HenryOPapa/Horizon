package br.com.papa.horizon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.entity.Especialidade;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class EspecialidadeController {
	
	
	
	@RequestMapping("/cadastroEspecialidade")
	public ModelAndView cadastroEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();		
		ModelAndView mv = new ModelAndView("cadastroEspecialidade");
		mv.addObject("especialidades",especialidades);
		
		return mv;
	}
	
	@RequestMapping("/novaEspecialidade")
	public String adicionaEspecialidade(Especialidade especialidade){
		EspecialidadeDao dao = new EspecialidadeDao();
		dao.save(especialidade);
		
		return "redirect:cadastroEspecialidade";		
		
	}
	
	

}

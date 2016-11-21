package br.com.papa.horizon.controller;

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

import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.vo.DadosPadraoVO;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("especialidade")
public class EspecialidadeController {
	
	private EspecialidadeDao especialidadeDao;
		
	@RequestMapping
	public ModelAndView cadastroPeca(HttpSession session){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		especialidadeDao = new EspecialidadeDao();
		List<Especialidade> especialidades = especialidadeDao.findAll();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) session.getAttribute("dadosPadraoVO");
		

		
		retorno.put("especialidades", especialidades);
		retorno.put("dadosPadraoVO", dadosPadraoVO);
		if("total".equals(dadosPadraoVO.getUsuario().getNivelAcesso())){
			return new ModelAndView("especialidadeCadastro").addObject("result",
					gson.toJson(retorno));			
		}else{
			return new ModelAndView("onlyAdmin").addObject("result",gson.toJson(retorno));			
		}
		

	}
	
	@RequestMapping(value = "/cadastrarEspecialidade", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarPeca(@RequestBody Especialidade especialidade , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		especialidadeDao = new EspecialidadeDao();
		
		try{
			especialidadeDao.save(especialidade);
			return new ResponseEntity<String>(gson.toJson(especialidade), HttpStatus.OK);							
			
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVA ESPECIALIDADE: " +e);
			return new ResponseEntity<String>(gson.toJson(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	

}

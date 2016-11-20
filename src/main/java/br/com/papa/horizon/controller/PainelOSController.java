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

import br.com.papa.horizon.dao.OrdemDeServicoDao;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.OrdemDeServicoVO;

import com.google.gson.Gson;
@Controller
@RequestMapping("painelOS")
public class PainelOSController {
	
	OrdemDeServicoDao ordemDeServicoDao;

	/**
	 * Método que ira carregar todas as OS na tela
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping
	public ModelAndView cadastroCliente(HttpSession session){
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		ordemDeServicoDao = new OrdemDeServicoDao();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) session.getAttribute("dadosPadraoVO"); //Recuperando o funcionario e o usuario da sessao
		List<OrdemDeServicoVO> ordensDeServico = new ArrayList<OrdemDeServicoVO>();
		
		try{	
			ordensDeServico = ordemDeServicoDao.localizarOS();
		}catch(Exception e){
			System.out.println(""+e);
		}
		
		retorno.put("ordensDeServico", ordensDeServico);
		retorno.put("dadosPadraoVO", dadosPadraoVO);
		return new ModelAndView("painelOS").addObject("result",
				gson.toJson(retorno));
	}


	@RequestMapping(value = "/iniciarTarefa", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> iniciarTarefa(@RequestBody OrdemDeServicoVO ordemDeServicoVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		OrdemDeServico ordemDeServico = new OrdemDeServico();
		ordemDeServicoDao = new OrdemDeServicoDao();
		ordemDeServico = ordemDeServicoDao.findById(ordemDeServicoVO.getIdOrdemServico());

		try{
			ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.WIP);
			ordemDeServicoDao.update(ordemDeServico);
		}catch(Exception e){
			System.out.println("ERRO AO INICIAR TAREFA" +e);
			return new ResponseEntity<String>(gson.toJson(null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	




	

	

}

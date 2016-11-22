package br.com.papa.horizon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.dao.OrdemDeServicoDao;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;
import br.com.papa.horizon.vo.DadosPadraoVO;
import br.com.papa.horizon.vo.FuncionarioVO;
import br.com.papa.horizon.vo.OrcamentoVO;
import br.com.papa.horizon.vo.OrdemDeServicoVO;

import com.google.gson.Gson;


@Controller
@RequestMapping("relatorios")
public class RelatoriosController {
	
	FuncionarioDao funcionarioDao;
	
	@RequestMapping
	public ModelAndView menu(HttpSession httpSession) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) httpSession.getAttribute("dadosPadraoVO");
		funcionarioDao = new FuncionarioDao();
		List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();
		
		
		try{
			funcionarios = funcionarioDao.localizarFuncionarios();
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR FUNCIONARIOS "+e);
		}
		
		retorno.put("dadosPadraoVO", dadosPadraoVO);
		retorno.put("funcionarios", funcionarios);
		return new ModelAndView("relatorios").addObject("result",
				gson.toJson(retorno));
	}
	
	
	@RequestMapping(value = "/atualizarFiltroFuncionario", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> atualizarFiltroFuncionario(@RequestBody Funcionario funcionario,
														@RequestParam (value="idTarefa") int idTarefa,
														HttpSession httpSession) throws Exception { 
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) httpSession.getAttribute("dadosPadraoVO");
		FuncionarioDao dao = new FuncionarioDao();
		OrcamentoDao orcamentoDao = new OrcamentoDao();
		OrdemDeServicoDao ordemDeServicoDao = new OrdemDeServicoDao();
		
		
		if(idTarefa < 3){
			Orcamento orcamento = new Orcamento();
			if(idTarefa == 0){
				orcamento.setStatusOrcamento(StatusOrcamento.EM_ABERTO);
			}else if(idTarefa == 1){
				orcamento.setStatusOrcamento(StatusOrcamento.APROVADO);
			}else{
				orcamento.setStatusOrcamento(StatusOrcamento.REPROVADO);
			}
			List<OrcamentoVO> resultadoPesquisa = new ArrayList<OrcamentoVO>();
			
			try{
				resultadoPesquisa = orcamentoDao.getOrcamentosConcluidos(orcamento.getStatusOrcamento());
				
			}catch(Exception e){
				System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
			}
			int x = 1;
			retorno.put("x", x);
			retorno.put("resultadoPesquisa", resultadoPesquisa);

		}else{
			
			OrdemDeServico ordemDeServico = new OrdemDeServico();
			if(idTarefa == 3){
				ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.TODO);
			}else if(idTarefa == 4){
				ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.WIP);
			}else{
				ordemDeServico.setStatusOrdemServico(StatusOrdemDeServico.DONE);
			}
			
			List<OrdemDeServicoVO> resultadoPesquisa = new ArrayList<OrdemDeServicoVO>();

			try{
				
				resultadoPesquisa = ordemDeServicoDao.localizarTODO(ordemDeServico.getStatusOrdemServico());
				
			}catch(Exception e){
				System.out.println("ERRO AO LOCALIZAR ORDEM DE SERVICO: "+e);
			}
			
			int x = 2;
			retorno.put("x", x);
			retorno.put("resultadoPesquisa", resultadoPesquisa);
		}
		
		
		
		
		retorno.put("dadosPadraoVO", dadosPadraoVO);

		
		return new ResponseEntity<String>(gson.toJson(retorno), HttpStatus.OK);							
	}
	
	
	
	
	
	
	private List<OrdemDeServicoVO> populateVO(List<OrdemDeServico> resultList) throws ParseException {
		SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
		List<OrdemDeServicoVO> listOS = new ArrayList<OrdemDeServicoVO>();
		OrdemDeServicoVO ordemDeServicoVO;
		
		for(OrdemDeServico os : resultList ){
			ordemDeServicoVO = new OrdemDeServicoVO();
			ordemDeServicoVO.setIdOrdemServico(os.getIdOrdemServico());
			ordemDeServicoVO.setEspecialidade(os.getEspecialidade().getDescricao());
			ordemDeServicoVO.setObservacao(os.getObservacao());
			ordemDeServicoVO.setPontos(os.getPontos());
			ordemDeServicoVO.setRelato(os.getRelato());
			ordemDeServicoVO.setStatusOrdemServico(os.getStatusOrdemServico());
			ordemDeServicoVO.setIdFuncionario(os.getFuncionario().getId());
			ordemDeServicoVO.setDataCriacao(os.getDataCriacao());
			listOS.add(ordemDeServicoVO);
		}
		
		return listOS;
	}
	
		

}

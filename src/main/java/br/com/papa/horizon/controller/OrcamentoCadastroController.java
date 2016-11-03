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

import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.vo.ClienteVO;
import br.com.papa.horizon.vo.EquipamentoVO;
import br.com.papa.horizon.vo.OrcamentoAuxiliarVO;

import com.google.gson.Gson;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
@RequestMapping("novoOrcamento")
public class OrcamentoCadastroController {
	OrcamentoDao orcamentoDao = new OrcamentoDao();
	Usuario usuario;

	@RequestMapping
	public ModelAndView cadastroOrcamento(HttpSession session){
		Gson gson = new Gson();
		usuario = (Usuario) session.getAttribute("usuario");
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Especialidade> especialidades = new ArrayList<Especialidade>();

		try{	
			especialidades = (List<Especialidade>) orcamentoDao.localizaEspecialidade();
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ESPECIALIDADES: "+e);
		}
		retorno.put("especialidades", especialidades);
		return new ModelAndView("orcamentoCadastro").addObject("result",
				gson.toJson(retorno));
	}


	@RequestMapping(value = "/localizarCliente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> localizarCliente(@RequestBody Cliente cliente , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		usuario = new Usuario();
		usuario = (Usuario) httpSession.getAttribute("usuario");
		Map<String, Object> result = new HashMap<String, Object>();

		try{
			cliente = orcamentoDao.procuraPorCpf(cliente.getCpf());
			httpSession.setAttribute("cliente", cliente);

		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR CLIENTE: " +e);
			return new ResponseEntity<String>(gson.toJson(cliente), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		result.put("usuario", usuario);
		result.put("cliente", createClienteVO(cliente));
		result.put("equipamentos", createEquipamentoVO(cliente.getEquipamentos()));

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}



	@RequestMapping(value = "/salvarOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> salvarOrcamento(@RequestBody OrcamentoAuxiliarVO orcamentoAuxiliarVO, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		Cliente cliente = new Cliente();
		cliente = (Cliente) httpSession.getAttribute("cliente");
		Orcamento orcamento = orcamentoAuxiliarVO.getOrcamento();
		cliente.addOrcamento(orcamento);
		orcamento.setEquipamento(orcamentoAuxiliarVO.getEquipamento());
		orcamento.setEspecialidade(orcamentoAuxiliarVO.getEspecialidade());
		orcamento.setStatusOrcamento(StatusOrcamento.EM_ABERTO);
		


		try{
			orcamentoDao.update(orcamento);
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR ORCAMENTO: " +e);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		httpSession.removeAttribute("cliente");

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}


	/***************************************************************************************************************
	 * *************************************************************************************************************
	 * ***************************************METODOS DE POPULACAO DOS VOs******************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 */





	public ClienteVO createClienteVO(Cliente cliente){
		ClienteVO clienteVo = new ClienteVO();
		clienteVo.setId(cliente.getId_cliente());
		clienteVo.setCep(cliente.getCep());
		clienteVo.setCidade(cliente.getCidade());
		clienteVo.setCpf(cliente.getCpf());
		clienteVo.setDataNascimento(cliente.getDataNascimento());
		clienteVo.setEmail(cliente.getEmail());
		clienteVo.setEstado(cliente.getEstado());
		clienteVo.setLogradouro(cliente.getLogradouro());
		clienteVo.setNome(cliente.getNome());
		clienteVo.setTelefone(cliente.getTelefone());
		return clienteVo;
	}

	private List<EquipamentoVO> createEquipamentoVO(List<Equipamento> equipamentos) {
		List<EquipamentoVO> equipamentosVo = new ArrayList<EquipamentoVO>();
		EquipamentoVO equipamentoVo = new EquipamentoVO();
		for(Equipamento item : equipamentos){
			equipamentoVo = new EquipamentoVO();
			equipamentoVo.setId_equipamento(item.getId_equipamento());
			equipamentoVo.setMarca(item.getMarca());
			equipamentoVo.setModelo(item.getModelo());
			equipamentoVo.setNumeroSerie(item.getNumeroSerie());
			equipamentoVo.setTipoEquipamento(item.getTipoEquipamento());
			equipamentosVo.add(equipamentoVo);
		}
		return equipamentosVo;
	}



}

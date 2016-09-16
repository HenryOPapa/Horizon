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

import br.com.papa.horizon.dao.ClienteDao;
import br.com.papa.horizon.dao.EquipamentoDao;
import br.com.papa.horizon.dao.EspecialidadeDao;
import br.com.papa.horizon.dao.OrcamentoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.GsonExclusionStrategy;

import com.google.gson.Gson;



@Controller
@RequestMapping("manutencaoOrcamento")
public class ManutencaoOrcamentoController {
	OrcamentoDao orcamentoDao;
	Usuario usuario;
	
	@RequestMapping
	public ModelAndView manutencaoOrcamento(HttpSession session){
		Gson gson = new Gson();
		usuario = (Usuario) session.getAttribute("usuario");
		orcamentoDao = new OrcamentoDao();
		Map<String, Object> retorno = new HashMap<String, Object>();
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		
		try{		
		 orcamentos =  orcamentoDao.findAll();
		}catch(Exception e){
			System.out.println("ERRO AO CONSULTAR ORCAMENTOS: "+e);
		}
		retorno.put("orcamentos", orcamentos);
		return new ModelAndView("manutencaoOrcamento").addObject("result",
				gson.toJson(retorno));
	}
	
	
	@RequestMapping(value = "/detalharOrcamento", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> detalharOrcamento(@RequestBody Orcamento orcamento , HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		gson = GsonExclusionStrategy.createGsonFromBuilder(
				new GsonExclusionStrategy(Equipamento.class));
		usuario = new Usuario();
		usuario = (Usuario) httpSession.getAttribute("usuario");
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();
		Map<String, Object> result = new HashMap<String, Object>();
		

		try{
			result = localizarItensDeOrcamento(orcamento.getIdCliente(),
												orcamento.getIdEquipamento(),
												orcamento.getIdEspecialidade(),
												result);
			
		}catch(Exception e){
			System.out.println("ERRO AO LOCALIZAR ITENS DE ORCAMENTO: " +e);
			return new ResponseEntity<String>(gson.toJson(orcamento), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		result.put("orcamento", orcamento);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	
	/*
	 * ###############################################################################################
	 * ################################### INICIO METODOS PRIVADOS ###################################
	 * ############################################################################################### 
	 */
	
	public Map<String, Object> localizarItensDeOrcamento (Long idCliente,
															Long idEquipamento,
															Long idEspecialidade,
															Map<String, Object> result){//Este método é chamado pelo método detalharOrcamento na linha 66
		
		//Aqui encontramos o cliente pelo id
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.findById(idCliente);
		cliente.setEquipamentos(new ArrayList());
		result.put("cliente", cliente);
		
		//Aqui ta na cara que encontramos o equipamento.... pelo id 'Óbviu'
		EquipamentoDao equipamentoDao = new EquipamentoDao();
		Equipamento equipamento = equipamentoDao.findById(idEquipamento);
		equipamento.setCliente((Cliente) new Object());
		result.put("equipamento", equipamento);
		
		//Se eu tiver que falar o que estamos procurando aqui, desiste da vida meu amigo(a)
		EspecialidadeDao especialidadeDao = new EspecialidadeDao();
		Especialidade especialidade = especialidadeDao.findById(idEspecialidade);
		result.put("especialidade", especialidade);
		//é especialidade ¬¬
		
		return result; //aqui retornamos o result pro metodo detalharOrcamento, cliente, equipamento e especialidade ta tudo junto e misturado nele
		
	}
	




}

package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import br.com.papa.horizon.dao.UsuarioDao;
import br.com.papa.horizon.entity.Especialidade;
//import br.com.papa.horizon.entity.Contato;
//import br.com.papa.horizon.entity.Documento;
//import br.com.papa.horizon.entity.Endereco;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.util.Email;
import br.com.papa.horizon.vo.DadosPadraoVO;

import com.google.gson.Gson;


/**
 * 
 * 
 * Método responsavel por abrir a tela de funcionario
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("cadastroFuncionario")//url da tela http://localhost:8080/horizon/cadastroFuncionario
public class FuncionarioController extends Exception{

	private FuncionarioDao funcionarioDao; //Novo objeto de banco

	@RequestMapping
	public ModelAndView cadastroFuncionario(HttpSession session){//Método ModelAndView para direcionar para tela de cadastro
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		DadosPadraoVO dadosPadraoVO = (DadosPadraoVO) session.getAttribute("dadosPadraoVO");
		
		funcionarioDao = new FuncionarioDao();
		List<Especialidade> especialidades = funcionarioDao.localizarEspecialidade();
		retorno.put("especialidades", especialidades); //enviando lista de especialidade para tela
		
		if("total".equals(dadosPadraoVO.getUsuario().getNivelAcesso())){
			return new ModelAndView("funcionario/funcionarioCadastro").addObject("result",gson.toJson(retorno)); //retorno para tela	
		}else{
			return new ModelAndView("onlyAdmin").addObject("result",gson.toJson(retorno));		//bloqueia caso nao seja adm
		}
	}

/**
 * Método desparado pelo botao cadastrar na tela de funcionario,
 * recebe o funcionario direto da tela ja preenchido e salva no banco na linha 80
 * 
 * @param funcionario
 * @param nivelAcesso
 * @param httpSession
 * @return
 * @throws Exception
 */
	@RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cadastrarFuncionario(@RequestBody Funcionario funcionario , @RequestParam(value = "nivelAcesso") String nivelAcesso, HttpSession httpSession) throws Exception { 

		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		funcionarioDao = new FuncionarioDao();//declara objeto de banco

		try{
			funcionarioDao.save(funcionario);//salva o usuario
			criarUsuario(funcionario, nivelAcesso);//chama o metodo da linha 100 para criar usuario
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR NOVO FUNCIONARIO: " +e);
			return new ResponseEntity<String>(gson.toJson(funcionario), HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}




	/*
	 * O metodo 'criarUsuario' tem como objetivo
	 * coletar o nome do funcionario adicionado
	 * e criar automaticamente um usuario de acesso
	 */
	public void criarUsuario(Funcionario funcionario, String nivelAcesso){

		UUID uuid = UUID.randomUUID();  //gera um randomizador por assim dizer
		Usuario usuario = new Usuario(); //novo objeto de usuario
		UsuarioDao dao = new UsuarioDao();
		Email email = new Email();
		String[] split = funcionario.getNome().split(" "); //Dividindo o nome completa em uma lista com nomes separados
		if (split != null && split.length > 0){
			String login =  split[0] + "." + split[split.length - 1];//Pegando o primeiro e o ultimo para criar o usuario
			login = login.toLowerCase(); //deixando tudo minusculo
			usuario.setLogin(login);
			usuario.setSenha(uuid.toString().substring(0,6));
			usuario.setNivelAcesso(nivelAcesso);
			usuario.setIdFuncionario(funcionario.getId());
			usuario.setPrimeiroAcesso(true);
		}

		try{ 
			dao.save(usuario);//salvando o funcionario
			email.enviaEmailCadastroFuncionario(usuario.getSenha(), usuario.getLogin(), funcionario);
		}catch(Exception e){
			System.out.println("ERRO AO CADASTRAR USUARIO: " + e);
		}
	}

}

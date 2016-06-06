package br.com.papa.horizon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.dao.ClienteDao;
//import br.com.papa.horizon.dao.EnderecoDao;
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
//import br.com.papa.horizon.entity.Contato;
//import br.com.papa.horizon.entity.Documento;
//import br.com.papa.horizon.entity.Endereco;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Controller
public class ClienteController {

	@RequestMapping("/cadastroCliente")//URL de Acesso 
	public String clienteCadastro(){

		return "menu";
	}

	@RequestMapping("/listaClientes")//URL de Acesso 
	public ModelAndView lista(){

		ClienteDao dao = new ClienteDao();
		List<Cliente> clientes = dao.findAll();

		ModelAndView mv = new ModelAndView("listaCliente");
		mv.addObject("clientes", clientes);

		return mv;
	}


	@RequestMapping("/alterarDadosCliente")//URL de Acesso
	public ModelAndView alterarDadosCliente(Cliente cliente){

		ClienteDao dao = new ClienteDao();
		Long id = cliente.getId_cliente();
		Cliente clienteSelecionado = dao.findById(id);
		ModelAndView mv = new ModelAndView("alteraDadosCliente");
		mv.addObject("cliente", clienteSelecionado);	


		return mv;
	}

	/*
	 * Os métodos abaixo são chamados 
	 * traves da tag "action" na jsp, 
	 * não sendo possível acessá-lo via url
	 */
	@RequestMapping("/adicionaCliente")//Método invocado pelo form action da jsp
	public String adiciona(Cliente cliente){

		ClienteDao dao = new ClienteDao();
		dao.save(cliente);

		return "contaAdicionadaSucesso";
	}




	@RequestMapping("/removeCliente")//Método invocado pelo form action da jsp
	public String remover(Cliente cliente){

		ClienteDao dao = new ClienteDao();
		dao.delete(cliente);

		return "redirect:listaClientes";
	}

	@RequestMapping("/atualizarCliente")//Método invocado pelo form action da jsp
	public String atualizar(Cliente cliente){

		ClienteDao dao = new ClienteDao();
		//		Cliente clienteAtualizado = dao.findById(cliente.getId_cliente());
		//		
		//		clienteAtualizado.setNome(cliente.getNome());
		//		clienteAtualizado.setDataNascimento(cliente.getDataNascimento());

		dao.update(cliente);
		return "redirect:listaClientes";
	}




	/*	@RequestMapping("/listaClientes")//URL de Acesso 
	public void atualizar(){

		ClienteDao dao = new ClienteDao();
		List<Cliente> clientes = dao.findAll();

		ModelAndView mv = new ModelAndView("listaCliente");
		mv.addObject("clientes", clientes);

	}
	 */	


}

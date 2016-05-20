package br.com.papa.horizon.dao;


import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;

public class OrcamentoDao extends GenericDao<Orcamento>{

	public OrcamentoDao() {
		super(Orcamento.class);
	}
	
	public Cliente procuraPorCpf(String cpf){
		ClienteDao dao = new ClienteDao();
		return dao.procuraPorCpf(cpf);
	}
		
	public void adicionaOrcamento(Cliente cliente, Orcamento orcamento){
		ClienteDao dao = new ClienteDao();
		cliente = dao.findById(cliente.getId_cliente());
		cliente.addOrcamento(orcamento);
		dao.update(cliente);
		
	}
	
	public Especialidade localizaEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();		
		return (Especialidade) especialidades;
	}
}

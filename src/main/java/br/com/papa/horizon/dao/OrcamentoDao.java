package br.com.papa.horizon.dao;


import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaUtilizada;

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
	
	
	/*
	 * adicionarPeca, irá receber o atual orcamento, a peca escolhida pelo tecnico
	 * e irá salvar a mesma dentro do orcamenoto
	 */
	
	public Orcamento adicionarPeca(Orcamento orcamento, PecaUtilizada pecaUtilizada){
		orcamento.addPeca(pecaUtilizada);
		update(orcamento);
		return orcamento;	
	}
	
	public Orcamento atualizarOrcamento(Orcamento orcamento){
		update(orcamento);
		return orcamento;	
	}
	
	public Especialidade localizaEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		List<Especialidade> especialidades = dao.findAll();		
		return (Especialidade) especialidades;
	}
	
	public List<PecaUtilizada> localizaPecasUtilizadas(Long id_orcamento){
		PecaUtilizadaDao dao = new PecaUtilizadaDao();
		return dao.recuperarPecas(id_orcamento);
		
	}
	
	public List<Peca> localizarPecas(){
		PecasDao dao = new PecasDao();
		List<Peca> pecas = dao.findAll();		
		return pecas;
	}
	
}

package br.com.papa.horizon.dao;

import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaUtilizada;

/**
 * 
 * @author Henry O' Papa
 *
 */

public class OrcamentoDao extends GenericDao<Orcamento>{

	public OrcamentoDao() {
		super(Orcamento.class);
	}

	public Cliente procuraPorCpf(String cpf){
		ClienteDao dao = new ClienteDao();
		return dao.achaCliente(cpf);
	}
	
	public void atualizaCliente(Cliente cliente){
		ClienteDao dao = new ClienteDao();
		dao.update(cliente);
	}
	
	/**
	 * procuraEspecialidade
	 * Método responsavel por localizar a especialidade
	 * pelo ID
	 * @param idEspecialidade
	 * @return
	 */
	
	public Especialidade procuraEspecialidade(Long idEspecialidade){
		EspecialidadeDao dao = new EspecialidadeDao();
		return dao.findEspecialidade(idEspecialidade);
	}
	
	/**
	 * procuraEquipamento
	 * Método responsavel por localizar o equipamento
	 * pelo ID
	 * @param numSerie
	 * @return
	 */
	
	public Equipamento procuraEquipamento(String numSerie){
		EquipamentoDao dao = new EquipamentoDao();
		return dao.findByNumSerie(numSerie);
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

	public List<Especialidade> localizaEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		return dao.findAll();
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
	
	public PecaUtilizada localizarPecaUnica(Long idPeca){
		PecasDao dao = new PecasDao();
		Peca peca = dao.findById(idPeca);
		PecaUtilizada pecaUtilizada = new PecaUtilizada();
		pecaUtilizada.setDescricao(peca.getDescricao());
		pecaUtilizada.setValor(peca.getValor());
		return pecaUtilizada;
	}


}

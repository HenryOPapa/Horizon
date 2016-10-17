package br.com.papa.horizon.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.PecaUtilizada;
import br.com.papa.horizon.entity.Servico;
import br.com.papa.horizon.util.Email;
import br.com.papa.horizon.util.Enum.StatusOrcamento;

/**
 * 
 * @author Henry O' Papa
 *
 */
@Repository
@Transactional(readOnly = true)
public class OrcamentoDao extends GenericDao<Orcamento>{

	public OrcamentoDao() {
		super(Orcamento.class);
	}

	public Cliente procuraPorCpf(String cpf){
		ClienteDao dao = new ClienteDao();
		return dao.procuraPorCpf(cpf);
	}
	
	public void atualizaCliente(Cliente cliente){
		ClienteDao dao = new ClienteDao();
		dao.update(cliente);
	}
	
	public Peca localizaPecaUnica(Long id){
		PecasDao dao = new PecasDao();
		return dao.findById(id);
		
	}
	
	public Servico localizarServicoUnico(Long id){
		ServicoDao dao = new ServicoDao();
		return dao.findById(id);
	}
	
	public Especialidade procuraEspecialidade(Long idEspecialidade){
		EspecialidadeDao dao = new EspecialidadeDao();
		return dao.findEspecialidade(idEspecialidade);
	}
		
	public Equipamento procuraEquipamento(String numSerie){
		EquipamentoDao dao = new EquipamentoDao();
		return dao.findByNumSerie(numSerie);
	}

	public Orcamento atualizarOrcamento(Orcamento orcamento){
		update(orcamento);
		return orcamento;	
	}

	public List<Especialidade> localizaEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		return dao.findAll();
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
	
	public PecaUtilizada localizarServico(Long id){
		ServicoDao dao = new ServicoDao();
		Servico servico = dao.findById(id);
		PecaUtilizada itemDeServico = new PecaUtilizada();
		itemDeServico.setDescricao(servico.getDescricao());
		itemDeServico.setValor(servico.getValor());
		return itemDeServico;
	}
	
	public void salvarItensDeOrcamento(List<PecaUtilizada> itensDeOrcamento){
		PecaUtilizadaDao dao = new PecaUtilizadaDao();
		dao.saveList(itensDeOrcamento);
	}
	
	public void enviarEmailCliente(Cliente cliente, Orcamento orcamento) throws EmailException{
		Email email = new Email();
		email.enviaEmailOrcamento(cliente.getEmail(), orcamento);
	}
	

	public List<Orcamento> getOrcamentosConcluidos(StatusOrcamento statusOrcamento){
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Orcamento> criteria = builder.createQuery(Orcamento.class);
		Root<Orcamento> from = criteria.from(Orcamento.class);
		criteria.where(builder.equal(from.get("statusOrcamento"), statusOrcamento));
		Query query = getEntityManager().createQuery(criteria);
		return query.getResultList();
		
	}
	
	public List<PecaUtilizada> getItensDeServico(Long idOrcamento){
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PecaUtilizada> criteria = builder.createQuery(PecaUtilizada.class);
		Root<PecaUtilizada> from = criteria.from(PecaUtilizada.class);
		criteria.where(builder.equal(from.get("idOrcamento"), idOrcamento));
		Query query = getEntityManager().createQuery(criteria);
		return query.getResultList();
		
	}

}

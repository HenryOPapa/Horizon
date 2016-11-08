package br.com.papa.horizon.dao;

import java.util.ArrayList;
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
import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.entity.Servico;
import br.com.papa.horizon.util.Email;
import br.com.papa.horizon.util.Enum.StatusOrcamento;
import br.com.papa.horizon.vo.OrcamentoVO;

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
	
	public List<OrcamentoVO> buscarOrcamentos(){
		List<Orcamento> orcamento = findAll();
		return populateOrcamentoVO(orcamento);
	}

	public Cliente procuraPorCpf(String cpf){
		ClienteDao dao = new ClienteDao();
		return dao.findByCPF(cpf);
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
	
	public void salvarItensDeOrcamento(List<ItensOrcamento> itensOrcamento){
		ItensOrcamentoDao dao = new ItensOrcamentoDao();
		dao.saveList(itensOrcamento);
	}
	
	public ItensOrcamento localizarPecaUnica(Long idPeca){
		PecasDao dao = new PecasDao();
		Peca peca = dao.findById(idPeca);
		ItensOrcamento pecaUtilizada = new ItensOrcamento();
		pecaUtilizada.setDescricao(peca.getDescricao());
		pecaUtilizada.setValor(peca.getValor());
		return pecaUtilizada;
	}
	
	public ItensOrcamento localizarServico(Long id){
		ServicoDao dao = new ServicoDao();
		Servico servico = dao.findById(id);
		ItensOrcamento itemDeServico = new ItensOrcamento();
		itemDeServico.setDescricao(servico.getDescricao());
		itemDeServico.setValor(servico.getValor());
		return itemDeServico;
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
	
	public List<ItensOrcamento> getItensDeServico(Long idOrcamento){
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ItensOrcamento> criteria = builder.createQuery(ItensOrcamento.class);
		Root<ItensOrcamento> from = criteria.from(ItensOrcamento.class);
		criteria.where(builder.equal(from.get("idOrcamento"), idOrcamento));
		Query query = getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	
	/**
	 * Métodos de populacao
	 */
	
	public List<OrcamentoVO> populateOrcamentoVO(List<Orcamento> orcamentos){
		List<OrcamentoVO> orcamentosVO = new ArrayList<OrcamentoVO>();
		OrcamentoVO orcamentoVO;
		
		for(Orcamento orcamento: orcamentos){
			orcamentoVO = new OrcamentoVO();
			orcamentoVO.setObservacao(orcamento.getObservacao());
			orcamentoVO.setPontos(orcamento.getPontos());
			orcamentoVO.setRelato(orcamento.getRelato());
			orcamentoVO.setStatusOrcamento(orcamento.getStatusOrcamento());
			orcamentoVO.setValorTotal(orcamento.getValorTotal());
			orcamentoVO.setId_orcamento(orcamento.getId_orcamento());
			orcamentosVO.add(orcamentoVO);
		}
		
		return orcamentosVO;
	}

}

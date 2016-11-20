package br.com.papa.horizon.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;
import br.com.papa.horizon.vo.OrdemDeServicoVO;

/**
 * 
 * @author Henry O' Papa
 *
 */

public class OrdemDeServicoDao extends GenericDao<OrdemDeServico>{
	public OrdemDeServicoDao() {
		super(OrdemDeServico.class);
	}
	
	public void salvarItensDeServico(List<ItensOrdemServico> itensOrdemServico){
		ItensOrdemServicoDao dao = new ItensOrdemServicoDao();
		dao.saveList(itensOrdemServico);
	}
	
	public List<OrdemDeServicoVO> localizarOS(){
		List<OrdemDeServico> resultOS = findAll();
		List<OrdemDeServicoVO> listOS = new ArrayList<OrdemDeServicoVO>();
		OrdemDeServicoVO ordemDeServicoVO;
		
		for(OrdemDeServico os : resultOS ){
			ordemDeServicoVO = new OrdemDeServicoVO();
			ordemDeServicoVO.setIdOrdemServico(os.getIdOrdemServico());
			ordemDeServicoVO.setEspecialidade(os.getEspecialidade().getDescricao());
			ordemDeServicoVO.setObservacao(os.getObservacao());
			ordemDeServicoVO.setPontos(os.getPontos());
			ordemDeServicoVO.setRelato(os.getRelato());
			ordemDeServicoVO.setStatusOrdemServico(os.getStatusOrdemServico());
			ordemDeServicoVO.setIdFuncionario(os.getFuncionario().getId());
			listOS.add(ordemDeServicoVO);
		}
		
		
		return listOS;
	}
	
	public List<ItensOrdemServico> getItensOrdemServico(Long idOS){
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ItensOrdemServico> criteria = builder.createQuery(ItensOrdemServico.class);
		Root<ItensOrdemServico> from = criteria.from(ItensOrdemServico.class);
		criteria.where(builder.equal(from.get("ordemDeServico"), idOS));
		Query query = getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	
	public List<OrdemDeServicoVO> localizarTODO(StatusOrdemDeServico statusOrdemServico) throws ParseException{
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<OrdemDeServico> criteria = builder.createQuery(OrdemDeServico.class);
		Root<OrdemDeServico> from = criteria.from(OrdemDeServico.class);
		criteria.where(builder.equal(from.get("statusOrdemServico"), statusOrdemServico));
		Query query = getEntityManager().createQuery(criteria);
		
		
		return populateVO(query.getResultList());		
	}
	
	public List<OrdemDeServicoVO> localizarWIP(StatusOrdemDeServico statusOrdemServico) throws ParseException{
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<OrdemDeServico> criteria = builder.createQuery(OrdemDeServico.class);
		Root<OrdemDeServico> from = criteria.from(OrdemDeServico.class);
		criteria.where(builder.equal(from.get("statusOrdemServico"), statusOrdemServico));
		Query query = getEntityManager().createQuery(criteria);
		
		
		return populateVO(query.getResultList());		
	}
	
	public List<OrdemDeServicoVO> localizarDONE(StatusOrdemDeServico statusOrdemServico) throws ParseException{
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<OrdemDeServico> criteria = builder.createQuery(OrdemDeServico.class);
		Root<OrdemDeServico> from = criteria.from(OrdemDeServico.class);
		criteria.where(builder.equal(from.get("statusOrdemServico"), statusOrdemServico));
		Query query = getEntityManager().createQuery(criteria);
		
		
		return populateVO(query.getResultList());		
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

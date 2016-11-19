package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.OrdemDeServico;
import br.com.papa.horizon.vo.OrdemDeServicoVO;
import br.com.papa.horizon.vo.OrdemServicoVO;

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
}

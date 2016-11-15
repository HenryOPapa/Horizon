package br.com.papa.horizon.dao;

import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.ItensOrdemServico;
import br.com.papa.horizon.entity.OrdemDeServico;

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
}

package br.com.papa.horizon.dao;

import br.com.papa.horizon.entity.Cliente;
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
	
	public void adicionaNovaOrdem(Cliente cliente, OrdemDeServico ordemDeServico){
		ClienteDao dao = new ClienteDao();
		cliente = dao.findById(cliente.getId_cliente());
		cliente.addOrdemDeServico(ordemDeServico);
		dao.update(cliente);
		
	}
}

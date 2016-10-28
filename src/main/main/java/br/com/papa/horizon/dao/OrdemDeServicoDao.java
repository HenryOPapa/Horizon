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
}

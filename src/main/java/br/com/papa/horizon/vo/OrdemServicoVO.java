package br.com.papa.horizon.vo;

import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

/**
 * 
 * @author henry.papa
 *
 */
public class OrdemServicoVO extends BaseServicoVO{
	
	private StatusOrdemDeServico statusOrdemServico;

	public StatusOrdemDeServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemDeServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}
	
	
	
		
}

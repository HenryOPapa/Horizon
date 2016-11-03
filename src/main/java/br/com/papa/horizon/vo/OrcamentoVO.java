package br.com.papa.horizon.vo;

import br.com.papa.horizon.util.Enum.StatusOrcamento;

/**
 * 
 * @author henry.papa
 *
 */
public class OrcamentoVO extends BaseServicoVO{
	
	private Long id_orcamento;
	
	private StatusOrcamento statusOrcamento;
	

	public Long getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(Long id_orcamento) {
		this.id_orcamento = id_orcamento;
	}

	public StatusOrcamento getStatusOrcamento() {
		return statusOrcamento;
	}

	public void setStatusOrcamento(StatusOrcamento statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}
	
	
	
}

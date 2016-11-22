package br.com.papa.horizon.vo;

import br.com.papa.horizon.entity.Orcamento;
import br.com.papa.horizon.entity.OrdemDeServico;

public class RelatorioAuxiliarVO {
	
	private Orcamento orcamento;
	private OrdemDeServico ordemDeServico;
	private Long id;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public OrdemDeServico getOrdemDeServico() {
		return ordemDeServico;
	}
	public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}
	
	
	

}

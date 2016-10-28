package br.com.papa.horizon.vo;

import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

/**
 * 
 * @author henry.papa
 *
 */

public class BaseServicoVO {
	
	private String relato;
	
	private String observacao;

	private Double valorTotal;

	private StatusOrdemDeServico statusOrdemServico;
	
	private Integer pontos;
	
	
	
	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getRelato() {
		return relato;
	}

	public void setRelato(String relato) {
		this.relato = relato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusOrdemDeServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemDeServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}


}

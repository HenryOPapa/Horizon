package br.com.papa.horizon.vo;

import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

public class OrdemDeServicoVO {

	private Long idOrdemServico;

	private String relato;

	private String observacao;

	private Double valorTotal;

	private StatusOrdemDeServico statusOrdemServico;
		
	private Integer pontos;
	
	private String especialidade;
	
	private Long idFuncionario;
	
	

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Long getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Long idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
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

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	

}

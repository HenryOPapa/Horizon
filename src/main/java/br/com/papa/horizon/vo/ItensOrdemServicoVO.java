package br.com.papa.horizon.vo;

public class ItensOrdemServicoVO {

	

	private Long idItemOrdemServico;


	private String descricao;


	private Double valor;


	private int quantidade;
	
	private double valorFinal;
	
	


	public Long getIdItemOrdemServico() {
		return idItemOrdemServico;
	}

	public void setIdItemOrdemServico(Long idItemOrdemServico) {
		this.idItemOrdemServico = idItemOrdemServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	




}

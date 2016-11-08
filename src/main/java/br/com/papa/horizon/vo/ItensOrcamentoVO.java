package br.com.papa.horizon.vo;

import javax.persistence.Column;

public class ItensOrcamentoVO {
	

	private Long idItemOrcamento;


	private String descricao;


	private Double valor;


	private int quantidade;
	
	private double valorFinal;
	
	private Long id_orcamento;
	
	

	public Long getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(Long id_orcamento) {
		this.id_orcamento = id_orcamento;
	}

	public Long getIdItemOrcamento() {
		return idItemOrcamento;
	}

	public void setIdItemOrcamento(Long idItemOrcamento) {
		this.idItemOrcamento = idItemOrcamento;
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

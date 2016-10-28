package br.com.papa.horizon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author Diana.Ramos / Felipe.Rodrigo
 *
 */

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PECA_ESTOQUE")
	private Long idPecaEstoque;
	
	@Column(name = "ID_PECA", nullable = false)
	private Long idPeca;
	
	@Column(name="DESCRICAO_PECA", nullable = false)
	private String descricaoPeca;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	private String notaFiscal;
	
	

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Long getIdPecaEstoque() {
		return idPecaEstoque;
	}

	public void setIdPecaEstoque(Long idPecaEstoque) {
		this.idPecaEstoque = idPecaEstoque;
	}

	public Long getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(Long idPeca) {
		this.idPeca = idPeca;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricaoPeca() {
		return descricaoPeca;
	}

	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}
	
	
	
	
}

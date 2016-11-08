
package br.com.papa.horizon.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ATENCAO   ATENCAO
 * ATENCAO ATENCAO
 * ATENCAO ATENCAO
 * ATENCAO ATENCAO
 * ATENCAO ATENCAO
 * ATENCAO ATENCAO
 * ESTA É A ANTIGA CLASSE PECA_UTILIZADA
 * 
 * 
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name = "ITEM_ORCAMENTO")
public class ItensOrcamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -143503253765503845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ITEM_ORCAMENTO")
	private Long idItemOrcamento;

	@Column(name = "DESCRICAO", nullable = false, length = 30)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Column(name = "QUANTIDADE", nullable = false)
	private int quantidade;
	
	@Column(name = "VALOR_TOTAL")
	private double valorTotal;

	@Column(name = "ID_ORCAMENTO")
	private Long idOrcamento;

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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

	public Long getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(Long idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}



}


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
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name = "peca_ordem_servico")
public class PecaOrdemServico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -143503253765503845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PECA_ORDEM_SERVICO")
	private Long id_PecaOrdemServico;
	
	@Column(name = "DESCRICAO", nullable = false, length = 30)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;
		
	@Column(name = "QUANTIDADE", nullable = false)
	private int quantidade;
	
	private double valorFinal;
		
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_ORDEM_DE_SERVICO")
	private OrdemDeServico ordemDeServico;
	

	public Long getId_PecaOrdemServico() {
		return id_PecaOrdemServico;
	}

	public void setId_PecaOrdemServico(Long id_PecaOrdemServico) {
		this.id_PecaOrdemServico = id_PecaOrdemServico;
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

	public OrdemDeServico getOrdemDeServico() {
		return ordemDeServico;
	}

	public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	
	
}

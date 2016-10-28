
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
@Table(name = "ITEM_ORDEM_SERVICO")
public class ItensOrdemServico implements Serializable{


	private static final long serialVersionUID = -7302959751367178758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ITEM_SERVICO")
	private Long idItemServico;

	@Column(name = "DESCRICAO", nullable = false, length = 30)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Column(name = "QUANTIDADE", nullable = false)
	private int quantidade;

	@Column(name = "ID_OS")
	private Long idOS;


	public Long getIdItemServico() {
		return idItemServico;
	}

	public void setIdItemServico(Long idItemServico) {
		this.idItemServico = idItemServico;
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

	public Long getIdOS() {
		return idOS;
	}

	public void setIdOS(Long idOS) {
		this.idOS = idOS;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}



}


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
@Table(name = "peca_utilizada")
public class PecaUtilizada implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -143503253765503845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PECA_UTILIZADA")
	private Long id_pecaUtilizada;
	
	@Column(name = "DESCRICAO", nullable = false, length = 30)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_ORCAMENTO")
	private Orcamento orcamento;
	
	
	public Long getId_pecaUtilizada() {
		return id_pecaUtilizada;
	}

	public void setId_pecaUtilizada(Long id_pecaUtilizada) {
		this.id_pecaUtilizada = id_pecaUtilizada;
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

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	
	
	
}

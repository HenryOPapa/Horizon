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
@Table(name = "peca")
public class Peca implements Serializable{

	private static final long serialVersionUID = 3614421728068394774L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PECAS")
	private Long id;

	@Column(name = "DESCRICAO", nullable = false, length = 30)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	
	
}

package br.com.papa.horizon.entity.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author henry.papa
 *
 */

@Entity
@Table (name = "LOG_NOTA_FISCAL")
public class LOGNotaFiscal implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1312605234662390269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOG_NOTA_FISCAL")
	private Long idLogNotaFiscal;
	
	@Column(name="NUMERO_NOTA_FISCAL")
	private String numeroNotaFiscal;
	
	@Column(name="DATA_INCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;
	
	@Column(name="DESCRICAO_PECA")
	private String descricaoPeca;
	
	@Column(name = "QUANTIDADE_PECA")
	private Integer quantidadePeca;

	public Long getIdLogNotaFiscal() {
		return idLogNotaFiscal;
	}

	public void setIdLogNotaFiscal(Long idLogNotaFiscal) {
		this.idLogNotaFiscal = idLogNotaFiscal;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getDescricaoPeca() {
		return descricaoPeca;
	}

	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}

	public Integer getQuantidadePeca() {
		return quantidadePeca;
	}

	public void setQuantidadePeca(Integer quantidadePeca) {
		this.quantidadePeca = quantidadePeca;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	
	
	
	
	
}

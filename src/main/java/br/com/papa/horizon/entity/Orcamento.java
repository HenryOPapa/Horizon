package br.com.papa.horizon.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.papa.horizon.util.Enum.StatusOrcamento;

/**
 * 
 * @author Henry O' Papa
 *
 */
@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable{

	private static final long serialVersionUID = -4573440496209503089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORCAMENTO")
	private Long id_orcamento;

	@Column(name="RELATO")
	private String relato;

	@Column(name = "OBSERVACAO")
	private String observacao;

	@Column(name = "VALORTOTAL")
	private Double valorTotal;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusOrcamento statusOrcamento;

	@Column(name = "PONTOS")
	private Integer pontos;

	@OneToOne
	@JoinColumn(name="ID_ESPECIALIDADE")
	private Especialidade especialidade;

	@OneToOne
	@JoinColumn(name="ID_EQUIPAMENTO")
	private Equipamento equipamento;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	
	

	public Long getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(Long id_orcamento) {
		this.id_orcamento = id_orcamento;
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

	public StatusOrcamento getStatusOrcamento() {
		return statusOrcamento;
	}

	public void setStatusOrcamento(StatusOrcamento statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	



}

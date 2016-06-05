package br.com.papa.horizon.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "EQUIPAMENTO_DANIFICADO")
	private String equipamentoDanificado;
	
	@Column(name = "ESPECIALIDADE")
	private String especialidade;
	
	@Column(name = "VALORTOTAL")
	private Double valorTotal;
	
	@Column(name = "STATUS")
	private StatusOrcamento statusOrcamento;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;	
	
	@OneToMany(mappedBy = "orcamento" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PecaUtilizada> pecas;
	
	public void  addPeca(PecaUtilizada peca){
		if (pecas == null){
			pecas = new ArrayList<PecaUtilizada>();
		}
		peca.setOrcamento(this);
		pecas.add(peca);
	}

	public void deletePeca(PecaUtilizada peca){
		if(pecas != null){
			pecas.remove(peca);
		}
	}

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

	public String getEquipamentoDanificado() {
		return equipamentoDanificado;
	}

	public void setEquipamentoDanificado(String equipamentoDanificado) {
		this.equipamentoDanificado = equipamentoDanificado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PecaUtilizada> getPecas() {
		return pecas;
	}

	public void setPecas(List<PecaUtilizada> pecas) {
		this.pecas = pecas;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
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
	
	
		
	
}

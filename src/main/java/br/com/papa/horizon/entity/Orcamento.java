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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
	
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "PONTOS")
	private Integer pontos;
	
	@Column(name = "ID_EQUIPAMENTO")
	private Long idEquipamento;
	
	@Column(name = "ID_ESPECIALIDADE")
	private Long idEspecialidade;

	@OneToMany(mappedBy = "orcamento" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PecaUtilizada> pecas;	

	@OneToMany(mappedBy = "orcamento" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Servico> servicos;


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

	public void  addServico(Servico servico){
		if (servicos == null){
			servicos = new ArrayList<Servico>();
		}
		servico.setOrcamento(this);
		servicos.add(servico);
	}

	public void deleteEquipamento(PecaUtilizada peca){
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


	public void setPecas(List<PecaUtilizada> pecas) {
		this.pecas = pecas;
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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<PecaUtilizada> getPecas() {
		return pecas;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(Long idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public Long getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Long idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	

	










}

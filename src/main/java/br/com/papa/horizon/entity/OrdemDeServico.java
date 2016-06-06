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
import javax.persistence.Table;



import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

/**
 * 
 * @author Henry O' Papa
 *
 */
@Entity
@Table(name = "ORDEM_DE_SERIVCO")
public class OrdemDeServico implements Serializable{
	

	private static final long serialVersionUID = 3785531877846933514L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDEM_DE_SERVICO")
	private Long id_ordem_de_servico;
	
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
	private StatusOrdemDeServico statusOrdemDeServico;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;	
	
	@OneToMany(mappedBy = "ordemDeServico" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PecaOrdemServico> pecas;	
	
	@OneToMany(mappedBy = "ordemDeServico" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Servico> servicos;
	
	
	public void  addPeca(PecaOrdemServico peca){
		if (pecas == null){
			pecas = new ArrayList<PecaOrdemServico>();
		}
		peca.setOrdemDeServico(this);
		pecas.add(peca);
	}

	public void deletePeca(PecaOrdemServico peca){
		if(pecas != null){
			pecas.remove(peca);
		}
	}
	
	public void  addServico(Servico servico){
		if (servicos == null){
			servicos = new ArrayList<Servico>();
		}
		servico.setOrdemDeServico(this);
		servicos.add(servico);
	}

	public void deleteServico(Servico servico){
		if(servicos != null){
			servicos.remove(servico);
		}
	}


	public Long getId_ordem_de_servico() {
		return id_ordem_de_servico;
	}

	public void setId_ordem_de_servico(Long id_ordem_de_servico) {
		this.id_ordem_de_servico = id_ordem_de_servico;
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

	public StatusOrdemDeServico getStatusOrdemDeServico() {
		return statusOrdemDeServico;
	}

	public void setStatusOrdemDeServico(StatusOrdemDeServico statusOrdemDeServico) {
		this.statusOrdemDeServico = statusOrdemDeServico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PecaOrdemServico> getPecas() {
		return pecas;
	}

	public void setPecas(List<PecaOrdemServico> pecas) {
		this.pecas = pecas;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	
	

	
		
	
}

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

import br.com.papa.horizon.util.Enum.StatusOrdemDeServico;

/**
 * 
 * @author Henry O' Papa
 *
 */
@Entity
@Table(name = "ORDEM_DE_SERVICO")
public class OrdemDeServico implements Serializable{

	private static final long serialVersionUID = -4573440496209503089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDEM_SERVICO")
	private Long idOrdemServico;

	@Column(name="RELATO")
	private String relato;

	@Column(name = "OBSERVACAO")
	private String observacao;

	@Column(name = "VALORTOTAL")
	private Double valorTotal;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusOrdemDeServico statusOrdemServico;
		
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
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_FUNCIONARIO")
	private Funcionario funcionario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ORDEM_SERVICO")
	private List<ItensOrdemServico> itensOrdemServico;
	
	public void  addItemOS(ItensOrdemServico itemOrdemServico){
		if (itensOrdemServico == null){
			itensOrdemServico = new ArrayList<ItensOrdemServico>();
		}
				itemOrdemServico.setOrdemDeServico(this);
				itensOrdemServico.add(itemOrdemServico);
	}

	public void deleteItemOS(ItensOrdemServico itemOrdemServico){
		if(itensOrdemServico != null){
			itensOrdemServico.remove(itensOrdemServico);
		}
	}

	
	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItensOrdemServico> getItensOrdemServico() {
		return itensOrdemServico;
	}

	public void setItensOrdemServico(List<ItensOrdemServico> itensOrdemServico) {
		this.itensOrdemServico = itensOrdemServico;
	}

	public Long getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Long idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public StatusOrdemDeServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemDeServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
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

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

	










}

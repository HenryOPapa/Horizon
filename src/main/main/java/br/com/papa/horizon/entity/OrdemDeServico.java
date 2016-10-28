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
import javax.persistence.Table;




import br.com.papa.horizon.util.Enum.StatusOrcamento;
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
	
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "PONTOS")
	private Integer pontos;
	
	@Column(name = "ID_EQUIPAMENTO")
	private Long idEquipamento;
	
	@Column(name = "ID_ESPECIALIDADE")
	private Long idEspecialidade;

	@OneToMany(mappedBy = "orcamento" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Servico> servicos;


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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
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

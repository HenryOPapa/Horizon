
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

/**
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name = "clientes") 
public class Cliente implements Serializable{

	private static final long serialVersionUID = -4573440496209503089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id_cliente;

	@Column(name = "NOME", nullable = false, length = 250)
	private String nome;
	
	@Column(name = "DATA_NASCIMENTO")
	private String dataNascimento;
		
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name="CEP")
	private String cep;
	
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CLIENTE")
	private List<Equipamento> equipamentos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CLIENTE")
	private List<Orcamento> orcamentos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_CLIENTE")
	private List<OrdemDeServico> ordensDeServico ;
	
	public void  addOrcamento(Orcamento orcamento){
		if (orcamentos == null){
			orcamentos = new ArrayList<Orcamento>();
		}
		orcamento.setCliente(this);
		orcamentos.add(orcamento);
	}

	public void deleteOrcamento(Orcamento orcamento){
		if(orcamentos != null){
			orcamentos.remove(orcamento);
		}
	}
	
	public void  addOrdemServico(OrdemDeServico ordemDeServico){
		if (ordensDeServico == null){
			ordensDeServico = new ArrayList<OrdemDeServico>();
		}
		ordemDeServico.setCliente(this);
		ordensDeServico.add(ordemDeServico);
	}

	public void deleteOrdemServico(OrdemDeServico ordemDeServico){
		if(ordensDeServico != null){
			ordensDeServico.remove(ordemDeServico);
		}
	}

	public void  addEquipamento(Equipamento equipamento){
		if (equipamentos == null){
			equipamentos = new ArrayList<Equipamento>();
		}
		equipamento.setCliente(this);
		equipamentos.add(equipamento);
	}

	public void deleteEquipamento(Equipamento equipamento){
		if(equipamentos != null){
			equipamentos.remove(equipamento);
		}
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}




	
	
}

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name = "funcionarios") 
public class Funcionario implements Serializable{

	private static final long serialVersionUID = -4573440496209503089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FUNCIONARIO")
	private Long id;

	@Column(name = "NOME", nullable = false, length = 30)
	private String nome;

	@Column(name = "DATA_NASCIMENTO")
	private String dataNascimento;
	
	@Column(name = "RG")
	private String rg;
	
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ESPECIALIDADE")
	private String especialidade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_FUNCIONARIO")
	private List<OrdemDeServico> ordensDeServico ;
	
	public void  addOrdemServico(OrdemDeServico ordemDeServico){
		if (ordensDeServico == null){
			ordensDeServico = new ArrayList<OrdemDeServico>();
		}
		ordemDeServico.setFuncionario(this);
		ordensDeServico.add(ordemDeServico);
	}

	public void deleteOrdemServico(OrdemDeServico ordemDeServico){
		if(ordensDeServico != null){
			ordensDeServico.remove(ordemDeServico);
		}
	}


		
	
	public List<OrdemDeServico> getOrdensDeServico() {
		return ordensDeServico;
	}

	public void setOrdensDeServico(List<OrdemDeServico> ordensDeServico) {
		this.ordensDeServico = ordensDeServico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
//	public Documento getDocumento() {
//		return documento;
//	}
//
//	public void setDocumento(Documento documento) {
//		this.documento = documento;
//	}
//
//	public Contato getContato() {
//		return contato;
//	}
//
//	public void setContato(Contato contato) {
//		this.contato = contato;
//	}
//
//	public Endereco getEndereco() {
//		return endereco;
//	}
//
//	public void setEndereco(Endereco endereco) {
//		this.endereco = endereco;
//	}




	//	@Override
	//	public boolean equals(Object obj) {
	//		if (this == obj)
	//			return true;
	//		if (obj == null)
	//			return false;
	//		if (getClass() != obj.getClass())
	//			return false;
	//		Pessoa other = (Pessoa) obj;
	//		if (id == null) {
	//			if (other.id != null)
	//				return false;
	//		} else if (!id.equals(other.id))
	//			return false;
	//		return true;
	//	}
	//


}

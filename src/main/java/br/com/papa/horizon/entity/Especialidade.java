package br.com.papa.horizon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "especialidade")
public class Especialidade implements Serializable{
	
	private static final long serialVersionUID = -4573440496209503089L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ID_ESPECIALIDADE")
	private Long id_especialidade;
	
	@Column (name = "descricao")
	private String descricao;


	public Long getId_especialidade() {
		return id_especialidade;
	}

	public void setId_especialidade(Long id_especialidade) {
		this.id_especialidade = id_especialidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	

}

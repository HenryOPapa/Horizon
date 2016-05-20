package br.com.papa.horizon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.papa.horizon.util.Enum.NivelAcesso;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_USUARIO")
	private long id;
	
	@Column(name = "LOGIN", nullable=false)
	private String login;
	
	@Column(name = "SENHA", nullable=false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NIVEL_ACESSO", nullable=false)
	private NivelAcesso nivelAcesso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	
	
	
	
	

}

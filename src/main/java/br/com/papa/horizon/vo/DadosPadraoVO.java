package br.com.papa.horizon.vo;

import br.com.papa.horizon.entity.Usuario;

public class DadosPadraoVO {
	
	private FuncionarioVO funcionario;
	private Usuario usuario;

	
	public FuncionarioVO getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}

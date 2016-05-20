package br.com.papa.horizon.dao;

import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Funcionario;
/**
 * 
 * @author Henry O' Papa
 *
 */

public class FuncionarioDao extends GenericDao<Funcionario>{
	
	public FuncionarioDao(){
		super(Funcionario.class);
	}
	
	public List<Funcionario> procuraPorCpf(String cpf){
		String jpql = "from Pessoa p where p.cpf like ?";
		return find(jpql, cpf);
	}
	
	public List<Funcionario> procuraPorNome(String nome){
		String jpql = "from Pessoa p where p.cpf like ?";
		return find(jpql, nome);
	}
	
	
	
}

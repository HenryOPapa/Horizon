package br.com.papa.horizon.dao;

import java.util.List;

import br.com.papa.horizon.entity.Especialidade;
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
	
	public Funcionario localizarFuncionario(String idUsuario){
		String jpql = "from Funcionario f where f.usuario = ?";
		return (Funcionario) findOne(jpql, idUsuario);
	}
	
	public List<Especialidade> localizarEspecialidade(){
		EspecialidadeDao dao = new EspecialidadeDao();
		return dao.findAll();
	}
	
}

package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.vo.FuncionarioVO;
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
	
	public List<FuncionarioVO> localizarFuncionarios(){
		return createFuncionarioVO(findAll());
	}

	private List<FuncionarioVO> createFuncionarioVO(List<Funcionario> funcionarios) {
		List<FuncionarioVO> funcionariosVO = new ArrayList<FuncionarioVO>();
		FuncionarioVO funcionarioVO;
		for(Funcionario func : funcionarios){
			funcionarioVO = new FuncionarioVO();
			funcionarioVO.setCidade(func.getCidade());
			funcionarioVO.setCpf(func.getCpf());
			funcionarioVO.setDataNascimento(func.getDataNascimento());
			funcionarioVO.setEmail(func.getEmail());
			funcionarioVO.setEspeciliadade(func.getEspecialidade());
			funcionarioVO.setEstado(func.getEstado());
			funcionarioVO.setId(func.getId());
			funcionarioVO.setLogradouro(func.getEndereco());
			funcionarioVO.setNome(func.getNome());
			funcionarioVO.setTelefone(func.getTelefone());
			funcionariosVO.add(funcionarioVO);
		}
		
		return funcionariosVO;
	}
	
}

package br.com.papa.horizon.dao;


import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Usuario;
import br.com.papa.horizon.vo.FuncionarioVO;

/**
 * 
 * @author Henry O' Papa
 *
 */

public class UsuarioDao extends GenericDao <Usuario>{
	
	public UsuarioDao(){
		super(Usuario.class);
	}
	
	public Usuario localizaUsuario(String login){
		String jpql = "from Usuario u where u.login like ?";
		return (Usuario) findOne(jpql, login);
	}
	
	public FuncionarioVO localizaFuncionario(Long id){
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.findById(id);
		
		return populateFuncionarioVO(funcionario);
	}
	
	
	public FuncionarioVO populateFuncionarioVO(Funcionario funcionario){
		FuncionarioVO funcionarioVO = new FuncionarioVO();
		funcionarioVO.setEspeciliadade(funcionario.getEspecialidade());
		funcionarioVO.setNome(funcionario.getNome());
		funcionarioVO.setId(funcionario.getId());
		funcionarioVO.setEmail(funcionario.getEmail());
		
		return funcionarioVO;
	}
	
	
	

}

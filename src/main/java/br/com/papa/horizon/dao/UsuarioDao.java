package br.com.papa.horizon.dao;


import br.com.papa.horizon.entity.Usuario;

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

}

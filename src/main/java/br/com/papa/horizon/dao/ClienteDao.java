package br.com.papa.horizon.dao;

import javax.persistence.EntityManager;

/**
 * 
 * @author Henry O' Papa
 *
 */
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.util.Util;

public class ClienteDao extends GenericDao<Cliente>{
	
	public ClienteDao(){
		super(Cliente.class);
	}
	
	protected EntityManager getEntityManager(){
		return Util.getInstance().getEntityManager();
	}
	
	
	public Cliente procuraPorId(Long id){
		String jpql = "from Cliente c where c.id_cliente like ?";
		return (Cliente) find(jpql, id);
	}
	
	public Cliente procuraPorCpf(String cpf){
		String jpql = "from Cliente c where c.cpf like ?";
		return (Cliente) findOne(jpql, cpf);
	}
	
	
	
}

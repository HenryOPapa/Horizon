package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author Henry O' Papa
 *
 */
import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.util.Util;
import br.com.papa.horizon.vo.ClienteVO;

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
	
	public Cliente findByCPF(String idCliente){
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> from = criteria.from(Cliente.class);
		criteria.where(builder.equal(from.get("id_cliente"),idCliente ));
		Query query = getEntityManager().createQuery(criteria);
		return (Cliente) query.getSingleResult();
		
	}
	
	public Cliente achaCliente(String cpf){
		String jpql = "Select c from Cliente e where c.cpf = ?";
		List<Cliente> result = new ArrayList<Cliente>();
		result =  (List<Cliente>) findOne2(jpql,cpf);
			
		return result.get(0);
	}
	
	public List<ClienteVO> buscarClientes(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = findAll();
		return populateClienteVO(clientes);
	}
	
	
	
	/**
	 * Inicio dos Metodos de Populaçao
	 */
	
	public List<ClienteVO> populateClienteVO(List<Cliente> clientes){
		List<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		ClienteVO clienteVO;
		for(Cliente cliente : clientes){
			clienteVO = new ClienteVO();
			clienteVO.setCep(cliente.getCep());
			clienteVO.setCidade(cliente.getCidade());
			clienteVO.setCpf(cliente.getCpf());
			clienteVO.setDataNascimento(cliente.getDataNascimento());
			clienteVO.setEmail(cliente.getEmail());
			clienteVO.setEstado(cliente.getEstado());
			clienteVO.setId(cliente.getId_cliente());
			clienteVO.setLogradouro(cliente.getLogradouro());
			clienteVO.setNome(cliente.getNome());
			clienteVO.setTelefone(cliente.getTelefone());
			clientesVO.add(clienteVO);
		}
		
		return clientesVO;
	}
	
	
	
}

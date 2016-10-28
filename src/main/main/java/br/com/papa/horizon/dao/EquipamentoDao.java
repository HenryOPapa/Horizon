package br.com.papa.horizon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.Equipamento;
/**
 * 
 * @author Henry O' Papa
 *
 */

@Repository
@Transactional(readOnly = true)
public class EquipamentoDao extends GenericDao<Equipamento>{
	
	public EquipamentoDao() {
		super(Equipamento.class);
		// TODO Auto-generated constructor stub
	}
	
	public Equipamento findByNumSerie(String numSerie){
		String jpql = "from Equipamento e where e.numeroSerie like ?";
		return (Equipamento) findOne(jpql, numSerie);
	}
	
	public Cliente localizarDonoEquipamento (Cliente cliente){
		ClienteDao dao = new ClienteDao();
		Long id = cliente.getId_cliente();
		cliente = dao.findById(id);
	
	return cliente;

	}
	
}

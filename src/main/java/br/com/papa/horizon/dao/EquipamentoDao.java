package br.com.papa.horizon.dao;

import br.com.papa.horizon.entity.Equipamento;

import org.springframework.web.servlet.ModelAndView;

import br.com.papa.horizon.entity.Cliente;
/**
 * 
 * @author Henry O' Papa
 *
 */

public class EquipamentoDao extends GenericDao<Equipamento>{
	
	public EquipamentoDao() {
		super(Equipamento.class);
		// TODO Auto-generated constructor stub
	}
	
	public void adicionarEquipamento(Cliente cliente, Equipamento equipamento){
		ClienteDao dao = new ClienteDao();
		cliente = dao.findById(cliente.getId_cliente());
		cliente.addEquipamento(equipamento);
		dao.update(cliente);
		
	}
	public Cliente localizarDonoEquipamento (Cliente cliente){
		ClienteDao dao = new ClienteDao();
		Long id = cliente.getId_cliente();
		cliente = dao.findById(id);
	
	return cliente;

	}
	
}

package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.papa.horizon.entity.Estoque;
import br.com.papa.horizon.entity.Peca;
import br.com.papa.horizon.util.Util;


/**
 * 
 * @author Diana.Ramos / Felipe.Rodrigo
 *
 */

public class EstoqueDao extends GenericDao<Estoque>{
	 private PecasDao pecaDao;
	
	public EstoqueDao() {
		super(Estoque.class);
	}
	
	protected EntityManager getEntityManager(){
		return Util.getInstance().getEntityManager();
	}
	

	
	public List<Peca> buscaPecas (){
		pecaDao = new PecasDao();
		return (List<Peca>) pecaDao.findAll();
	}
	
	public Peca localizaPeca(Long idPeca){
		pecaDao = new PecasDao();
		return (Peca) pecaDao.findById(idPeca);
	}
	
	public Estoque pecaEmEstoque(Long id){
		String jpql = "Select e from Estoque e where e.idPeca = ?";
		List<Estoque> result = new ArrayList<Estoque>();
		result =  (List<Estoque>) findOne2(jpql,id);
			
		return result.get(0);
	}

}

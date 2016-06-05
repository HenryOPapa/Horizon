package br.com.papa.horizon.dao;



import br.com.papa.horizon.entity.Peca;


/**
 * 
 * @author Henry O' Papa
 *
 */

public class PecasDao extends GenericDao <Peca>{
	
	public PecasDao() {
		super(Peca.class);
	}
	
	public Peca localizaPecaPorNome(String descricao){
		String jpql = "from Peca p where p.descricao like ?";
		return (Peca) findOne(jpql, descricao);
	}
	

	
}

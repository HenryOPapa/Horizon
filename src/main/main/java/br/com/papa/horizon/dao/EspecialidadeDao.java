package br.com.papa.horizon.dao;

import org.springframework.stereotype.Repository;

import br.com.papa.horizon.entity.Especialidade;

/**
 * 
 * @author Henry O' Papa
 *
 */
@Repository
public class EspecialidadeDao extends GenericDao <Especialidade>{
	
	public EspecialidadeDao(){
		super(Especialidade.class);
	}
	
	
	public Especialidade findEspecialidade(Long idEspecialidade){
		return (Especialidade) findById(idEspecialidade);
	}

}

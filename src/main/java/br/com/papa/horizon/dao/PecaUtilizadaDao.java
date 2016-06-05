package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.PecaUtilizada;

public class PecaUtilizadaDao extends GenericDao<PecaUtilizada>{

	public PecaUtilizadaDao() {
		super(PecaUtilizada.class);
	}
	

	public List<PecaUtilizada> recuperarPecas(Long id_orcamento){
		List<PecaUtilizada> pecasUtilizadas = findAll();
		List<PecaUtilizada> pecas = new ArrayList();
		for(PecaUtilizada peca : pecasUtilizadas){
			if(peca.getOrcamento().getId_orcamento() == id_orcamento){
				pecas.add(peca);				
			}
		}
		return pecas;
		
	}	
	

}

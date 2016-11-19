package br.com.papa.horizon.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.papa.horizon.entity.Cliente;
import br.com.papa.horizon.entity.ItensOrcamento;

public class PecaUtilizadaDao extends GenericDao<ItensOrcamento>{

	public PecaUtilizadaDao() {
		super(ItensOrcamento.class);
	}
	
}

package br.com.papa.horizon.vo;

import java.util.List;

import br.com.papa.horizon.entity.Equipamento;
import br.com.papa.horizon.entity.Especialidade;
import br.com.papa.horizon.entity.ItensOrcamento;
import br.com.papa.horizon.entity.Orcamento;

public class OrcamentoAuxiliarVO {
	
	Orcamento orcamento;
	Equipamento equipamento;
	Especialidade especialidade;
	List<ItensOrcamento> itensOrcamento;


	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	public List<ItensOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}
	public void setItensOrcamento(List<ItensOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}
	
	
	

}
